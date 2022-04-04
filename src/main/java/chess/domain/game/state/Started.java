package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.position.Position;

public class Started implements State {

    private final Color turn;
    private final Board board;

    public Started(final Color turn, final Board board) {
        this.turn = turn;
        this.board = board;
    }

    @Override
    public State start() {
        throw new IllegalStateException("[ERROR] 이미 start를 하여 다시 start를 할 수 없습니다.");
    }

    @Override
    public State end() {
        return new Ended(board);
    }

    @Override
    public State move(final Position from, final Position to) {
        board.isMovable(from, to, turn);

        if (board.isCheckmate(to)) {
            board.move(from, to);
            return new Ended(board);
        }

        board.move(from, to);
        return new Started(turn.getOpposite(), board);
    }

    @Override
    public State status() {
        return new Started(turn, board);
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
