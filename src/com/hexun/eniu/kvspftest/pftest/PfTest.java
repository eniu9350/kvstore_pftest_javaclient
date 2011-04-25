package com.hexun.eniu.kvspftest.pftest;

public class PfTest {
	private static int currentId;
	static {
		currentId = 0;
	}

	/**
	 * id
	 */
	private int id;

	/**
	 * repetitions per round
	 */
	private int reps;

	/**
	 * total rounds
	 */
	private int rounds;

	public PfTest(){
		id = PfTest.currentId++;
	}
	
	public PfTest(int reps, int rounds) {
		id = PfTest.currentId++;
		this.reps = reps;
		this.rounds = rounds;
	}

	public void loadLine(String line) {
		String[] tokens = line.split(" ");
		if (tokens.length != 2) {
			System.out
					.println("each line of cfg must contain exactly 2 parameters!");
			return;
		} else {
			this.reps = Integer.parseInt(tokens[0]);
			this.rounds = Integer.parseInt(tokens[1]);
		}
	}

	// ------------- getters and setters ------------------------------

	public int getReps() {
		return reps;
	}

	public void setReps(int reps) {
		this.reps = reps;
	}

	public int getRounds() {
		return rounds;
	}

	public void setRounds(int rounds) {
		this.rounds = rounds;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
