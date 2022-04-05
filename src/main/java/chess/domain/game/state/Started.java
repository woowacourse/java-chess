package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.position.Position;

public class Started implements State {

    private final Color turn;

    public Started(final Color turn) {
        this.turn = turn;
    }

    @Override
    public State start() {
        throw new IllegalStateException("[ERROR] 이미 start를 하여 다시 start를 할 수 없습니다.");
    }

    @Override
    public State end() {
        return new Ended();
    }

    @Override
    public State move(final Board board, final Position from, final Position to) {
        board.isMovable(from, to, turn);

        if (board.isGameOver(to)) {
            board.move(from, to);
            return new Ended();
        }

        board.move(from, to);
        return new Started(turn.getOpposite());
    }

    @Override
    public State status() {
        return new Started(turn);
    }

    @Override
    public boolean isNotEnded() {
        return true;
    }

    @Override
    public boolean isStarted() {
        return true;
    }

    @Override
    public Color getTurn() {
        return turn;
    }
}
