package za.ac.tut.controller;

import java.io.File;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import za.ac.tut.model.bl.ArchiveItemsFacadeLocal;
import za.ac.tut.model.entity.ArchiveItems;

@WebServlet(name = "OralHistoryServlet", urlPatterns = {"/OralHistoryServlet"})
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2,  // 2MB
    maxFileSize = 1024 * 1024 * 50,       // 50MB
    maxRequestSize = 1024 * 1024 * 100    // 100MB
)
public class OralHistoryServlet extends HttpServlet {

    @EJB
    private ArchiveItemsFacadeLocal archiveFacade;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            // 1. Collect form data
            String title = request.getParameter("storyTitle");
            String tribeIdStr = request.getParameter("tribeId");
            Part filePart = request.getPart("oralFile");

            // 2. Define the Absolute Path (Corrected)
            String uploadPath = "C:\\Users\\lucas\\Documents\\Storage\\uploads";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs(); // Creates the 'uploads' folder if missing
            }

            // 3. Save the actual file to the disk
            String fileName = filePart.getSubmittedFileName();
            String fullPath = uploadPath + File.separator + fileName;
            filePart.write(fullPath);

            // 4. Save metadata to the database
            ArchiveItems newItem = new ArchiveItems();
            newItem.setTitle(title);
            newItem.setCategory("Oral History");
            newItem.setFilePath(fullPath); // Store where it lives on the PC
            newItem.setStatus("Pending");  // For the Admin Moderation Queue
            
            // Link to the tribe if you have that relationship set up
            //newItem.setTribeId(Integer.parseInt(tribeIdStr));

            archiveFacade.create(newItem);

            // 5. Success handling
            request.setAttribute("message", "History recorded! Awaiting cultural verification.");
            request.getRequestDispatcher("success.jsp").forward(request, response);

        } catch (Exception ex) {
            request.setAttribute("error", "Upload failed: " + ex.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}