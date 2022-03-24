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

        Assertions.assertThat(piece.availableMove(new Position(XPosition.B, YPosition.TWO), target))
                .isEqualTo(true);
    }

    private static Stream<Position> availablePositions() {
        return Stream.of(
                new Position(XPosition.A, YPosition.THREE),
                new Position(XPosition.B, YPosition.THREE),
                new Position(XPosition.C, YPosition.THREE),
                new Position(XPosition.A, YPosition.TWO),
                new Position(XPosition.C, YPosition.TWO),
                new Position(XPosition.A, YPosition.ONE),
                new Position(XPosition.B, YPosition.ONE),
                new Position(XPosition.C, YPosition.ONE)
        );
    }

    @ParameterizedTest
    @MethodSource("disablePositions")
    @DisplayName("King 은 상하좌우 한 칸 이외에 이동할 수 없다.")
    void dontMoveKing(Position target) {
        Piece piece = new King(TeamColor.WHITE);

        Assertions.assertThat(piece.availableMove(new Position(XPosition.B, YPosition.TWO), target))
                .isEqualTo(false);
    }

    private static Stream<Position> disablePositions() {
        return Stream.of(
                new Position(XPosition.D, YPosition.ONE),
                new Position(XPosition.D, YPosition.TWO),
                new Position(XPosition.D, YPosition.THREE),
                new Position(XPosition.D, YPosition.FOUR),
                new Position(XPosition.A, YPosition.FOUR),
                new Position(XPosition.B, YPosition.FOUR),
                new Position(XPosition.C, YPosition.FOUR)
        );
    }
}
