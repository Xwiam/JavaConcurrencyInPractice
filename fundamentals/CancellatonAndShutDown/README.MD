1、The cooperative approach is required because we rarely want a task,thread,or service to stop immediately,since that could leave shared data structure in an inconsistent state.

The task code itself is usually better able to assess the cleanup required than is the code requesting cancellation.

2、Dealing well with failure,shutdown,and cancellation is one of the characteristic that distinguish a well-behaived application from one that merely works.

3、A task that wants to be cancellable must have a cancellation policy that specifies the "how","when",and "what" of cancellation -how other code can request cancellation,when the task

checks whether cancellation has been requested,and what actions the task takes in response to cancellation request.

4、A task needn't necessarily drop everything when it detects an interruption request - it can choose to postpone it until a more opportune time by remembering that it was interrupted,finishing the task 

it was performing,and then throwing InterruptedException or otherwise indicating interruption.This technique can protect data from corruption when an activity is interrupted in the middle of an update.

5、Only code that implements a thread's interruption policy may swallow an interruption request.General-purpose task and library code should never swallow interruption requests.

6、Activities that do not support cancellation but still call interruptible blocking methods will have to call them in a loop,retrying when interruption is detected.

7、What the handler should do with an uncaught exception depends on your quality-of-service requirements.The most common response is to write an error message and stack trace to the application log.Handlers

can also take more direct action,such as trying to restart the thread,shutting down the application,paging an operator,or other corrective or diagnostic action.In long-running applications,always use uncaught

exceptioin handlers for all threads that at least log the exceptioin.

8、Shutdown hooks can be used for service or application cleanup,such as deleting temporary files or cleaning up resources that are not automatically cleaned up by the OS.



