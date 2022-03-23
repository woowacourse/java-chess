package domain.piece;

import domain.position.Position;
import domain.position.XPosition;
import domain.position.YPosition;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BishopTest {

    @ParameterizedTest
    @MethodSource("availablePositions")
    @DisplayName("Bishop 은 대각선으로 이동할 수 있다.")
    void movePawnOneSpace(Position target) {
        Piece piece = new Bishop(Player.WHITE);

        Assertions.assertThat(piece.availableMove(new Position(XPosition.B, YPosition.TWO), target)).isEqualTo(true);
    }

    private static Stream<Position> availablePositions() {
        return Stream.of(
            new Position(XPosition.A, YPosition.ONE),
            new Position(XPosition.H, YPosition.EIGHT),
            new Position(XPosition.C, YPosition.ONE),
            new Position(XPosition.A, YPosition.THREE)
        );
    }
}
