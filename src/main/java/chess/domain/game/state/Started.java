package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.piece.PieceColor;

public abstract class Started implements State {

    protected final Board board;

    protected Started(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    @Override
    public double calculateBlackScore() {
        return board.calculateScore(PieceColor.BLACK);
    }

    @Override
    public double calculateWhiteScore() {
        return board.calculateScore(PieceColor.WHITE);
    }
}
