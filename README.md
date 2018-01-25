# Calculo de Promedio de Vector con Threads

Se calculara el promedio de un array de números enteros usando **Threads**, divideindo el trabajo de forma pararela,
se utilizara la ayuda de la colección **CountDownLatch** del paquete *java.util.concurrent*, para la cual se debe tener en cuenta los siguientes aspectos:

1. Instanciar el **Latch**, donde *NUM_HILOS* representa la cantidad de procesos que deberan ejecutarse y decrementar el *latch* para permitir el flujo del hilo que queremos que se ejecute despúes de cumplida la funcionalidad de los hilos a esperar:
```[java]
CountDownLatch latch = new CountDownLatch(NUM_HILOS);
```
2. Especificar el punto donde queremos que se espere a que el *latch* se decremente a cero y se pueda pasar, para este caso es el hilo principal, donde luego de obtener el valor del promedio de cada hilo se procede al calculo final:
```[java] 
latch.await();
```

3. En nuestra tarea, sea un **Runnable** o un **Callable<T>**, luego de realizar el trabajo que requerimos decrementamos el *latch*:
```[java] 
latch.countDown();
```