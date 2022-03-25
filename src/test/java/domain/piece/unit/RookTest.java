package domain.piece.unit;

import domain.piece.property.TeamColor;
import domain.position.XPosition;
import domain.position.Position;
import domain.position.YPosition;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class RookTest {

    @ParameterizedTest
    @MethodSource("availablePositions")
    @DisplayName("Rook 은 상하좌우로 이동할 수 있다.")
    void moveRookUpDownRightLeft(Position target) {
        Piece piece = new Rook(TeamColor.WHITE);

        Assertions.assertThat(piece.availableMove(Position.of(XPosition.B, YPosition.TWO), target))
                .isEqualTo(true);
    }

    private static Stream<Position> availablePositions() {
        return Stream.of(
            Position.of(XPosition.B, YPosition.ONE),
            Position.of(XPosition.B, YPosition.EIGHT),
            Position.of(XPosition.A, YPosition.TWO),
            Position.of(XPosition.H, YPosition.TWO)
        );
    }
}
