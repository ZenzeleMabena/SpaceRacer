package csc2a.SpaceRacer.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import csc2a.SpaceRacer.model.Meteor;

/**
 * @author ZB Mabena 219026791
 * @version PX
 * @see Meteor
 * 
 * 
 * This Class will serve the sole purpose of reading in meteor objects from a a provided text file
 */

public class TextFileHandler {
	
	
	
	
	
	/**
	 * This is a Helper function  that will solely parse strings into objects
	 * @param line this is the string that will be used to get data and instantiate a meteor
	 * @return the new Meteor that was instantiated using the line
	 */
	
	private static Meteor  parseMeteor(String line)
	{
		Meteor newMeteor;
		
		StringTokenizer tokenizer = new StringTokenizer(line,"\t");
		
		
		int Type = Integer.parseInt(tokenizer.nextToken());
		int x = Integer.parseInt(tokenizer.nextToken());
		int y = Integer.parseInt(tokenizer.nextToken());
		
		
		newMeteor = new Meteor(Type,x,y);
	
		return newMeteor;
	}
	
	
	////have a static  function for reading in meteors
	
	
	/**
	 * the purpose of this function is to read in information from text file and return meteor objects read in from the file 
	 * @param fileName this is the name of the file from the which the meteor objects will be read from 
	 * @return this is a collection of meteors read in from the given file
	 */
	
	public static ArrayList<Meteor> readMeteors()
	{
		ArrayList<Meteor> meteors = new ArrayList<>();
		
		
		File MeteorFile = new File("data/MeteorFile.txt");
		Scanner txtIn = null;
		
		try
		{
			Meteor newMeteor;
			txtIn = new Scanner(MeteorFile);
			while(txtIn.hasNext())
			{
				String line = txtIn.nextLine();
			
				
				newMeteor = parseMeteor(line);
				
				
				if(newMeteor != null)
				{
				meteors.add(newMeteor);
				}
	
			}
				
			
		}
		catch(FileNotFoundException fnfe)
		{
			fnfe.printStackTrace();
		}
		finally
		{
			if(txtIn != null)
			{
				txtIn.close();
			}
		}

		return meteors;
	}

}
