package domain.piece.unit;

import domain.piece.Piece;
import domain.piece.property.TeamColor;
import domain.position.Position;
import domain.position.XPosition;
import domain.position.YPosition;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class KnightTest {

    @ParameterizedTest
    @MethodSource("availablePositions")
    @DisplayName("Knight 는 상하좌우로 이동할 수 있다.")
    void moveKnight(Position target) {
        Piece piece = new Knight(TeamColor.WHITE);

        Assertions.assertThat(piece.availableMove(new Position(XPosition.D, YPosition.FOUR), target))
                .isEqualTo(true);
    }

    private static Stream<Position> availablePositions() {
        return Stream.of(
                new Position(XPosition.E, YPosition.SIX),
                new Position(XPosition.C, YPosition.SIX),
                new Position(XPosition.B, YPosition.FIVE),
                new Position(XPosition.F, YPosition.FIVE),
                new Position(XPosition.B, YPosition.THREE),
                new Position(XPosition.F, YPosition.THREE),
                new Position(XPosition.C, YPosition.TWO),
                new Position(XPosition.E, YPosition.TWO)
        );
    }

    @ParameterizedTest
    @MethodSource("disablePositions")
    @DisplayName("Knight 는 상하좌우 L자 이외로 이동할 수 없다.")
    void nonMoveKnight(Position target) {
        Piece piece = new Knight(TeamColor.WHITE);

        Assertions.assertThat(piece.availableMove(new Position(XPosition.D, YPosition.FOUR), target))
                .isEqualTo(false);
    }

    private static Stream<Position> disablePositions() {
        return Stream.of(
                new Position(XPosition.D, YPosition.FIVE),
                new Position(XPosition.E, YPosition.FOUR),
                new Position(XPosition.C, YPosition.FOUR),
                new Position(XPosition.D, YPosition.THREE),
                new Position(XPosition.B, YPosition.FOUR),
                new Position(XPosition.D, YPosition.TWO),
                new Position(XPosition.F, YPosition.FOUR),
                new Position(XPosition.D, YPosition.SIX)
        );
    }
}
