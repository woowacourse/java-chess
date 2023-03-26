package chess.domain.result;

import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.TeamColor;
import chess.domain.position.Position;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Score {
    private final Map<TeamColor, Double> score;

    private Score(Map<TeamColor, Double> score) {
        this.score = score;
    }

    public static Score calculate(Map<Position, Piece> board) {
        Map<TeamColor, Double> score = Arrays.stream(TeamColor.values())
                .collect(Collectors.toMap(Function.identity(),
                        color -> sumScoreExceptPawn(color, board) + sumScorePawn(color, board)));
        return new Score(score);
    }

    private static Double sumScoreExceptPawn(TeamColor color, Map<Position, Piece> board) {
        return board.values()
                .stream()
                .filter(piece -> piece.isSameTeam(color))
                .filter(piece -> !piece.isPawn())
                .mapToDouble(Piece::getValue)
                .sum();
    }

    private static Double sumScorePawn(TeamColor color, Map<Position, Piece> board) {
        return IntStream.range(0, 8)
                .map(file -> countPawnByEachFile(file, color, board))
                .mapToDouble(Pawn::calculateScore)
                .sum();
    }

    private static int countPawnByEachFile(int file, TeamColor color, Map<Position, Piece> board) {
        return (int) IntStream.range(0, 8)
                .mapToObj(rank -> board.get(Position.of(rank, file)))
                .filter(piece -> !Objects.isNull(piece))
                .filter(Piece::isPawn)
                .filter(piece -> piece.isSameTeam(color))
                .count();
    }

    public Double getScore(TeamColor teamColor) {
        return score.get(teamColor);
    }
}
