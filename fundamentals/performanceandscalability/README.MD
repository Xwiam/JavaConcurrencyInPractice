1、Improving performance means doing more work with fewer resources.The meaning of resources can vary;for a given activity,some specific resource is usually in

shortest supply,whether it is CPU cycles,memory,network bandwidth,I/O bandwidth,database requests,disk space,or any number of other resources.

2、Some of these(service time,latency) are measures of "how fast"a given unit of work can be processed or acknowledged;others(capacity,throughput) are measures of

"how much" work can be performed with a given quantity of computing resources.

3、the "how much" aspects -scalability,throughput,and capacity- are usually of greater concern for server applications than the "how fast" aspects.

4、Avoid premature optimization.First make it right,then make it fast - if it is not already fast enough.

5、For example,the free perfbar application can give you a good picture of how busy the CPUs are,and since your goal is usually to keep the CPUs busy,this is a very 

good way to evaluate whether you need performance tuning or how effective your tuning has been.

6、Amdahl's Law！！speedup<= 1/(F+(1-F)/N). N！！the count of processors,F！！the fraction of the calculation that must be executed serially.

7、Context switches are not free.thread schedualing requires manipulating shared data structures in the OS and JVM.The OS and JVM use the same CPUs your program does;

more CPU time spent in JVM and OS code means less is avaliable for your program.But OS and JVM activity is not the only cost of context switches.When a new thread is

switched in,the date it needs is unlikely to be in the local processor cache,so a context switch causes a flurry or cache misses,and thus threads run a little more slowly 

when they are first scheduled.

8、Uncontended synchronization can be handled entirely within the JVM;contended synchronization may require OS activity,which adds to the cost.When locking is contended,

the losing threads must block.The JVM can implement blocking either via spin-waiting(repeated trying to acquire the lock until it succeeds) or by suspending the blocked thread

through the operating system.spin-waiting is preferable for shorts waits and suspension is preferable for long waits.

9、There are three ways to reduce lock contention:Reduce the duration for which locks are held;Reduce the frequency with which locks are requested;or Replace exclusive locks 

with coordination mechanisms that permit greater concurrency.

10、To use vmstat、mpstat、iostat to monitor the cpu and io utilization.If the CPUs are not fully utilized,there are serveral likely causes: Insuficient load,I/O bound,Externally bound,Lock contention.

11、By moving all the logging I/O to a single thread,we also eliminate the chance of contention for the output stream and thus eliminate a source of blocking.This improves overall throughput because

fewer resources are consumed in scheduling,context switching,and lock management.

