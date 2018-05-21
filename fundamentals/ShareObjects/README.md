1、synchronized is not only about atomicity ,but also has another significant aspect:memory visibility.

2、Reading data without synchronization is analogous to using the READ_UNCOMMITED isolation level in a database

3、locking is not just about mutual exclusion but also about memory visibility.

4、Volatile variables are not cached in registers or in caches where they are hidden from other processors,

so a read of volatile variable always returns the most recent write by any thread.

5、accessing a volatile variable performs no locking and so cannot cause the execusing thread to block,

making volatile variables a lighter-weight synchronization mechanism than synchronized.

6、when thread A writes to a volatile variable and subsequently thread B reads that same variable,the values of all variables that were visible to A

prior to writing to the volatile variable become visible to B after reading the volatile variable.So from a memory visibility perspective,

writing a volatile variable is like exiting a synchronized block and reading a volatile variable is like entering a synchronized block.

7、Locking can guarantee both visibility and atomicity;volatile variables can only guarantee visibility.

8、You can use volatile variables only when writing to the variable do not depend on its current value.

9、The language and core libraries of java provide mechanisms that can help in maintaining thread confinement --local variables and the ThreadLocal class.

10、Immutable objects can be used safely by any thread without additional synchronization,even when synchronization is not used to publish them.

11、Static initializers are executed by the JVM at class initialization time;because of internal synchronization in the JVM,this mechanism is guaranteed

to safely publish any objects initialized in this way.