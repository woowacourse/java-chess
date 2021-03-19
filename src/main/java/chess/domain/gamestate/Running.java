package chess.domain.gamestate;

import chess.domain.Turn;
import chess.domain.board.Board;
import chess.domain.board.Point;

public class Running implements GameState {

    private static final IllegalArgumentException EXCEPTION
        = new IllegalArgumentException("올바르지 않은 입력입니다.");

    private final Board board;

    public Running(Board board) {
        this.board = board;
    }

    @Override
    public GameState start() {
        throw EXCEPTION;
    }

    @Override
    public GameState move(Point source, Point destination, Turn turn) {
        if (!board.canMove(source, destination, turn.now())) {
            throw new IllegalArgumentException("불가능한 이동입니다.");
        }
        board.move(source, destination);
        turn.next();
        if (board.isKingDead()) {
            return new Finished();
        }
        return this;
    }

    @Override
    public GameState end() {
        return new Finished();
    }

    @Override
    public GameState status() {
        return this;
    }
}
