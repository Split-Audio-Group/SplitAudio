

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import datamodel.User;
import util.Info;
import util.SessionLog;
import util.UtilDB;

/**
 * Servlet implementation class Upload
 */
@WebServlet("/Upload")
@MultipartConfig
public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Upload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
    		response.setContentType("text/html;charset=UTF-8");
        	
        	// Get the uploaded file from the request
            Part filePart = request.getPart("audio-file");
            String fileName = filePart.getSubmittedFileName();
            
        
            
            // Get the file extension
            String extension = "";
            int i = fileName.lastIndexOf('.');
            if (i > 0) {
                extension = fileName.substring(i + 1);
            }

            // Check if the file is an audio file
            if (!extension.equalsIgnoreCase("wav")) {
            	response.sendRedirect(request.getContextPath() + "/EditPage.html?success=badFileExt");
                return;
            }
            
            // Get the file size
            double fileSize = filePart.getSize() / 100000.00;

            // Check if the file size is within acceptable limits
            if (fileSize > 250) { // 250 KB
            	response.sendRedirect(request.getContextPath() + "/EditPage.html?success=badFileSize");
                return;
            }
            
         // Read the file content
            byte[] fileContent = new byte[(int) fileSize];
            try {
            	filePart.getInputStream().read(fileContent);
            } catch (IOException e) {
            	e.printStackTrace();
            }
            
            // Get the file path to write
            String filePath = "/home/ubuntu/files/" + fileName;
            
            // Sets userID to default value
            int userID = 1;
				
            // Tries to find the current user  and their associated ID
			User currentUser = SessionLog.getCurrentUser();
			if (currentUser != null) {
				userID = currentUser.getId();
			}

           
            // Write the file to the directory located on the server
            File file = new File(filePath + fileName); // Modify this path to match the directory where you want to save the file
            filePart.write(file.getAbsolutePath());
            
            
            
         // Save the file data to the database
            UtilDB.createFile(userID, fileName, filePath, fileSize, true);
            
         // Redirect back to the form with a success message
            response.sendRedirect(request.getContextPath() + "/EditPage.html?success=true");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
