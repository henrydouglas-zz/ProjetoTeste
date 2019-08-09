package com.texoit.apigoldenraspeberry.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TX_MOVIE_INFORMATION")
public class MovieInformation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String movieTitle;
	
	@Column(nullable = false)
	private String studio;
	
	@Column(nullable = false)
	private String producer;
	
	@Column(nullable = false)
	private boolean winner;
	
	@Column(nullable = false)
	private Long year;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getMovieTitle() {
		return movieTitle;
	}
	
	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}
	
	public String getStudio() {
		return studio;
	}
	
	public void setStudio(String studio) {
		this.studio = studio;
	}
	
	public String getProducer() {
		return producer;
	}
	
	public void setProducer(String producer) {
		this.producer = producer;
	}
	
	public boolean getWinner() {
		return winner;
	}
	
	public void setWinner(boolean winner) {
		this.winner = winner;
	}
	
	public Long getYear() {
		return year;
	}
	
	public void setYear(Long year) {
		this.year = year;
	}
}