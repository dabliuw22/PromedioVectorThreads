package com.leysoft.promediovector.logics;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.leysoft.promediovector.tasks.Worker;

public class PromedioParallel {
	
	private ExecutorService service;
	private int NUM_HILOS;
	private CountDownLatch latch;

	public PromedioParallel(int NUM_HILOS) {
		this.NUM_HILOS = NUM_HILOS;
		this.service = Executors.newFixedThreadPool(this.NUM_HILOS);
		this.latch = new CountDownLatch(NUM_HILOS);
	}
	
	public double promedio(int[] array) {
		int size = (int) Math.ceil(array.length*1.0/NUM_HILOS);
		List<Future<Double>> futures = new ArrayList<>();
		for(int i = 0; i < NUM_HILOS; i++) {
			Future<Double> future = service.submit(new Worker(array, i*size, size*(i + 1), latch));
			futures.add(future);
		}
		double promedio = 0.0;
		try {
			for(Future<Double> f: futures) {
				promedio += f.get();
			}
			latch.await();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		service.shutdown();
		return promedio/array.length;
	}
}
