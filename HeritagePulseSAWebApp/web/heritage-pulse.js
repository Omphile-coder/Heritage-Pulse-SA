/**
 * Heritage Pulse SA - Smart GPS Logic
 * Handles real-time location detection and event triggering.
 */

document.addEventListener("DOMContentLoaded", function() {
    // Check if we are on the GPS page
    const mapDisplay = document.getElementById("map-display");
    if (mapDisplay) {
        initiateCulturalGPS();
    }
});

function initiateCulturalGPS() {
    if (navigator.geolocation) {
        // Options for high accuracy for the "Pulse" trigger
        const options = {
            enableHighAccuracy: true,
            timeout: 5000,
            maximumAge: 0
        };

        navigator.geolocation.getCurrentPosition(handleSuccess, handleError, options);
    } else {
        alert("Geolocation is not supported by your browser. Heritage Pulse cannot detect nearby events.");
    }
}

/**
 * Handle successful coordinate retrieval
 * Triggers a redirect to the EventServlet to fetch location-based data 
 */
function handleSuccess(position) {
    const lat = position.coords.latitude;
    const lng = position.coords.longitude;

    // Check if we already have these coordinates in the URL to prevent infinite loops
    const urlParams = new URLSearchParams(window.location.search);
    if (urlParams.get('lat') === lat.toString() && urlParams.get('lng') === lng.toString()) {
        console.log("Location already updated.");
        return;
    }

    // Redirect to the Servlet to trigger the "Pulse" notification of nearby landmarks [cite: 48]
    window.location.href = "EventServlet?lat=" + lat + "&lng=" + lng;
}

function handleError(error) {
    let message = "An error occurred while detecting your location: ";
    switch(error.code) {
        case error.PERMISSION_DENIED:
            message += "User denied the request for Geolocation.";
            break;
        case error.POSITION_UNAVAILABLE:
            message += "Location information is unavailable.";
            break;
        case error.TIMEOUT:
            message += "The request to get user location timed out.";
            break;
        default:
            message += "An unknown error occurred.";
            break;
    }
    console.error(message);
}

function showPosition(position) {
    const lat = position.coords.latitude;
    const lng = position.coords.longitude;

    // Zoom the map to the user
    map.setView([lat, lng], 10);

    // Add a special pin for the User
    L.circle([lat, lng], {
        color: 'red',
        fillColor: '#f03',
        fillOpacity: 0.5,
        radius: 500
    }).addTo(map).bindPopup("You are here");
}