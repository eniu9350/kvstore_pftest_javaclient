package com.hexun.eniu.kvspftest.result;

public class ResultEntry {
	private int reps;
	private long time; // ms

	public ResultEntry(int reps, long time) {
		this.reps = reps;
		this.time = time;
	}

	public int getReps() {
		return reps;
	}

	public void setReps(int reps) {
		this.reps = reps;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public String toString() {
		return String.valueOf(reps) + ' ' + String.valueOf(time);
	}

}
