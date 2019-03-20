package com.tg;

public class SubjectDto {

	private int num = 0;
	private String name = "";
	private int score = 0;
	
	public SubjectDto() {
		super();
	}

	public SubjectDto(int num, String name, int score) {
		super();
		this.num = num;
		this.name = name;
		this.score = score;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "SubjectDto [num=" + num + ", name=" + name + ", score=" + score + "]";
	}
	
}
