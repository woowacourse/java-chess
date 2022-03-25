package domain.piece.unit;

import domain.piece.property.Team;
import domain.position.Position;
import domain.position.XPosition;
import domain.position.YPosition;
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
        Piece piece = new Bishop(Team.WHITE);

        Assertions.assertThat(piece.availableMove(Position.of(XPosition.B, YPosition.TWO), target)).isEqualTo(true);
    }

    private static Stream<Position> availablePositions() {
        return Stream.of(
                Position.of(XPosition.A, YPosition.ONE),
                Position.of(XPosition.H, YPosition.EIGHT),
                Position.of(XPosition.C, YPosition.ONE),
                Position.of(XPosition.A, YPosition.THREE)
        );
    }
}
