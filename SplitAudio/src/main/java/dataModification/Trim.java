package dataModification;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import util.UtilDB;

/**
 * Servlet implementation class Trim
 */
@WebServlet("/Trim")
public class Trim extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Trim() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String startTime = request.getParameter("trimFrom").trim();
		String endTime = request.getParameter("trimToWhen").trim();
		
		
		// time for trim
		int startSecond = Integer.parseInt(startTime);
		int endTimeInSeconds = Integer.parseInt(endTime);
		
		int secondsToCopy = endTimeInSeconds - startSecond;
	
		// print them for debug
		System.out.println("Trim from: " + startSecond);
		System.out.println("To: " + endTimeInSeconds);
		
		
		File inputFile = UtilDB.getSession().getCurrentFile();
		File outputFile = new File("SplittedAudioFile.wav");
        System.out.println("Size of input file " + inputFile.length());
		
		AudioInputStream inputStream = null;
		AudioInputStream shortenedStream = null;
		
		try {
			
			AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(inputFile);
			AudioFormat format = fileFormat.getFormat();
			inputStream = AudioSystem.getAudioInputStream(inputFile);
			
			int bytesPerSecond = format.getFrameSize() * (int)format.getFrameRate();
			inputStream.skip(startSecond * bytesPerSecond);
			long framesOfAudioToCopy = secondsToCopy * (int)format.getFrameRate();
			shortenedStream = new AudioInputStream(inputStream, format, framesOfAudioToCopy);
			File destinationFile = new File(inputFile.getParentFile(), "Trimmed" + ".wav");
		    AudioSystem.write(shortenedStream, fileFormat.getType(), destinationFile);
		    
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
	        		+ "	<a href="+ destinationFile +" download>Download File</a>");
	        
	        System.out.println("Size of output file " + destinationFile.length());

		}
		catch (UnsupportedAudioFileException e) {
			System.out.println(e);
		}
		catch (IOException e) {
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