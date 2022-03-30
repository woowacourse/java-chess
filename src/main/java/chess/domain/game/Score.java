package chess.domain.game;

import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.piece.Team;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import java.util.EnumMap;
import java.util.Map;
import java.util.Map.Entry;

public class Score {

    private static final int PAWN_COUNT = 1;

    private final Map<Position, Piece> board;

    public Score(final Map<Position, Piece> board) {
        this.board = board;
    }

    public double calculateScore(final Team team) {
        return calculateFirstLinePieces(team) + calculatePawn(team);
    }

    private double calculateFirstLinePieces(final Team team) {
        return board.values().stream()
                .filter(piece -> piece.getTeam() == team)
                .filter(piece -> !piece.isPawn())
                .mapToDouble(Piece::getPoint)
                .sum();
    }

    private double calculatePawn(final Team team) {
        Map<Column, Integer> pawnCount = new EnumMap<>(Column.class);
        for (final Entry<Position, Piece> boardEntry : board.entrySet()) {
            putPawnCount(team, pawnCount, boardEntry);
        }

        return pawnCount.values().stream()
                .mapToDouble(this::adjustPawnPoint)
                .sum();
    }

    private void putPawnCount(final Team team, final Map<Column, Integer> pawnCount,
                              final Entry<Position, Piece> boardEntry) {
        Piece piece = boardEntry.getValue();
        if (piece.isPawn() && piece.getTeam() == team) {
            Column column = boardEntry.getKey().getColumn();
            pawnCount.put(column, pawnCount.getOrDefault(column, 0) + PAWN_COUNT);
        }
    }

    private double adjustPawnPoint(final int count) {
        if (count > PAWN_COUNT) {
            return count * Pawn.PENALTY_POINT;
        }
        return count;
    }

    public Team calculateWinningTeam(final double whiteScore, final double blackScore) {
        if (whiteScore > blackScore) {
            return Team.WHITE;
        }
        return Team.BLACK;
    }
}
