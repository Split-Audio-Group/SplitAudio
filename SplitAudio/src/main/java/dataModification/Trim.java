package dataModification;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
		String filename = request.getParameter("audio-file").trim();
		String startTime = request.getParameter("trimFrom").trim();
		String endTime = request.getParameter("trimToWhen").trim();
		
		float startTimeInSeconds = Float.parseFloat(startTime);
		float endTimeInSeconds = Float.parseFloat(endTime);
	
		System.out.println("Selected file: " + filename);
		System.out.println("Trim from: " + startTimeInSeconds);
		System.out.println("To: " + endTimeInSeconds);
		
		
		try {
			File inputFile = new File(filename);
			File outputFileName = new File("sampleOutputFile.wav");
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(inputFile);
	        AudioFormat format = audioInputStream.getFormat();

			long startByte = (long)(startTimeInSeconds * format.getFrameRate()) * format.getFrameSize();
			long endByte = (long)(endTimeInSeconds * format.getFrameRate()) * format.getFrameSize();

	        AudioInputStream trimmedAudioInputStream = new AudioInputStream(audioInputStream, format, endByte - startByte);
	        trimmedAudioInputStream.skip(startByte);

//	        OutputStream outputFile;
			AudioSystem.write(trimmedAudioInputStream, AudioFileFormat.Type.WAVE, outputFileName);

	        audioInputStream.close();
	        trimmedAudioInputStream.close();
			
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