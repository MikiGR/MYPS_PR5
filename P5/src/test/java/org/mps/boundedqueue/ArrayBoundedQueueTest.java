package org.mps.boundedqueue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.Iterator;

public class ArrayBoundedQueueTest {

    @Nested
    class ArrayBoundedQueueConstructor {
        @Test
        @DisplayName("Si intento crear un ArrayBoundedQueue con capacidad -1 lanza IllegalArgumentException")
        public void ArrayBoundedQueue_capacityIsNegative_throwIllegalArgumentException() {
            assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> 
            new ArrayBoundedQueue<>(-1));
        }

        @Test
        @DisplayName("Si intento crear un ArrayBoundedQueue con capacidad positiva se crea correctamente")
        public void ArrayBoundedQueue_allCorrect_returnTrue() {
            ArrayBoundedQueue<Integer> abq = new ArrayBoundedQueue<>(1);
            assertThat(abq.size()).isEqualTo(0);
        }
    }

    @Nested
    class Put {
        @Test
        @DisplayName("Si intento hacer put de un elemento nulo lanza IllegalArgumentException")
        public void put_NullValue_throwIllegalArgumentException() {
            ArrayBoundedQueue<Integer> abq = new ArrayBoundedQueue<>(3);
            assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> abq.put(null));
        }

        @Test
        @DisplayName("Si intento hacer put y está lleno lanza FullBoundQueueException")
        public void put_isFull_throwFullBoundQueueException() {
            ArrayBoundedQueue<Integer> abq = new ArrayBoundedQueue<>(1);            
            abq.put(1);
            assertThatExceptionOfType(FullBoundedQueueException.class).isThrownBy(() -> abq.put(2));
        }

        @Test
        @DisplayName("Si intento hacer put y hay hueco se introduce correctamente")
        public void put_allCorrect_returnTrue() {
            ArrayBoundedQueue<Integer> abq = new ArrayBoundedQueue<>(1);
            abq.put(1);
            assertThat(abq.get()).isEqualTo(1);
        }
    }

    @Nested
    class get {
        @DisplayName("Si hacemos get de una lista vacía lanza una excepcion")
        @Test
        public void get_EmptyQueue_throwException(){
            ArrayBoundedQueue<Integer> abq = new ArrayBoundedQueue<>(1);

            assertThatThrownBy(() -> {abq.get();}).isInstanceOf(EmptyBoundedQueueException.class);
        }

        @DisplayName("Si hacemos get del primer elemento de una lista de dos elementos lo devuelve correctamente. ")
        @Test
        public void get_TwoElementsQueueFirstElement_returnTrue(){
            ArrayBoundedQueue<Integer> abq = new ArrayBoundedQueue<>(3);
            abq.put(1);
            abq.put(2);
            assertThat(abq.get())
            .isEqualTo(1);
        }

        @DisplayName("Si hacemos get del segundo elemento de una lista de dos elementos lo devuelve correctamente. ")
        @Test
        public void get_TwoElementsQueueSecondElement_returnTrue(){
            ArrayBoundedQueue<Integer> abq = new ArrayBoundedQueue<>(3);
            abq.put(1);
            abq.put(2);
            abq.get();
            assertThat(abq.get())
            .isEqualTo(2);
        }
    } 

    @Nested
    class isFull {
        @DisplayName("Si preguntamos si esta lleno en una cola llena devuelve true")
        @Test
        public void isFull_colaLlena_returnTrue() {
            ArrayBoundedQueue<Integer> abq = new ArrayBoundedQueue<>(3);
            abq.put(1);
            abq.put(2);
            abq.put(3);
            assertThat(abq.isFull()).isTrue();
        }

        @DisplayName("Si preguntamos si esta lleno en una cola vacia devuelve false")
        @Test
        public void isFull_colaVacia_returnFalse() {
            ArrayBoundedQueue<Integer> abq = new ArrayBoundedQueue<>(3);
            assertThat(abq.isFull()).isFalse();
        }
    }

    @Nested
    class isEmpty {
        @DisplayName("Si preguntamos si esta vacio en una cola vacia devuelve true")
        @Test
        public void isEmpty_colaVacia_returnTrue() {
            ArrayBoundedQueue<Integer> abq = new ArrayBoundedQueue<>(3);
            abq.put(1);
            abq.put(2);
            abq.put(3);
            assertThat(abq.isFull()).isTrue();
        }

        @DisplayName("Si preguntamos si esta lleno en una cola vacia devuelve false")
        @Test
        public void isEmpty_colaLlena_returnTrue() {
            ArrayBoundedQueue<Integer> abq = new ArrayBoundedQueue<>(3);
            assertThat(abq.isFull()).isFalse();
        }
    }

    @Nested
    class size {
        @Test
        @DisplayName("Si llamo a size de una ArrayBoundedQueue de tamaño 1 devuelve 1")
        public void size_onlyOneElement_returnTrue() {
            ArrayBoundedQueue<Integer> abq = new ArrayBoundedQueue<>(1);
            abq.put(1);
            assertThat(abq.size()).isEqualTo(1);
        }
    }

    @Nested
    class getFirst {
        @Test
        @DisplayName("Si llamo a getFirst de una ArrayBoundedQueue recien creado devuelve 0")
        public void getFirst_oneElement_returnTrue() {
            ArrayBoundedQueue<Integer> abq = new ArrayBoundedQueue<>(1);
            assertThat(abq.getFirst()).isEqualTo(0);
        }
    }

    @Nested
    class getLast {
        @Test
        @DisplayName("Si llamo a getLast de una ArrayBoundedQueue con un elemento devuelve 1")
        public void getLast_oneElement_returnTrue() {
            ArrayBoundedQueue<Integer> abq = new ArrayBoundedQueue<>(2);
            abq.put(1);
            assertThat(abq.getLast()).isEqualTo(1);

        }
    }

    @Nested
    class iterator {
        @DisplayName("Si iteramos en una cola de tres elementos con un next, devuelve el primero. ")
        @Test
        public void iterator_colaTresElementos_returnTrue() {
            ArrayBoundedQueue<Integer> abq = new ArrayBoundedQueue<>(3);
            abq.put(1);
            abq.put(2);
            abq.put(3);
            Iterator<Integer> it = abq.iterator();
            assertThat(it.next().intValue()).isEqualTo(1);
        }

        @DisplayName("Si intentamos iterar en una cola vacía salta una excepción")
        @Test
        public void iterator_colaVacia_throwException() {
            ArrayBoundedQueue<Integer> abq = new ArrayBoundedQueue<>(1);
   
            Iterator<Integer> it = abq.iterator();
            assertThatThrownBy(() -> {it.next();});
        }

        @DisplayName("Si intentamos iterar en una de un elemento devuelve el correcto. ")
        @Test
        public void iterator_colaUnElemento_returnTrue() {
            ArrayBoundedQueue<Integer> abq = new ArrayBoundedQueue<>(1);
            abq.put(1);
            Iterator<Integer> it = abq.iterator();
            assertThat(it.next()).isEqualTo(1);
        }

        @DisplayName("Si intentamos iterar en una cola de dos elementos , devuelve el correcto.")
        @Test
        public void iterator_colaDosElementos_returnTrue() {
            ArrayBoundedQueue<Integer> abq = new ArrayBoundedQueue<>(2);
            abq.put(1);
            Iterator<Integer> it = abq.iterator();
            abq.put(2);
            it.next();
            assertThat(it.next()).isEqualTo(2);
        }

        @DisplayName("Si intentamos iterar en una cola de un elemento con hasNext devuelve false.")
        @Test
        public void iterator_colaUnElementoHasNext_returnFalse() {
            ArrayBoundedQueue<Integer> abq = new ArrayBoundedQueue<>(2);
            abq.put(1);
            Iterator<Integer> it = abq.iterator();
            it.next();
            assertThat(it.hasNext()).isFalse();
        }
    }

}
