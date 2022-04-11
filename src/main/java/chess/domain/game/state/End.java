package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.board.coordinate.Coordinate;

public class End implements State {

    private final Board board;

    public End(Board board) {
        this.board = board;
    }

    @Override
    public State start() {
        throw new IllegalStateException("게임이 종료되었습니다.");
    }

    @Override
    public State move(Coordinate from, Coordinate to) {
        throw new IllegalStateException("게임이 종료되었습니다.");
    }

    @Override
    public State end() {
        throw new IllegalStateException("게임이 종료되었습니다.");
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public Board getBoard() {
        return board;
    }
}
