package chess;

public interface State {

    State start();

    State move();
}
