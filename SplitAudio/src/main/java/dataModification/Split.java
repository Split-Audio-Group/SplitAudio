package dataModification;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import util.UtilDB;

/**
 * Servlet implementation class Split
 */
@WebServlet("/Split")
public class Split extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Split() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String filename = UtilDB.getSession().getCurrentFile();
		String segmentDuration = request.getParameter("splitWhen").trim();
		 
		
		System.out.println(UtilDB.getSession().getCurrentFile());
		//System.out.println(filename);
		
		// split time
		int segmentDurationInSeconds = Integer.parseInt(segmentDuration);
		
		System.out.println(segmentDurationInSeconds);
		
		//split function
		try {
			File inputFile = UtilDB.getSession().getCurrentFile();
			System.out.println("Size of input file " + inputFile.length());
			
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(inputFile);
	        AudioFormat format = audioInputStream.getFormat();

	        int bytesPerSecond = format.getFrameSize() * (int)format.getFrameRate();
			int segmentLength = bytesPerSecond * segmentDurationInSeconds;

	        int segmentNumber = 1;
	        int bytesRead = 0;
	        byte[] audioBuffer = new byte[segmentLength];

	        File outputFile = new File(inputFile.getParentFile(), "segment_" + segmentNumber + ".wav");
	        while ((bytesRead = audioInputStream.read(audioBuffer)) != -1) {
	            
	            AudioInputStream segmentAudioInputStream = new AudioInputStream(audioInputStream, format, bytesRead);
	            AudioSystem.write(segmentAudioInputStream, AudioFileFormat.Type.WAVE, outputFile);
	            segmentNumber++;
	        }

	        
	        response.setContentType("text/html");
	        PrintWriter out = response.getWriter();
	        String title = "Download a file";
	        String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n"; //
	        out.println(docType + //
	              "<html>\n" + //
	              "<head><title>" + title + "</title></head>\n" + //
	              "<body bgcolor=\"#f0f0f0\">\n" + //
	              "<h1 align=\"center\">" + title + "</h1>\n");
	        out.println("<h1>Download a file</h1>\n"
	        		+ "	<p>Click the button to download the file:</p>\n"
	        		+ "	<a href="+ outputFile +" download>Download File</a>");

	        
	        System.out.println("Size of output file " + outputFile.length());
	        audioInputStream.close();
			
		}
		catch (UnsupportedAudioFileException e){
			response.sendRedirect(request.getContextPath() + "/EditPage.html?success=badFileSize");
			System.out.println(e);
		}
		catch(NullPointerException e) {
			response.sendRedirect(request.getContextPath() + "/EditPage.html?success=false");
		}
		catch (IOException e) {
			response.sendRedirect(request.getContextPath() + "/EditPage.html?success=false");
			System.out.println(e);
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