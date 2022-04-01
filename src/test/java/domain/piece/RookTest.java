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

public class RookTest {

    @ParameterizedTest
    @MethodSource("rookAvailableTarget")
    @DisplayName("Rook은 상하좌우로 움직일 수 있다.")
    void moveRookUpDownRightLeft_success(Position target) {
        Piece piece = new Rook(Player.WHITE);
        Position source = Position.of(File.B, Rank.TWO);

        assertDoesNotThrow(() -> piece.move(source, target));
    }

    private static Stream<Position> rookAvailableTarget() {
        return Stream.of(
            Position.of(File.B, Rank.ONE),
            Position.of(File.B, Rank.SIX),
            Position.of(File.A, Rank.TWO),
            Position.of(File.H, Rank.TWO)
        );
    }

    @ParameterizedTest
    @MethodSource("rookUnavailableTarget")
    @DisplayName("Rook은 상하좌우 이외의 위치는 움직일 수 없다.")
    void moveRookUpDownRightLeft_fail(Position target) {
        Piece piece = new Rook(Player.WHITE);
        Position source = Position.of(File.B, Rank.TWO);

        assertThrows(IllegalArgumentException.class,
            () -> piece.move(source, target));
    }

    private static Stream<Position> rookUnavailableTarget() {
        return Stream.of(
            Position.of(File.G, Rank.SEVEN),
            Position.of(File.A, Rank.ONE),
            Position.of(File.A, Rank.THREE),
            Position.of(File.C, Rank.ONE)
        );
    }
}
