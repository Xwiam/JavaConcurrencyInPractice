1、Encapsulation makes it possible to determine that a class is thread-safe without having to examine the entire program.

2、You cannot ensure thread safety without understanding an object's invariants and post-conditions.Constraints on the valid values

or state transitions for state variables can create atomicity and encapsulation requirements.

3、Collection classes often exhibit a form of "split ownership",in which the collection owns the state of the collection infrastructure,

but client code owns the objects stored in the collection.An example is ServletContext.Servlets need not use synchronization when calling

setAttribute and getAttribute,but they may have to use synchronization when using the objects stored in the ServletContext.

4、Encapsulating data within an object confines access to the data to the object's methods,making it easier to ensure that the data is

always accessed with the appropriate lock held.

5、An object following the Java monitor pattern encapsulates all its mutable state and guards it with the object's own intrinstic lock.

6、There are advantages to using a private lock object instead of an object's intrinstic lock(or any other publicly accessible lock).Making the 

lock object private encapsulates the lock so that client code cannot require it,whereas a publicly accessible lock allows client code to participate 

in its synchronization policy-correctly or incorrectly.

7、if a class is composed of multiple independent thread-safe variables and has no operations that hava any invalid state transitions,then it can delegate 

thread safety to the underlying state variables.

8、if a state variable is thread-safe,does not participate in any invariants that constrain its value,and has no prohibited state transitions for any of its

operations,then it can safely be published.

9、If extending a class to add another atomic operation is fragile because it distributes the locking code for a class over multiple classes in an object hierarchy,

client-side locking is even more fragile because it entails putting locking code for class C into classes that are totally unrelated to C.

10、Client-side locking has a lot in common with class extension-they both couple the behavior of the derived class to the implementation of the base class.Just as 

extension violates encapsulation of implementation,client-side locking violates encapsulation of synchronization policy.There is a less fragile alternative for adding an

atomic operation to an existing class:compostion.Like COllections.synchronizedList and other collections wrappers,ImprovedList assumes that once a list is passed to its 

constructor,the client will not use the underlying list directly again,accesing it only through the ImprovedList.

