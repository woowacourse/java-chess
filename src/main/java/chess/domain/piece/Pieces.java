package chess.domain.piece;

import chess.domain.Score;
import chess.domain.TeamColor;
import java.util.List;
import java.util.stream.Collectors;

public class Pieces {

    private final List<Piece> pieces;

    public Pieces(final List<Piece> pieces) {
        this.pieces = pieces;
    }

    public Score calculateScoreOfTeam(final TeamColor color) {
        List<Piece> currentTeamPieces = findCurrentTeamPieces(color);
        Score sum = Score.INITIAL_SCORE;
        for (Piece piece : currentTeamPieces) {
            sum = sum.add(piece.getScore());
        }
        return sum.reducePawnScore(calculatePawnCount(currentTeamPieces));
    }

    private List<Piece> findCurrentTeamPieces(final TeamColor color) {
        return pieces.stream()
            .filter(piece -> piece.isSameColor(color))
            .collect(Collectors.toList());
    }

    private long calculatePawnCount(final List<Piece> currentTeamPieces) {
        return currentTeamPieces.stream()
            .filter(Piece::isPawn)
            .count();
    }

}
