package com.bss.inte.boot.controller;

import java.sql.BatchUpdateException;
import java.sql.SQLException;

public class Tir implements Runnable {

	@Override
	public void run() {
		testcase(); 
		System.out.println("abc");
	}
	
	public void testcase() {
		try {
			throw new BatchUpdateException();
			
		} catch (BatchUpdateException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Thread T = new Thread(new Tir());
		T.start();
	}
}