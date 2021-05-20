package com.bss.inte.boot.controller;

public class MultiBatchThreadCounter {
	private int threadCount = 0;

	public synchronized int increase() {
		return threadCount++;
	}

	public synchronized int decrease() {
		return threadCount--;
	}

	/**
	 * Let Thread Count to -1 to sure that if another thread is waiting to enter
	 * after finishing decreasing the thread count at the same time.
	 * 
	 * @return
	 */
	public synchronized boolean isLastFinishingThread() {
		if (threadCount == 0) {
			threadCount--;
			return true;
		}
		return false;
	}
}
