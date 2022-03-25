package domain.piece.unit;

import domain.piece.property.TeamColor;
import domain.position.Position;
import domain.position.XPosition;
import domain.position.YPosition;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class KingTest {

    @ParameterizedTest
    @MethodSource("availablePositions")
    @DisplayName("King 은 상하좌우로 이동할 수 있다.")
    void moveKing(Position target) {
        Piece piece = new King(TeamColor.WHITE);

        Assertions.assertThat(piece.availableMove(Position.of(XPosition.B, YPosition.TWO), target))
                .isEqualTo(true);
    }

    private static Stream<Position> availablePositions() {
        return Stream.of(
                Position.of(XPosition.A, YPosition.THREE),
                Position.of(XPosition.B, YPosition.THREE),
                Position.of(XPosition.C, YPosition.THREE),
                Position.of(XPosition.A, YPosition.TWO),
                Position.of(XPosition.C, YPosition.TWO),
                Position.of(XPosition.A, YPosition.ONE),
                Position.of(XPosition.B, YPosition.ONE),
                Position.of(XPosition.C, YPosition.ONE)
        );
    }

    @ParameterizedTest
    @MethodSource("disablePositions")
    @DisplayName("King 은 상하좌우 한 칸 이외에 이동할 수 없다.")
    void dontMoveKing(Position target) {
        Piece piece = new King(TeamColor.WHITE);

        Assertions.assertThat(piece.availableMove(Position.of(XPosition.B, YPosition.TWO), target))
                .isEqualTo(false);
    }

    private static Stream<Position> disablePositions() {
        return Stream.of(
                Position.of(XPosition.D, YPosition.ONE),
                Position.of(XPosition.D, YPosition.TWO),
                Position.of(XPosition.D, YPosition.THREE),
                Position.of(XPosition.D, YPosition.FOUR),
                Position.of(XPosition.A, YPosition.FOUR),
                Position.of(XPosition.B, YPosition.FOUR),
                Position.of(XPosition.C, YPosition.FOUR)
        );
    }
}
