package chess.domain.state;

public interface State {

    State start();
    State end();
    State changeTurn();
}
