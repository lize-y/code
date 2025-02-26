package MyThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class MyThreadPool {

    private final int coreSize;
    private final int maxSize;
    private final int timeout;
    private final TimeUnit timeUnit;
    private final BlockingQueue<Runnable> blockingQueue;
    private final RejectHandle rejectHandle;


    public MyThreadPool(int coreSize, int maxSize, int timeout, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue, RejectHandle rejectHandle) {
        this.coreSize = coreSize;
        this.maxSize = maxSize;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.blockingQueue = blockingQueue;
        this.rejectHandle = rejectHandle;
    }


    List<Thread> coreList = new ArrayList<>();
    List<Thread> supportList = new ArrayList<>();

    void execute(Runnable runnable) throws InterruptedException {
        if (coreList.size() < coreSize) {
            Thread thread = new CoreThread();
            coreList.add(thread);
            thread.start();
        }
        if (blockingQueue.offer(runnable)){
            return;
        }
        if (supportList.size() < maxSize - coreSize) {
            Thread thread = new SupportThread();
            supportList.add(thread);
            thread.start();
            // 给辅助线程poll的时间
            Thread.sleep(10);
        }
        if (!blockingQueue.offer(runnable)){
            rejectHandle.reject(runnable, this);
        }
    }

    class CoreThread extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    Runnable runnable = blockingQueue.take();
                    runnable.run();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    class SupportThread extends Thread {
        @Override
        public void run() {
            System.out.println("add:"+this.getName());
            while (true) {
                try {
                    Runnable runnable = blockingQueue.poll(timeout, timeUnit);
                    if (runnable == null) {
                        break;
                    }
                    runnable.run();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            supportList.remove(this);
            System.out.println("remove:"+this.getName());
        }
    }

    public interface RejectHandle {
        void reject(Runnable runnable, MyThreadPool myThreadPool);
    }

    public static class DefaultRejectHandle implements RejectHandle {
        @Override
        public void reject(Runnable runnable, MyThreadPool myThreadPool) {
            System.err.println("reject:"+runnable);
        }
    }
}
