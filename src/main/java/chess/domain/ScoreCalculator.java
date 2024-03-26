package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;

import java.util.List;
import java.util.Map;

public class ScoreCalculator {
    private final double whiteScore;
    private final double blackScore;

    private ScoreCalculator(double whiteScore, double blackScore) {
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
    }

    public static ScoreCalculator of(Board board) {
        double whiteScore = calculateScore(board, Team.WHITE);
        double blackScore = calculateScore(board, Team.BLACK);
        return new ScoreCalculator(whiteScore, blackScore);
    }

    private static double calculateScore(Board board, Team team) {
        Map<Position, Piece> rawBoard = board.getBoard();
        return rawBoard.values().stream()
                .filter(piece -> piece.isSameTeam(team))
                .mapToDouble(value -> scorePiece(board, value))
                .sum();
    }

    private static double scorePiece(Board board, Piece piece) {
        double basicScore = Score.getScoreByPieceType(piece.getType());

        if (piece.getType() == PieceType.PAWN) {
            basicScore = scorePawn(basicScore, board, piece);
        }

        return basicScore;
    }

    private static double scorePawn(double basicScore, Board board, Piece piece) {
        Position position = piece.getPosition();
        List<Position> otherPositions = position.getVerticalInternalPositions();
        List<Piece> otherPieces = board.getPiecesInVertical(otherPositions);

        if (piece.isSamePieceWithSameTeam(otherPieces)) {
            return basicScore / 2;
        }
        return basicScore;
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    public double getBlackScore() {
        return blackScore;
    }
}
