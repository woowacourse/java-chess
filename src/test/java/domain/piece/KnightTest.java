package domain.piece;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import domain.Player;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class KnightTest {

    @ParameterizedTest
    @MethodSource("knightAvailableTarget")
    @DisplayName("Knight는 현재 위치에서 상하좌우로 한칸 이동 후 대각선으로 이동할 수 있다.(L자)")
    void moveKnightRule(Position target) {
        Piece piece = new Knight(Player.WHITE);
        Position source = Position.of(File.C, Rank.FOUR);

        assertDoesNotThrow(() -> piece.move(source, target));
    }

    private static Stream<Position> knightAvailableTarget() {
        return Stream.of(
            Position.of(File.B, Rank.SIX),
            Position.of(File.D, Rank.SIX),
            Position.of(File.A, Rank.FIVE),
            Position.of(File.A, Rank.THREE),
            Position.of(File.E, Rank.FIVE),
            Position.of(File.E, Rank.THREE),
            Position.of(File.B, Rank.TWO),
            Position.of(File.D, Rank.TWO)
        );
    }

    @ParameterizedTest
    @MethodSource("knightUnavailableTarget")
    @DisplayName("Knight는 현재 위치에서 상하좌우로 한칸 이동 후 대각선 외 위치는 이동할 수 없다.(L자)")
    void moveKnightNonRulePosition(Position target) {
        Piece piece = new Knight(Player.WHITE);
        Position source = Position.of(File.C, Rank.FOUR);

        assertThrows(IllegalArgumentException.class,
            () -> piece.move(source, target));
    }

    private static Stream<Position> knightUnavailableTarget() {
        return Stream.of(
            Position.of(File.C, Rank.FIVE),
            Position.of(File.C, Rank.THREE),
            Position.of(File.E, Rank.SEVEN),
            Position.of(File.A, Rank.ONE),
            Position.of(File.E, Rank.SIX)
        );
    }
}
