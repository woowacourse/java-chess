package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.position.Column;
import java.util.EnumMap;
import java.util.Map;

public class Rank {

    private final Map<Column, Piece> pieces;

    public Rank(EnumMap<Column, Piece> pieces) {
        this.pieces = pieces;
    }

    public Map<Column, Piece> getPieces() {
        return pieces;
    }

    public Piece getPiece(Column column) {
        return pieces.get(column);
    }

    public void changePiece(Column column, Piece piece) {
        pieces.put(column, piece);
    }

    public double calculateRankTotalScore(Team team) {
        double totalScore = 0;
        for (Column column : pieces.keySet()) {
            totalScore = plusSameTeamPieceScore(team, totalScore, column);
        }
        return totalScore;
    }

    private double plusSameTeamPieceScore(Team team, double totalScore, Column column) {
        if (pieces.get(column).isSameTeam(team)) {
            totalScore += pieces.get(column).getScore();
        }
        return totalScore;
    }

    public boolean isSameTeamPawn(Team team, Column column) {
        return pieces.get(column).isSameTeam(team) && pieces.get(column).isPawn();
    }
}
