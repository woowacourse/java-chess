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

public class KingTest {

    @ParameterizedTest
    @MethodSource("targetPosition")
    @DisplayName("King 은 현재 위치에서 모든 방향으로 한 칸 이동할 수 있다.")
    void moveKingAnyPositionOneSpace(Position target) {
        Piece piece = new King(Player.WHITE);
        Position source = Position.of(File.B, Rank.TWO);

        assertDoesNotThrow(() -> piece.move(source, target));
    }

    private static Stream<Position> targetPosition() {
        return Stream.of(
            Position.of(File.A, Rank.THREE),
            Position.of(File.B, Rank.THREE),
            Position.of(File.C, Rank.THREE),
            Position.of(File.A, Rank.TWO),
            Position.of(File.C, Rank.TWO),
            Position.of(File.A, Rank.ONE),
            Position.of(File.B, Rank.ONE),
            Position.of(File.C, Rank.ONE)
        );
    }

    @ParameterizedTest
    @MethodSource("targetPosition_overRage")
    @DisplayName("King이 이동할 수 있는 범위 중 벽에 가로막힌 곳이 있다면 이동할 수 없다.")
    void moveKingAnyPositionOneSpace_overRange(Position target) {
        Piece piece = new King(Player.WHITE);
        Position source = Position.of(File.B, Rank.ONE);

        assertDoesNotThrow(() -> piece.move(source, target));
    }

    private static Stream<Position> targetPosition_overRage() {
        return Stream.of(
            Position.of(File.A, Rank.TWO),
            Position.of(File.A, Rank.ONE),
            Position.of(File.B, Rank.TWO),
            Position.of(File.C, Rank.TWO),
            Position.of(File.C, Rank.ONE)
        );
    }

    @ParameterizedTest
    @MethodSource("unavailableTarget")
    @DisplayName("King 은 현재 위치에서 모든 방향으로 한 칸 이외의 위치는 이동할 수 없다.")
    void moveKingNonRulePosition(Position target) {
        Piece piece = new King(Player.WHITE);
        Position source = Position.of(File.C, Rank.FOUR);

        assertThrows(IllegalArgumentException.class,
            () -> piece.move(source, target));
    }

    private static Stream<Position> unavailableTarget() {
        return Stream.of(
            Position.of(File.A, Rank.FOUR),
            Position.of(File.C, Rank.SIX),
            Position.of(File.D, Rank.TWO),
            Position.of(File.H, Rank.ONE),
            Position.of(File.E, Rank.SIX)
        );
    }
}
