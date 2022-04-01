package domain.piece.unit;

import static domain.PositionFixtures.*;
import static domain.piece.property.Team.*;

import domain.position.Position;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class BishopTest {

    @ParameterizedTest
    @MethodSource("availablePositions")
    @DisplayName("Bishop 은 대각선으로 이동할 수 있다.")
    void moveBishop(Position target) {
        Piece piece = new Bishop(WHITE);

        Assertions.assertThat(piece.availableMove(B2, target)).isEqualTo(true);
    }

    private static Stream<Position> availablePositions() {
        return Stream.of(A1, A3, C1, H8);
    }
}
