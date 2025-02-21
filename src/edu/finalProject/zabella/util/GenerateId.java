package edu.finalProject.zabella.util;

public final class GenerateId {
	private static int nextId = 1;
	private GenerateId(){}


	public static int getNextId() {
		return nextId++;
	}


	public static int nextId() {
		return getNextId();
	}
}
