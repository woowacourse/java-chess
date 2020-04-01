package chess.domain.score;

import chess.domain.Team;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ScoreCalculator {

    public static Score calculate(final Map<Position, Piece> board) {
        Map<Team, Double> scores = new HashMap<>();

        if (isAnyKingKilled(board)) {
            return new Score(findTeamKingKilled(board).opponent());
        }

        for (Team team : Team.values()) {
            scores.put(team, calculateScoreOf(board, team));
        }
        return new Score(scores);
    }

    private static boolean isAnyKingKilled(Map<Position, Piece> board) {
        return Arrays.stream(Team.values())
            .anyMatch(team -> isKingKilled(board, team));
    }

    private static Team findTeamKingKilled(final Map<Position, Piece> board) {
        return Arrays.stream(Team.values())
            .filter(team -> isKingKilled(board, team))
            .findFirst()
            .orElseThrow(() -> new IllegalStateException("킹이 죽은 팀이 없습니다."));
    }

    private static boolean isKingKilled(final Map<Position, Piece> board, final Team killed) {
        return board.entrySet().stream()
            .noneMatch(entry -> entry.getValue().is(PieceType.KING)
                && entry.getValue().belongs(killed));
    }

    private static double calculateScoreOf(final Map<Position, Piece> board, final Team team) {
        return board.entrySet().stream()
            .filter(entry -> entry.getValue().belongs(team))
            .mapToDouble(entry -> calculateScoreOf(board, entry.getKey()))
            .sum();
    }

    private static double calculateScoreOf(final Map<Position, Piece> board, Position position) {
        if (board.get(position).is(PieceType.PAWN)) {
            return board.get(position)
                .getScore(isSameTeamPawnOnSameColumnOfPawn(board, position));
        }
        return board.get(position).getScore();
    }

    private static boolean isSameTeamPawnOnSameColumnOfPawn(
        final Map<Position, Piece> board, Position position) {

        if (!board.containsKey(position) || board.get(position).is(PieceType.PAWN)) {
            throw new IllegalArgumentException("start 위치에 기물이 없거나 폰이 아닙니다.");
        }
        return position.getSameColumnPositions().stream()
            .anyMatch(target -> board.containsKey(target)    /* 도착위치에 기물이 존재함 */
                && board.get(target).is(PieceType.PAWN)      /* 그 기물이 폰임 */
                && board.get(target).isSameTeam(board.get(position)));
    }
}
