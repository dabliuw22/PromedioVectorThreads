package com.leysoft.promediovector;

import java.util.Random;

import com.leysoft.promediovector.logics.PromedioParallel;

public class App {
    public static void main( String[] args ) {
    	int[] array = llenarArray(1000000);
    	int NUM_HILOS = Runtime.getRuntime().availableProcessors();
    	PromedioParallel parallel = new PromedioParallel(NUM_HILOS);
    	System.out.println("Promedio Total: " + parallel.promedio(array));
    }
    
    public static int[] llenarArray(int SIZE) {
    	int[] array = new int[SIZE];
    	Random random = new Random();
    	for(int i = 1; i <= SIZE; i++) {
    		array[i-1] = random.nextInt(100) + 1;
    	}
    	return array;
    }
}
