package chess.domain.chessgame;

import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.strategy.piecemovestrategy.PieceType;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class PlayerScore {

    private static final double MAJORITY_PAWN_SCORE = 0.5;

    private final double playerScore;

    PlayerScore(final double playerScore) {
        this.playerScore = playerScore;
    }

    public static PlayerScore from(final Collection<Piece> pieces) {
        final double nonPawnScore = calculateNonPawnScore(pieces);
        final double pawnScore = calculatePawnScore(pieces);

        return new PlayerScore(nonPawnScore + pawnScore);
    }

    private static double calculateNonPawnScore(final Collection<Piece> pieces) {
        return pieces.stream()
                     .map(Piece::getPieceType)
                     .filter(pieceType -> !PieceType.isPawn(pieceType))
                     .mapToDouble(PieceType::getScore)
                     .sum();
    }

    private static double calculatePawnScore(final Collection<Piece> pieces) {
        final Map<File, Long> fileCount = countPawnByFile(pieces);

        return fileCount.values()
                        .stream()
                        .mapToDouble(PlayerScore::calculatePawnScoreEachFile)
                        .sum();
    }

    private static Map<File, Long> countPawnByFile(final Collection<Piece> pieces) {
        return pieces.stream()
                     .filter(piece -> PieceType.isPawn(piece.getPieceType()))
                     .map(Piece::getPosition)
                     .map(Position::getFile)
                     .collect(groupingBy(identity(), counting()));
    }

    private static double calculatePawnScoreEachFile(Long count) {
        if (count > 1) {
            return MAJORITY_PAWN_SCORE * count;
        }
        return PieceType.PAWN.getScore() * count;
    }

    public double getPlayerScore() {
        return playerScore;
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
}
