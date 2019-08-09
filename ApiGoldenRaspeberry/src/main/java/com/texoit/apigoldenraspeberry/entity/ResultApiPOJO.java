package com.texoit.apigoldenraspeberry.entity;

public class ResultApiPOJO {

	private String producer;
	private int interval;
	private int previousWin;
	private int followingWin;
	private boolean winner;
	
	public ResultApiPOJO(String producer, int interval, int previousWin, int followingWin, boolean winner) {
		super();
		this.producer = producer;
		this.interval = interval;
		this.previousWin = previousWin;
		this.followingWin = followingWin;
		this.winner = winner;
	}
	
	public ResultApiPOJO(String producer, Long interval, Long previousWin, Long followingWin, boolean winner) {
		super();
		this.producer = producer;
		this.interval = interval.intValue();
		this.previousWin = previousWin.intValue();
		this.followingWin = followingWin.intValue();
		this.winner = winner;
	}

	public String getProducer() {
		return producer;
	}
	
	public void setProducer(String producer) {
		this.producer = producer;
	}
	
	public int getInterval() {
		return interval;
	}
	
	public void setInterval(int interval) {
		this.interval = interval;
	}
	
	public int getPreviousWin() {
		return previousWin;
	}
	
	public void setPreviousWin(int previousWin) {
		this.previousWin = previousWin;
	}
	
	public int getFollowingWin() {
		return followingWin;
	}

	public void setFollowingWin(int followingWin) {
		this.followingWin = followingWin;
	}

	public boolean isWinner() {
		return winner;
	}

	public void setWinner(boolean winner) {
		this.winner = winner;
	}
}
