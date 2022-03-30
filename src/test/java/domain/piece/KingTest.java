package domain.piece;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

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

        piece.generateAvailablePosition(source);
        assertDoesNotThrow(() -> piece.getAvailablePositions(source, target));
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

        piece.generateAvailablePosition(source);
        assertDoesNotThrow(() -> piece.getAvailablePositions(source, target));
    }

    private static Stream<Position> targetPosition_overRage() {
        return Stream.of(
            Position.of(File.A, Rank.TWO),
            Position.of(File.B, Rank.TWO),
            Position.of(File.C, Rank.TWO),
            Position.of(File.A, Rank.ONE),
            Position.of(File.C, Rank.ONE)
        );
    }
}
