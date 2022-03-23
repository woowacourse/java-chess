package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import domain.Player;
import domain.position.Column;
import domain.position.Position;
import domain.position.Row;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class KingTest {

    @ParameterizedTest
    @MethodSource("targetPosition")
    @DisplayName("King 은 현재 위치에서 모든 방향으로 한 칸 이동할 수 있다.")
    void moveKingDiagonally(Position target) {
        Piece piece = new King(Player.WHITE);
        Position source = new Position(Row.TWO, Column.B);
        List<Position> positions = piece.availableMovePositions(source);

        assertThat(positions.contains(target)).isEqualTo(true);
    }

    private static Stream<Position> targetPosition() {
        return Stream.of(
                new Position(Row.THREE, Column.A),
                new Position(Row.THREE, Column.B),
                new Position(Row.THREE, Column.C),
                new Position(Row.TWO, Column.A),
                new Position(Row.TWO, Column.C),
                new Position(Row.ONE, Column.A),
                new Position(Row.ONE, Column.B),
                new Position(Row.ONE, Column.C)
            );
    }
}
