package chess.observer;

public interface Publishable {
    void subscribe(Observable observable);

    void push(Object object);
}
