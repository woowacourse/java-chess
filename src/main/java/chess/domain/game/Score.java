package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.Col;
import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.domain.pieces.Piece;
import chess.domain.pieces.PieceType;
import chess.domain.pieces.Team;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Score {

    public static final int BOUNDARY_PAWN_COUNT_IN_SAME_COLUMN = 2;
    private final Map<Team, Double> scoreTable;

    public Score(final Map<Team, Double> scoreTable) {
        this.scoreTable = scoreTable;
    }

    public static Score fromBoard(Board board) {
        Map<Team, Double> scoreTable = new HashMap<>();
        scoreTable.put(Team.WHITE, calculateTeamScore(board, Team.WHITE));
        scoreTable.put(Team.BLACK, calculateTeamScore(board, Team.BLACK));
        return new Score(scoreTable);
    }

    private static double calculateTeamScore(Board board, Team team) {
        List<Piece> pieces = findWTeam(board, team);
        double score = pieces.stream()
            .mapToDouble(p -> p.getPieceType().getScore())
            .sum();
        int pawnCountInSameColumn = countOfPawnInSameColum(board, team);
        return score - (pawnCountInSameColumn * 0.5);
    }

    private static List<Piece> findWTeam(final Board board, Team team) {
        return board.getBoard().values().stream()
            .filter(p -> p.isSameTeam(team))
            .collect(Collectors.toList());
    }

    private static int countOfPawnInSameColum(final Board board, final Team team) {
        int totalPawnCountInSameColum = 0;
        for (Col col : Col.values()) {
            int pawnCount = getPawnCount(board, team, col);
            totalPawnCountInSameColum = updateTotalPawnCount(totalPawnCountInSameColum, pawnCount);
        }
        return totalPawnCountInSameColum;
    }

    private static int updateTotalPawnCount(int totalPawnCountInSameColum, final int pawnCount) {
        if (pawnCount >= BOUNDARY_PAWN_COUNT_IN_SAME_COLUMN) {
            totalPawnCountInSameColum += pawnCount;
        }
        return totalPawnCountInSameColum;
    }

    private static int getPawnCount(final Board board, final Team team, final Col col) {
        int pawnCount = 0;
        for (Row row : Row.values()) {
            Piece piece = board.findPiece(Position.of(row, col));
            pawnCount = updatePawnCount(team, pawnCount, piece);
        }
        return pawnCount;
    }

    private static int updatePawnCount(final Team team, int pawnCount, final Piece piece) {
        if (piece.getPieceType() == PieceType.PAWN && piece.isSameTeam(team)) {
            pawnCount++;
        }
        return pawnCount;
    }

    public Map<Team, Double> getScoreTable() {
        return Collections.unmodifiableMap(scoreTable);
    }
}
