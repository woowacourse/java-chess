package refactorChess.domain.game;

import java.util.Map;
import java.util.Objects;
import refactorChess.domain.board.ChessBoard;
import refactorChess.domain.board.Position;
import refactorChess.domain.piece.Piece;
import refactorChess.domain.piece.PieceColor;

public class Score {

    private static final double DECREASE_PAWN_SCORE = 0.5;

    private final double score;

    public Score(double score) {
        this.score = score;
    }

    public static Score calculateScore(ChessBoard chessBoard, PieceColor pieceColor) {
        return new Score(getDefaultScore(chessBoard, pieceColor) - getSameLinePawnScore(chessBoard, pieceColor));
    }

    private static double getDefaultScore(ChessBoard chessBoard, PieceColor pieceColor) {
        return chessBoard.getBoard().entrySet().stream()
                .filter(entry -> isSameColor(chessBoard, entry.getKey(), pieceColor))
                .mapToDouble(pieces -> pieces.getValue().getPieceType().getScore())
                .sum();
    }

    private static boolean isSameColor(ChessBoard chessBoard, Position position, PieceColor pieceColor) {
        return chessBoard.findByPiece(position).getPieceColor() == pieceColor;
    }

    private static double getSameLinePawnScore(ChessBoard chessBoard, PieceColor pieceColor) {
        final Map<Position, Piece> board = chessBoard.getBoard();
        final long sameLinePawnCount = board.entrySet().stream()
                .filter(entry -> isSamePawnAndColor(entry.getValue(), pieceColor))
                .filter(entry -> isSameColorPawnInColumn(board, entry.getKey(), pieceColor))
                .count();

        return sameLinePawnCount * DECREASE_PAWN_SCORE;
    }

    private static boolean isSameColorPawnInColumn(Map<Position, Piece> board,
                                                   Position position,
                                                   PieceColor pieceColor) {
        return board.entrySet().stream()
                .filter(positionArticle -> isSamePawnAndColor(positionArticle.getValue(), pieceColor))
                .anyMatch(positionArticle -> isExistOtherPawnInColumn(position, positionArticle.getKey()));
    }

    public static boolean isSamePawnAndColor(Piece piece, PieceColor pieceColor) {
        return piece.isPawn() && piece.getPieceColor() == pieceColor;
    }

    private static boolean isExistOtherPawnInColumn(Position position, Position otherPosition) {
        return !position.equals(otherPosition) && position.isSameColumn(otherPosition);
    }

    public boolean isOverScore(Score score) {
        return this.score > score.score;
    }

    public double getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Score)) {
            return false;
        }
        Score score1 = (Score) o;
        return Double.compare(score1.score, score) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }
}
