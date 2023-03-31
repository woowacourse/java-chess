package chess.board;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import chess.game.PieceScore;
import chess.piece.Piece;
import chess.piece.PieceType;
import chess.piece.Team;

public class GameScore {

    private static final int MINIMUM_NUMBER_OF_DUPLICATED_PAWN = 2;
    private static final double DUPLICATED_PAWN_SCORE = 0.5;

    private final Map<Position, Piece> board;

    public GameScore(final Map<Position, Piece> board) {
        this.board = board;
    }

    public double calculateScore(final Team team) {
        return calculateTotalScore(team) - calculateDuplicatedPawnScore(team);
    }

    private double calculateTotalScore(final Team team) {
        return board.values()
                .stream()
                .filter(piece -> piece.isSameTeam(team))
                .mapToDouble(piece -> PieceScore.findScore(piece.getType()))
                .sum();
    }

    private double calculateDuplicatedPawnScore(final Team team) {
        return countPawnInSameFile(team).values()
                .stream()
                .filter(numberOfPawn -> numberOfPawn >= MINIMUM_NUMBER_OF_DUPLICATED_PAWN)
                .mapToDouble(pawnScore -> pawnScore * DUPLICATED_PAWN_SCORE)
                .sum();
    }

    private Map<File, Long> countPawnInSameFile(final Team team) {
        return Arrays.stream(Rank.values())
                .flatMap(file -> Arrays.stream(File.values())
                        .map(rank -> new Position(rank, file)))
                .filter(position -> board.get(position).isSameTeam(team))
                .filter(position -> board.get(position).isSameType(PieceType.PAWN))
                .collect(Collectors.groupingBy(Position::getFile, Collectors.counting()));
    }
}
