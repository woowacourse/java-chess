package chess.chessgame;

import chess.chessboard.File;
import chess.chessboard.Square;
import chess.piece.Piece;
import chess.piece.PieceType;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class PlayerScore {

    private static final double MAJORITY_PAWN_SCORE = 0.5;
    private final double playerScore;

    PlayerScore(final double playerScore) {
        this.playerScore = playerScore;
    }

    public static PlayerScore from(final Map<Square, Piece> pieces) {
        final double nonPawnScore = calculateNonPawnScore(pieces.values());
        final double pawnScore = calculatePawnScore(pieces);

        return new PlayerScore(nonPawnScore + pawnScore);
    }

    private static double calculateNonPawnScore(final Collection<Piece> pieces) {
        return pieces.stream()
                     .filter(piece -> piece.getPieceType() != PieceType.PAWN)
                     .mapToDouble(Piece::getScore)
                     .sum();
    }

    private static double calculatePawnScore(final Map<Square, Piece> pieces) {
        final Map<File, Long> fileCount = countPawnByFile(pieces);

        return fileCount.values()
                        .stream()
                        .mapToDouble(PlayerScore::calculatePawnScoreEachFile)
                        .sum();
    }

    private static Map<File, Long> countPawnByFile(final Map<Square, Piece> pieces) {
        return pieces.keySet()
                     .stream()
                     .filter(square -> {
                         final Piece piece = pieces.get(square);
                         return piece.getPieceType() == PieceType.PAWN;
                     })
                     .map(Square::getFile)
                     .collect(groupingBy(Function.identity(), counting()));
    }

    private static double calculatePawnScoreEachFile(Long count) {
        if (count > 1) {
            return MAJORITY_PAWN_SCORE * count;
        }
        return PieceType.PAWN.getScore() * count;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PlayerScore otherScore = (PlayerScore) o;
        return Double.compare(otherScore.playerScore, playerScore) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerScore);
    }

    public double getPlayerScore() {
        return playerScore;
    }
}
