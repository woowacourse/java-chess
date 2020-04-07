package chess.domain.observer;

public interface Publishable {
    void subscribe(Observable observable);

    void push(Object object);
}
