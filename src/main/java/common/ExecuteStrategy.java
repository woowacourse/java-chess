package common;

@FunctionalInterface
public interface ExecuteStrategy<T> {

    T execute();
}
