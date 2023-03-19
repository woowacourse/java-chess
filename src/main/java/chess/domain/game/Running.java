package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.board.Squares;
import chess.domain.piece.property.Color;

import java.util.List;

public final class Running implements GameStatus {

    private final Board board;
    private final Color turn;

    public Running(final Board board, final Color turn) {
        this.board = board;
        this.turn = turn;
    }

    @Override
    public GameStatus start() {
        throw new UnsupportedOperationException("게임을 시작한 후에 다시 시작할 수 없습니다.");
    }

    @Override
    public GameStatus playTurn(final Position source, final Position target) {
        board.confirmMove(source, target, turn);
        return new Running(board, changeTurn());
    }

    @Override
    public GameStatus end() {
        return new Finished();
    }

    @Override
    public boolean isOnGoing() {
        return true;
    }

    private Color changeTurn() {
        if (turn.isBlack()) {
            return Color.WHITE;
        }
        return Color.BLACK;
    }

    public List<Squares> getBoard() {
        return board.getSquares();
    }
}
