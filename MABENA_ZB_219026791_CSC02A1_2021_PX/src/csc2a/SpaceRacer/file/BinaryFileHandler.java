package csc2a.SpaceRacer.file;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import csc2a.SpaceRacer.model.Score;


public class BinaryFileHandler {
	
	
	
	
	
	public static void  saveTopScores(Score scoreToSave)
	{
		ObjectOutputStream objOut = null;
		try
		{
			FileOutputStream fos = new FileOutputStream("data//Scores.dat");
			BufferedOutputStream bufOut = new BufferedOutputStream(fos);
			objOut = new ObjectOutputStream(bufOut);
			objOut.writeObject(scoreToSave);
			
			
			
			
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
			
			
		}
		finally
		{
			if(objOut != null)
			{
				try
				{
					objOut.close();
				}
				catch(IOException ioe)
				{
					ioe.printStackTrace();
				}
				
			}
		}

		
	}


}
