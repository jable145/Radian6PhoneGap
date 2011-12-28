package com.mm;

public class MyShutdownHook implements Runnable {

	@Override
	public void run() {
		System.out.println("Radian6 Proxy Shutdown Hook executing");
	}

}
