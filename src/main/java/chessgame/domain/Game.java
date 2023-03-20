package chessgame.domain;

import chessgame.domain.state.Ready;
import chessgame.domain.state.State;
import chessgame.factory.ChessBoardFactory;

public class Game {
    private final Board board;
    private Team turn = Team.WHITE;
    private State state;
    public Game() {
        this.state = new Ready();
        this.board = new Board(ChessBoardFactory.create());
    }

    public Board board() {
        return board;
    }

    public void setState(Command command) {
        state = state.click(command,board);
    }

    public boolean isEnd() {
        return state.isEnd();
    }
}
