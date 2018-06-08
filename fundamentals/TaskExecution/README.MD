1、Having many idle threads can tie up a lot of memory,putting pressure on the garbage collector,and having many threads competing for the CPUs can impose other performance costs as well.

if you have enough threads to keep all the CPUs busy,creating more threads won't help and may even hurt.

2、the way to stay out of OutOfMemoryError is to place some bound on how many threads your applications creates and to test your application thoroughly to ensure that,even when this bound 

is reached,it does not run out of resources.

3、By limiting the number of concurrent tasks,you can ensure that the application does not fail due to resource exhaustion or suffer performance problems due to contention for scarce resources.