1、When designing thread-safe classes,good object-oriented techniques -encapsulation, immutability and 
    
   clear specification of invariants - are your best friends.

2、 Thread-safe classes encapsulate any needed synchronization so that clients need not provide their own.

3、 Stateless objects are always thread-safe.

4、 To ensure thread safety ,check-then-act operations (like lazy initialization) and read-modify-write operations (like count++) must always be atomic.

5、 where practical, use exiting thread-safe objects, like AtomicLong,to manage your class's state.

6、 Every shared ,mutable variable should be guarded by exactly one lock. Make it clear to maintainers which lock that is.

7、 Avoid holding locks during lengthy computations or operations at risk of not completing quickly such as network or console I/O.