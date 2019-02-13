1、thread starvation deadlock can occur whenever a pool task initiates an unbounded blocking wait for some resouce or condition that can succeed only through the 

action of another pool task,such as waiting for the return value or side effect of another task,unless you can guarantee that the large is large enough.

2、If tasks that depend on other tasks execute in a thread pool,they can deadlock.Whenever you submit to an Executor tasks that are not independent,be aware of the 

possibility of thread starvation deadlock.

3、To size a thread pool properly,you need to understand your computing environment,your resource budget,and the nature of your tasks.How many processors does the 

deployment system have?How much memory?Do tasks perform mostly computation,I/O or some combination?Do they requires a scarce resouce,such as a JDBC connection?If you

have different categories of tasks with very different behaviors,consider using multiple thread pools so each can be tuned according to its workload.

4、For compute-intensive tasks,an Ncpu-processor system usually achieves optimum utilization with a thread pool of Ncpu+1 threads.(because Even compute-intensive threads

occasionally take a page fault or pause for some other reason,so an "extra" runnable thread prevents CPU cycles from going unused when this happens.)For tasks that also

include I/O or other blocking operations,you want a larger pool,since not all of the threads will be schedulable at all times.In order to size the pool properly,you must 

estimate the ratio of waiting time to compute time for your tasks.Nthreads = Ncpu*Ucpu*(1+W/C)

5、The core pool size,maximum pool size,and keep-alive time govern thread creation and teardown.The core size is the target size;the implementation attempts to maintain the

pool at the size even when there are no tasks to execute,and will not create more threads than this unless the work queue is full.

6、Requests often arrive in bursts even when the average request rate is fairly stable.Queues can help smooth out transient bursts of tasks,but if tasks continue to arrive too 

quickly you wil eventually have to throttle the arrival rate to avoid running out of memory.

7、The default for newFixedThreadPool and newSingleThreadExecutor is to use an unbounded LinkedBlockingQueue.Taks will queue up if all worker threads are busy,but the queue could 

grow without bound if the tasks keep arriving faster than they can be executed.

8、A large queue coupled with a small pool can help reduce memory usage,CPU usage,and context switching,at the cost of potentially constraining throughput.

9、SynchronousQueue is a practical choice only if the pool is unbounded or if rejecting excess tasks is acceptable.The newCachedThreadPool factory uses a SynchronousQueue.

10、Bounding either the thread pool or the work queue is suitable only when tasks are independent.With tasks that depend on other tasks,bounded thread pools or queues can cause thread

starvation deadlock;instead,use an unbounded pool configuration like newCachedThreadPool.An alternative configuration for tasks that submit other tasks and wait for their results is to 

use a bounded thread pool,a SynchronousQueue as the work queue,and the caller-runs saturation policy.