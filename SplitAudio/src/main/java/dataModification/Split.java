package dataModification;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

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
		 
		//System.out.println(filename);
		
		int segmentDurationInSeconds = Integer.parseInt(segmentDuration);
		
		System.out.println(segmentDurationInSeconds);
		try {
			File inputFile = UtilDB.getSession().getCurrentFile();
			
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(inputFile);
	        AudioFormat format = audioInputStream.getFormat();

	        int bytesPerSecond = format.getFrameSize() * (int)format.getFrameRate();
//	        int segmentDurationInSeconds;
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
	        
	        request.setAttribute("outputFile", outputFile);
	        request.getRequestDispatcher("src/main/webapp/splitJSP.jsp").forward(request, response);
	        
	        audioInputStream.close();
			
		}
		catch (UnsupportedAudioFileException e){
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