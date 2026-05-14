package za.ac.tut.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import za.ac.tut.model.bl.ArchiveItemsFacadeLocal;
import za.ac.tut.model.entity.ArchiveItems;
import za.ac.tut.model.entity.Tribes;


@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2,  // 2MB
    maxFileSize = 1024 * 1024 * 10,       // 10MB
    maxRequestSize = 1024 * 1024 * 50     // 50MB
)
public class UploadServlet extends HttpServlet {

    @EJB
    private ArchiveItemsFacadeLocal archiveFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Just route the user to the form
        request.getRequestDispatcher("contributor.jsp").forward(request, response);
    }

 @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            // 1. Capture the text data
            String title = request.getParameter("title");
            String category = request.getParameter("category");
            String description = request.getParameter("description");
            Integer tribeId = Integer.parseInt(request.getParameter("tribeId"));

            // 2. Handle the File Upload
            Part filePart = request.getPart("mediaFile"); 
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            
            // --- OPTION 2: ABSOLUTE LOCAL PATH ---
            // This points directly to your project's web folder so the files are "permanent"
            String uploadPath = "C:/Users/lucas/Documents/NetBeansProjects/HeritagePulseSA/HeritagePulseSAWebApp/web/uploads";

            File uploadDir = new File(uploadPath);
            // This acts as a safety net in case the folder is ever deleted
            if (!uploadDir.exists()) {
             uploadDir.mkdirs(); 
                }

         // Save the file
            File targetFile = new File(uploadDir, fileName);
            
            try (InputStream fileContent = filePart.getInputStream()) {
                Files.copy(fileContent, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
            // ---------------------------------------

           // We still save the relative path to the DB so the browser can find it
            String virtualFilePath = "uploads/" + fileName;

            // 3. Create the new Entity
            ArchiveItems newItem = new ArchiveItems();
            
            // --- THE MANUAL ID GENERATOR FIX ---
          
            int generatedId = (int) (Math.random() * 90000) + 10000;
            newItem.setItemId(generatedId);
            // -----------------------------------

            newItem.setTitle(title);
            newItem.setCategory(category);
            newItem.setDescription(description);
            newItem.setFilePath(virtualFilePath);
            newItem.setStatus("Pending"); 
            
            Tribes tribe = new Tribes(tribeId);
            newItem.setTribeId(tribe);

            // 4. Save to Database
            archiveFacade.create(newItem);

            // 5. Success Feedback
            request.setAttribute("uploadMessage", "Success! Your contribution has been sent to the moderation queue.");
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("uploadMessage", "An error occurred: " + e.getMessage());
        }

        request.getRequestDispatcher("contributor.jsp").forward(request, response);
    }
}