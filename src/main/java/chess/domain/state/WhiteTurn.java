package chess.domain.state;

import chess.domain.*;
import chess.domain.piece.Piece;

import java.util.EnumMap;
import java.util.Map;

public class WhiteTurn extends Playing{

    public WhiteTurn(Map<Row, Rank> board) {
        super(board);
    }

    @Override
    Playing turn() {
        return new BlackTurn(getBoard());
    }

    @Override
    void validateTeam(Piece piece) {
        if (!Team.WHITE.matchTeam(piece.getTeam())) {
            throw new IllegalArgumentException("상대편 말을 옮길 수 없습니다.");
        }
    }

    @Override
    GameState finished() {
        return new Finished(Team.WHITE, getBoard());
    }

    @Override
    public int getTeamScore() {
        double totalScore = 0;
        Map<Column, Integer> pawnNeighbors = new EnumMap<>(Column.class);
        for (Row row : getBoard().keySet()) {
            totalScore += getBoard().get(row).calculateWhiteTotalScore(Team.WHITE);
            for (Column column : Column.values()) {
                if (getBoard().get(row).isPawn(Team.WHITE, column)) {
                    pawnNeighbors.put(column, pawnNeighbors.getOrDefault(column, 0) + 1);
                }
            }
        }

        for (Column col : pawnNeighbors.keySet()) {
            int pawnCount = pawnNeighbors.get(col);
            if (pawnCount > 1) {
                totalScore -= pawnCount * 0.5;
            }
        }
        return (int) totalScore;
    }

    @Override
    public Team getTeam() {
        return Team.WHITE;
    }
}
