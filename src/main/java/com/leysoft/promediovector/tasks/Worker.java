package com.leysoft.promediovector.tasks;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public class Worker implements Callable<Double> {

	private int[] array;
	private int inicio;
	private int fin;
	private CountDownLatch latch;
	
	public Worker(int[] array, int inicio, int fin, CountDownLatch latch) {
		this.array = array;
		this.inicio = inicio;
		this.fin = Math.min(fin, array.length);
		this.latch = latch;
	}

	@Override
	public Double call() throws Exception {
		Thread.sleep(1000);
		double promedio = 0.0;
		for(int i = inicio; i < fin; i++) {
			promedio += array[i];
		}
		System.out.println("promedio("+ inicio + ", " + fin +")... " + promedio/(fin - inicio));
		latch.countDown();
		return new Double(promedio);
	}
}