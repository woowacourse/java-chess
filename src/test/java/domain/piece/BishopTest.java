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

public class BishopTest {

    @ParameterizedTest
    @MethodSource("moveDiagonalTarget")
    @DisplayName("Bishop 은 대각선으로 이동할 수 있다.")
    void moveDiagonalQueen(Position target) {
        Piece piece = new Bishop(Player.WHITE);
        Position source = Position.of(File.B, Rank.TWO);

        assertDoesNotThrow(() -> piece.move(source, target));
    }

    private static Stream<Position> moveDiagonalTarget() {
        return Stream.of(
            Position.of(File.G, Rank.SEVEN),
            Position.of(File.A, Rank.ONE),
            Position.of(File.A, Rank.THREE),
            Position.of(File.C, Rank.ONE)
        );
    }

    @ParameterizedTest
    @MethodSource("bishopUnavailableTarget")
    @DisplayName("Bishop 은 대각선 이외의 위치는 이동할 수 없다.")
    void moveStraightQueen(Position target) {
        Piece piece = new Bishop(Player.WHITE);
        Position source = Position.of(File.B, Rank.TWO);

        assertThrows(IllegalArgumentException.class,
            () -> piece.move(source, target));
    }

    private static Stream<Position> bishopUnavailableTarget() {
        return Stream.of(
            Position.of(File.B, Rank.ONE),
            Position.of(File.B, Rank.SIX),
            Position.of(File.A, Rank.TWO),
            Position.of(File.H, Rank.TWO),
            Position.of(File.C, Rank.FOUR),
            Position.of(File.A, Rank.FOUR)
        );
    }
}
