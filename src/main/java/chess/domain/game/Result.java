package chess.domain.game;

import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import java.util.EnumMap;
import java.util.Map;
import java.util.Map.Entry;

public class Result {

    private static final int PAWN_COUNT = 1;

    private final double blackScore;
    private final double whiteScore;
    private final WinningTeam winningTeam;

    public Result(final Map<Position, Piece> board, final Team teamOfDeadKing) {
        this.blackScore = calculateScore(board, Team.BLACK);
        this.whiteScore = calculateScore(board, Team.WHITE);
        this.winningTeam = new WinningTeam(blackScore, whiteScore, teamOfDeadKing);
    }

    public double calculateScore(final Map<Position, Piece> board, final Team team) {
        return calculateFirstLinePieces(board, team) + calculatePawn(board, team);
    }

    private double calculateFirstLinePieces(final Map<Position, Piece> board, final Team team) {
        return board.values().stream()
                .filter(piece -> piece.getTeam() == team)
                .filter(piece -> !piece.isPawn())
                .mapToDouble(Piece::getPoint)
                .sum();
    }

    private double calculatePawn(final Map<Position, Piece> board, final Team team) {
        Map<Column, Integer> pawnCount = new EnumMap<>(Column.class);
        for (final Entry<Position, Piece> boardEntry : board.entrySet()) {
            putPawnCount(team, pawnCount, boardEntry);
        }
        return pawnCount.values().stream()
                .mapToDouble(this::adjustPawnPoint)
                .sum();
    }

    private void putPawnCount(final Team team,
                              final Map<Column, Integer> pawnCount,
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

    public double getBlackScore() {
        return blackScore;
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    public WinningTeam getWinningTeam() {
        return winningTeam;
    }
}
