

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import util.UtilDB;

/**
 * Servlet implementation class Download
 */
@WebServlet("/Download")
@MultipartConfig
public class Download extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Download() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			 File inputFile = UtilDB.getSession().getCurrentFile();
		        
		        // Set the content type and header for the response
		        response.setContentType("application/octet-stream");
		        response.setHeader("Content-disposition", "attachment; filename=" + inputFile.getName());

		        // Get the input stream of the file and create an output stream for the response
		        FileInputStream inputStream = new FileInputStream(inputFile);
		        OutputStream outputStream = response.getOutputStream();

		        // Copy the input stream to the output stream in chunks of 1024 bytes
		        byte[] buffer = new byte[1024];
		        int bytesRead;
		        while ((bytesRead = inputStream.read(buffer)) != -1) {
		            outputStream.write(buffer, 0, bytesRead);
		        }

		        // Close the streams
		        inputStream.close();
		        outputStream.flush();
		        outputStream.close();
		    } catch (Exception e) {
		        // Handle any exceptions
		    }
			
			
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
