package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.maker.PiecesFactory;
import chess.domain.piece.Piece;

import java.util.List;
import java.util.Map;

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
        board.move(currentTurnColor, currentPosition, targetPosition);
        return new ChessGame(board, currentTurnColor.getOppositeColor());
    }

    public boolean isPlaying() {
        return board.hasPieces();
    }

    public boolean isGameOver() {
        return !board.hasTwoKings();
    }

    public Map<Color, Double> calculateScoreByColor() {
        return board.calculateScoreByColor();
    }

    public List<Piece> getExistingPieces() {
        return board.getPieces();
    }
}
