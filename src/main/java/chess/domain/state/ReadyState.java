package chess.domain.state;

import chess.domain.MoveParameter;
import chess.domain.Turn;
import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;

public class ReadyState implements State {

    private final BoardInitializer boardInitializer;

    public ReadyState(BoardInitializer boardInitializer) {
        this.boardInitializer = boardInitializer;
    }

    @Override
    public State start() {
        return new RunningState(Board.of(boardInitializer));
    }

    @Override
    public State move(MoveParameter moveParameter, Turn turn) {
        throw new UnsupportedOperationException("게임이 시작되지 않았습니다.");
    }

    @Override
    public State end() {
        throw new UnsupportedOperationException("아직 게임이 시작되지 않았습니다.");
    }

    @Override
    public Board getBoard() {
        throw new UnsupportedOperationException();
    }
}
