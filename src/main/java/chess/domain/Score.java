package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Score {

    enum scoreCalculator {
        QUEEN(PieceType.QUEEN, 9),
        ROOK(PieceType.ROOK, 5),
        BISHOP(PieceType.BISHOP, 3),
        KNIGHT(PieceType.KNIGHT, 2.5),
        KING(PieceType.KING, 0),
        PAWN(PieceType.PAWN, 1);

        private final PieceType pieceType;
        private final double score;

        scoreCalculator(PieceType pieceType, double score) {
            this.pieceType = pieceType;
            this.score = score;
        }

        static double getScoreByPieceType(PieceType pieceType) {
            return Arrays.stream(values())
                    .filter(value -> value.pieceType == pieceType)
                    .findFirst()
                    .map(value -> value.score)
                    .orElse((double) 0);
        }
    }

    public static double calculateScore(Board board, Team team) {
        Map<Position, Piece> rawBoard = board.getBoard();
        return rawBoard.values().stream()
                .filter(piece -> piece.isSameTeam(team))
                .mapToDouble(value -> score(board, value))
                .sum();
    }

    private static double score(Board board, Piece piece) {
        double basicScore = scoreCalculator.getScoreByPieceType(piece.getType());
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
}
