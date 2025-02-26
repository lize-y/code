package MyThreadPool;

import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 实现 线程池原理：
 *      核心线程队列、任务阻塞队列、辅助线程队列、拒绝策略
 * @see <a href="https://github.com/implement-study/thread_pool_demo">https://github.com/implement-study/thread_pool_demo</a>
 **/

public class Main {

    public static void main(String[] args) throws InterruptedException {
        MyThreadPool myThreadPool = new MyThreadPool(4, 6, 1000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(4), new MyThreadPool.DefaultRejectHandle());
        for (int i = 0; i < 10; i++){
            final int finalI = i;
            myThreadPool.execute(()->{
                //模拟运行操作
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + " end " + finalI);
            });
        }
    }
}
