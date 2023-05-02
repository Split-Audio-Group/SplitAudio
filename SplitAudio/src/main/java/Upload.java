

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import util.Info;
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
    	response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        
    	
    	try {
        	
        	
        	// Get the uploaded file from the request
            Part filePart = request.getPart("audio-file");
            String filename = filePart.getSubmittedFileName();
            
        
            
            // Get the file extension
            String extension = "";
            int i = filename.lastIndexOf('.');
            if (i > 0) {
                extension = filename.substring(i + 1);
            }

            // Check if the file is an audio file
            if (!extension.equalsIgnoreCase("wav")) {
            	response.sendRedirect(request.getContextPath() + "/EditPage.html?success=badFileExt");
                return;
            }
            
            // Get the file size
            long fileSize = filePart.getSize();

            // Check if the file size is within acceptable limits
            if (fileSize > 10000000) { // 10 MB
            	response.sendRedirect(request.getContextPath() + "/EditPage.html?success=badFileSize");
                return;
            }
            
            
            File file = new File("C:\\audio_files\\" + filename); // Modify this path to match the directory where you want to save the file
            UtilDB.getSession().setFile(file);
            filePart.write(file.getAbsolutePath());
            
            
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
