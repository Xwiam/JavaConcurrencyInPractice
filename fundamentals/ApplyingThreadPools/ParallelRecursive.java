package com.concurrency.fundamental.applyingthreadpools;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * @author xiwam
 * @Date 2019/2/13 10:45
 * @Desc
 */
public class ParallelRecursive {

    public<T> void parallelRecursive(final Executor exec,  List<Node<T>> list, final Collection<T> result) {
        for (final Node<T> node : list) {
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    result.add(node.getValue());
                }
            });
            parallelRecursive(exec, node.getChildren(), result);
        }
    }

    public<T> void sequentialRecursive(final Executor exec, List<Node<T>> list, final Collection<T> result) {
        for (Node<T> node : list) {
            result.add(node.getValue());
            sequentialRecursive(exec, node.getChildren(), result);
        }
    }
}

class Node<T> {
    private T value;
    private List<Node<T>> child;

    public Node(T i) {
        value = i;
    }
    public List<Node<T>> getChildren() {
        return child;
    }

    public void setChildren(List<Node<T>> child) {
        this.child = child;
    }

    public T getValue() {
        return value;
    }
}
