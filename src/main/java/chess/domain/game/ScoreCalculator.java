package chess.domain.game;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;
import chess.domain.piece.pawn.Pawn;

import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public final class ScoreCalculator {

    private static final int COUNT_OF_PAWN_DEGRADE_SCORE = 2;

    private ScoreCalculator() {
    }

    public static double calculateScoreByTeam(final Team team, final Map<Position, Piece> board) {
        final double totalScore = board.values()
                .stream()
                .filter(piece -> piece.isSameTeamWith(team))
                .mapToDouble(Piece::getScore)
                .sum();

        final Map<File, Long> pieceCountByFile = board.entrySet()
                .stream()
                .filter(entry -> isPawn(entry.getValue()))
                .filter(entry -> entry.getValue().isSameTeamWith(team))
                .collect(groupingBy(e -> e.getKey().getFile(), Collectors.counting()));

        final double totalMinusScore = pieceCountByFile.values()
                .stream()
                .filter(entries -> entries >= COUNT_OF_PAWN_DEGRADE_SCORE)
                .mapToDouble(entries -> entries * Pawn.DEGRADED_SCORE)
                .sum();

        return totalScore - totalMinusScore;
    }

    private static boolean isPawn(final Piece piece) {
        return piece.isSamePieceTypeAs(PieceType.INITIAL_WHITE_PAWN) ||
                piece.isSamePieceTypeAs(PieceType.INITIAL_BLACK_PAWN) ||
                piece.isSamePieceTypeAs(PieceType.WHITE_PAWN) ||
                piece.isSamePieceTypeAs(PieceType.BLACK_PAWN);
    }

}
