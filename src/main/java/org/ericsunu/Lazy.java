package org.ericsunu;

import java.util.function.Supplier;

public class Lazy<T> {
    private Supplier<T> initializer;
    private volatile T value;

    public Lazy(Supplier<T> initializer) {
        this.initializer = initializer;
    }

    public T get() {
        // First check without locking (fast path)
        T result = value;
        if (result == null) {
            synchronized (this) {
                result = value;
                if (result == null) {
                    result = initializer.get();
                    value = result;
                    // Once initialized, clear the initializer for GC.
                    initializer = null;
                }
            }
        }
        return result;
    }
}
