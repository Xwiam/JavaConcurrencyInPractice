1、The iterators returned by the synchronized collections are not designed to deal with concurrent modification, and they are fail-fast --meaning that

if the they detect that the collection has changed since iteration began,they throw the unchecked ConcurrentMOdification.

2、Replacing synchronized collections with concurrent collections can offer dramatic scalability improvements with little risk.

3、The iterators returned by ConcurrentHashMap are weakly consistent instead of fail-fast.A weakly consistent iterator can tolerate concurrent modification,traverses elements 

as they existed when the iterator was constructed,and may(but is not guaranteed to) reflect modifications to the collection after the construction of the iterator.

4、Because it has so many advantages and so few disadvantages compared to HashTable or synchronizedMap,replacing synchronized Map implementions with ConcurrentHashMap in most

cases results only in better scalability.Only if your application needs to lock the map for exclusive acces is ConcurrentHashMap not an appropriate drop-in replacement.

5、The iterators returned by the copy-on-write collections do not throw ConcurrentMOdification and return the elements exactly as they were at the time the iterator 

was created,regardless of subsequent modifications.

6、The producer-consumer pattern simplifies development because it removes code dependencies between producer and consumer classes,and simplifies workload management by decoupling 

activities that may produce or consume data at different or variable rates.BlockingQueue simplifies the implemention of producer-consumer design with any number of producers and consumers.

7、Blocking queues also provide an offer method,which returns a failure status if the item cannot be enqueued.This enables you to create more flexible policies for dealing with overload,

such as shedding load,serializing excess work items and writing them to disk,reducing the number of producer threads,or throttlign producers in some other manner.

8、Bounded queues are a powerful resource management tool for building reliable applications.they make your program more robust to overload by throttling activities that threaten to produce

more work than can be handled.

9、Synchronous queues are generally suitable only when there are enough consumers thar there nearly always will be one ready to take the handoff.

10、Just as blocking queues lend themselves to the producer-consumer pattern,deques lend themselves to a related pattern called work stealing.A producer-consumer design has one shared work queue

for all consumers;In a work stealing design,every consumer has its own deque.If a consumer exhausts the work in its own deque,it can steal work from the tail of somenone else's deque.Work stealing is 

well suited to problems in which consumers are also producers -- when performing a unit of work is likely to result in the identification of more work.

11、When a method can throw InterruptedException,it is telling you that it is a blocking method,and further that if it is interrupted,it will make an effort to stop blocking early.There are bascially two

choices to respond to interruption---Propagate the InterruptedException and restore the interrupt.

12、Latches are for waiting for events;barriers are for waiting for other threads.

13、Make fields final unless they need to be mutable.
