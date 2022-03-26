package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import domain.Player;
import domain.position.Column;
import domain.position.Position;
import domain.position.Row;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class KnightTest {

    @ParameterizedTest
    @MethodSource("targetPosition")
    @DisplayName("Knight 은 현재 위치에서 모든 방향으로 한 칸 이동할 수 있다.")
    void moveKingDiagonally(Position target) {
        Piece piece = new Knight(Player.WHITE);
        Position source = new Position(Row.FOUR, Column.C);

        assertThat(piece.isAvailableMove(source, target)).isEqualTo(true);
    }

    private static Stream<Position> targetPosition() {
        return Stream.of(
            new Position(Row.SIX, Column.B),
            new Position(Row.SIX, Column.D),
            new Position(Row.FIVE, Column.A),
            new Position(Row.THREE, Column.A),
            new Position(Row.FIVE, Column.E),
            new Position(Row.THREE, Column.E),
            new Position(Row.TWO, Column.B),
            new Position(Row.TWO, Column.D)
        );
    }
}
