package com.example.beans;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "restaurants")
public class Grade {
	private String date;
	private String grade;
	private String score;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public Grade(String date, String grade, String score) {
		super();
		this.date = date;
		this.grade = grade;
		this.score = score;
	}

}
