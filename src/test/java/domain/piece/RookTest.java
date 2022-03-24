package domain.piece;

import domain.piece.property.TeamColor;
import domain.piece.unit.Rook;
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
        CommonMovablePiece piece = new Rook(TeamColor.WHITE);

        Assertions.assertThat(piece.availableMove(new Position(XPosition.B, YPosition.TWO), target))
                .isEqualTo(true);
    }

    private static Stream<Position> availablePositions() {
        return Stream.of(
            new Position(XPosition.B, YPosition.ONE),
            new Position(XPosition.B, YPosition.EIGHT),
            new Position(XPosition.A, YPosition.TWO),
            new Position(XPosition.H, YPosition.TWO)
        );
    }
}
