package chess.domain.piece;

import chess.domain.TeamColor;
import java.util.List;
import java.util.stream.Collectors;

public class Pieces {

    private static final double PAWN_REDUCE = 0.5;

    private final List<Piece> pieces;

    public Pieces(final List<Piece> pieces) {
        this.pieces = pieces;
    }

    public double calculateScoreOfTeam(final TeamColor color) {
        List<Piece> currentTeamPieces = findCurrentTeamPieces(color);
        return currentTeamPieces.stream()
            .mapToDouble(Piece::getScore)
            .sum() - calculateScoreToReduce(currentTeamPieces);
    }

    private List<Piece> findCurrentTeamPieces(final TeamColor color) {
        return pieces.stream()
            .filter(piece -> piece.isSameColor(color))
            .collect(Collectors.toList());
    }

    private double calculateScoreToReduce(final List<Piece> currentTeamPieces) {
        long pawnCount = currentTeamPieces.stream()
            .filter(Piece::isPawn)
            .count();
        if (pawnCount > 1) {
            return pawnCount * PAWN_REDUCE;
        }
        return 0;
    }

    public int size() {
        return pieces.size();
    }

}
