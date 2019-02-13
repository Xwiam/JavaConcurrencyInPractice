1、A program will be free of lock-ordering deadlocks if all threads acquire the locks they need in a fixed global order.

2、Calling a method with no locks held is called an open call,and classes that rely on open calls are more well-behaved and composable 

than classes that make calls with lock held.Using open calls to avoid deadlock is analogous to using encapsulation to provide thread safety.

3、When a timed lock attempt fails,you do not necessarily know why.Maybe there was a deadlock.Still,at least you have the opportunity to record

that your attempt failed,log any useful information about what you were trying to do,and restart the computation somewhat more gracefully than

killing the entire process.

4、If a lock acquisition times out,you can release the locks,back off and wait for a while,and try again,possibly clearing the deadlock condition

and allowing the program to recover.(This technology works only when the two locks are acquired together;if multiple locks are acquired due to 

the nesting of method calls,you cannot just release the outer lock,even if you know you hold it.

5、Avoid the temptation to use thread priorities,since they increase platform dependence and can cause liveness problems.Most concurrent applications

can use the default priority for all threads.

6、Livelock is a form of liveness failure in which a thread,while not blocked,still cannot make progress because it keeps retrying an operation that 

will always fail.Livelock often occurs in transactional messaging applications,where the messaging infrastructure rolls bak a transaction if a message cannot

be processed successfully,and puts it back at the head of the queue.This form of livelock often comes from overeager error-recovery code that mistakenly treats

an unrecoverable error as a recoverable one.

7、Retrying with random waits and back-offs can be equally effective for avoiding livelock in concurrent applications.