import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Создаю потоки...");

        MyCallable call1 = new MyCallable("call1", 2);
        call1.setName("call 1");
        MyCallable call2 = new MyCallable("call2", 3);
        call2.setName("call 2");
        MyCallable call3 = new MyCallable("call3", 4);
        call3.setName("call 3");
        MyCallable call4 = new MyCallable("call4", 5);
        call4.setName("call 4");

        ExecutorService threadPool = Executors.newFixedThreadPool(4);

        caseAll(call1, call2, call3, call4, threadPool);

        System.out.println("--------------------------------------");

        caseAny(call1, call2, call3, call4, threadPool);

        threadPool.shutdown();

    }

    private static void caseAny(MyCallable call1, MyCallable call2, MyCallable call3, MyCallable call4, ExecutorService threadPool) throws InterruptedException, ExecutionException {
        Integer res = threadPool.invokeAny(new ArrayList<Callable<Integer>>(){{
            add(call1);
            add(call2);
            add(call3);
            add(call4);
        }});

        Thread.sleep(5000);

        System.out.println("One call's greetings were printed " + res + " times");
    }

    private static void caseAll(MyCallable call1, MyCallable call2, MyCallable call3, MyCallable call4, ExecutorService threadPool) throws InterruptedException, ExecutionException {
        List<Future<Integer>> futures = threadPool.invokeAll(new ArrayList<Callable<Integer>>(){{
            add(call1);
            add(call2);
            add(call3);
            add(call4);
        }});

        Thread.sleep(5000);

        System.out.println(call1.getName() + " greetings were printed " + futures.get(0).get() + " times");
        System.out.println(call2.getName() + " greetings were printed " + futures.get(1).get() + " times");
        System.out.println(call3.getName() + " greetings were printed " + futures.get(2).get() + " times");
        System.out.println(call4.getName() + " greetings were printed " + futures.get(3).get() + " times");
    }
}
