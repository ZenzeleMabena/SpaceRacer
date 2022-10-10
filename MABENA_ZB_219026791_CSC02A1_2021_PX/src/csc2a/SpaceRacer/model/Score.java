package csc2a.SpaceRacer.model;


import java.io.Serializable;

import javafx.beans.property.LongProperty;

import javafx.beans.property.SimpleLongProperty;

public class Score implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7987028706432390315L;
	private LongProperty score;
	private String user = new String("");
	
	
	public Score(long score,String user)
	{
		
		this.score = new SimpleLongProperty(0);
		this.score.set(score);
		this.user = user;
	
		
	}


	/**
	 * @return the score
	 */
	public LongProperty getScore() {
		return score;
	}


	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}


	/**
	 * @param score the score to set
	 */
	public void setScore(int score) {
		this.score.set(score);
	}


	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}
	

}
