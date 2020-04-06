package chess.observer;

public interface Publishable<T> {
    void subscribe(Observable observable);

    void push(T t);
}
