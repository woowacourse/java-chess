package chess.domain;

import chess.domain.board.Board;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Horizontal;
import chess.domain.position.Position;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public final class ChessResult {
    private final Map<Position, Piece> chessBoard;

    public ChessResult(final Map<Position, Piece> chessBoard) {
        this.chessBoard = new TreeMap<>(chessBoard);
    }

    public double totalScore(final Team team) {
        double total = 0;
        for (final Horizontal column : Horizontal.values()) {
            total += columnTotalScore(team, column);
        }
        return total;
    }

    private double columnTotalScore(final Team team, final Horizontal column) {
        final List<Piece> pieces = chessBoard.keySet().stream()
                .filter(position -> position.horizontal().isSameValue(column))
                .map(chessBoard::get)
                .filter(piece -> piece.friendly(team))
                .collect(Collectors.toList());

        return pieces.stream()
                .mapToDouble(Piece::score)
                .reduce(0, Double::sum) - pawnDiscountScore(pieces);
    }

    private double pawnDiscountScore(final List<Piece> pieces) {
        long count = pieces.stream()
                .filter(Piece::isPawn)
                .count();

        if (count >= 2) {
            return count * Pawn.EXTRA_SCORE;
        }
        return 0;
    }

    public Team winner() {
        if (new Board(chessBoard).isKingDead()) {
            return kingSlayerTeam(chessBoard);
        }

        return scoreWinner();
    }

    private Team kingSlayerTeam(Map<Position, Piece> chessBoard) {
        return chessBoard.values().stream()
                .filter(Piece::isKing)
                .map(Piece::team)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    private Team scoreWinner() {
        if (totalScore(Team.BLACK) > totalScore(Team.WHITE)) {
            return Team.BLACK;
        }
        if (totalScore(Team.BLACK) < totalScore(Team.WHITE)) {
            return Team.WHITE;
        }
        return Team.NOTHING;
    }

    public Map<String, Double> scoreResult() {
        if (winner().undefined()) {
            Team team = winner().anyTeamExcludingThis();
            return teamScores(team);
        }
        return teamScores(winner());
    }

    private Map<String, Double> teamScores(Team winner) {
        Map<String, Double> result = new LinkedHashMap<>();
        result.put(winner.teamName(), totalScore(winner));
        result.put(winner.oppositeTeamName(), totalScore(winner.oppositeTeam()));
        return result;
    }
}
