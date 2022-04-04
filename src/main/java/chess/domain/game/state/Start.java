package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.board.coordinate.Coordinate;

public class Start implements State {

    private final Board board;

    public Start() {
        this.board = Board.create();
    }

    @Override
    public State start() {
        return new WhiteTurn(getBoard());
    }

    @Override
    public State move(Coordinate from, Coordinate to) {
        throw new IllegalStateException("게임을 시작하지 않았습니다.");
    }

    @Override
    public State end() {
        throw new IllegalStateException("게임을 시작하지 않았습니다.");
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public Board getBoard() {
        return board;
    }
}
