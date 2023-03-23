package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.maker.PiecesFactory;
import chess.domain.piece.Piece;

import java.util.List;

public class ChessGame {
    private final Board board;
    private final Color currentTurnColor;

    private ChessGame(final Board board, final Color currentTurnColor) {
        this.board = board;
        this.currentTurnColor = currentTurnColor;
    }

    private ChessGame(final Board board) {
        this(board, Color.WHITE);
    }

    public static ChessGame from(final PiecesFactory piecesFactory) {
        return new ChessGame(Board.from(piecesFactory));
    }

    public ChessGame move(final Position currentPosition, final Position targetPosition) {
        validateTurnColor(currentPosition);
        board.move(currentPosition, targetPosition);
        return new ChessGame(board, currentTurnColor.getOppositeColor());
    }

    private void validateTurnColor(final Position currentPosition) {
        if (!board.isSameColor(currentPosition, currentTurnColor)) {
            throw new IllegalArgumentException("해당 색의 말을 이동시킬 순서가 아닙니다.");
        }
    }

    public boolean isPlaying() {
        return board.hasPieces();
    }

    public boolean isGameOver() {
        return !board.hasTwoKings();
    }

    public List<Piece> getExistingPieces() {
        return board.getPieces();
    }
}
