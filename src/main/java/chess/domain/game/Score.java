package chess.domain.game;

import chess.domain.board.ChessBoard;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceScore;
import chess.domain.position.Column;
import java.util.Arrays;

public class Score {

    private static final double SAME_FILE_PAWN_SCORE = 0.5;
    private static final int MINIMUM_PAWN_COUNT = 1;

    private final double whiteScore;
    private final double blackScore;

    public Score(double whiteScore, double blackScore) {
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
    }

    public static Score from(ChessBoard chessBoard) {
        return new Score(getScore(Color.WHITE, chessBoard), getScore(Color.BLACK, chessBoard));
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    public double getBlackScore() {
        return blackScore;
    }

    public static double getScore(Color color, ChessBoard chessBoard) {
        return getDefaultScore(color, chessBoard) - getDeductionOfPawn(color, chessBoard);
    }

    private static double getDefaultScore(Color color, ChessBoard chessBoard) {
        return chessBoard.getPieces().stream()
            .filter(piece -> piece.isSameColor(color))
            .mapToDouble(piece -> PieceScore.findPieceScore(piece.getClass()))
            .sum();
    }

    private static double getDeductionOfPawn(Color color, ChessBoard chessBoard) {
        return SAME_FILE_PAWN_SCORE * numberOfPawn(color, chessBoard);
    }

    private static long numberOfPawn(Color color, ChessBoard chessBoard) {
        return Arrays.stream(Column.values())
            .mapToLong(column -> numberOfPawnEachFile(color, column, chessBoard))
            .filter(numberOfPawn -> numberOfPawn > MINIMUM_PAWN_COUNT)
            .sum();
    }

    private static long numberOfPawnEachFile(Color color, Column column, ChessBoard chessBoard) {
        return chessBoard.getPieces().stream()
            .filter(piece -> isSameColumnAndSameColorPawn(color, column, piece))
            .count();
    }

    private static boolean isSameColumnAndSameColorPawn(Color color, Column column, Piece piece) {
        return piece.isSameColor(color) && piece.isSameFile(column) && piece.isPawn();
    }
}
