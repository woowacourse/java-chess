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

class KnightTest {

    @ParameterizedTest
    @MethodSource("availablePositions")
    @DisplayName("Knight 는 상하좌우로 이동할 수 있다.")
    void moveKnight(Position target) {
        Piece piece = new Knight(Team.WHITE);

        Assertions.assertThat(piece.availableMove(Position.of(XPosition.D, YPosition.FOUR), target))
                .isEqualTo(true);
    }

    private static Stream<Position> availablePositions() {
        return Stream.of(
                Position.of(XPosition.E, YPosition.SIX),
                Position.of(XPosition.C, YPosition.SIX),
                Position.of(XPosition.B, YPosition.FIVE),
                Position.of(XPosition.F, YPosition.FIVE),
                Position.of(XPosition.B, YPosition.THREE),
                Position.of(XPosition.F, YPosition.THREE),
                Position.of(XPosition.C, YPosition.TWO),
                Position.of(XPosition.E, YPosition.TWO)
        );
    }

    @ParameterizedTest
    @MethodSource("disablePositions")
    @DisplayName("Knight 는 상하좌우 L자 이외로 이동할 수 없다.")
    void nonMoveKnight(Position target) {
        Piece piece = new Knight(Team.WHITE);

        Assertions.assertThat(piece.availableMove(Position.of(XPosition.D, YPosition.FOUR), target))
                .isEqualTo(false);
    }

    private static Stream<Position> disablePositions() {
        return Stream.of(
                Position.of(XPosition.D, YPosition.FIVE),
                Position.of(XPosition.E, YPosition.FOUR),
                Position.of(XPosition.C, YPosition.FOUR),
                Position.of(XPosition.D, YPosition.THREE),
                Position.of(XPosition.B, YPosition.FOUR),
                Position.of(XPosition.D, YPosition.TWO),
                Position.of(XPosition.F, YPosition.FOUR),
                Position.of(XPosition.D, YPosition.SIX)
        );
    }
}
