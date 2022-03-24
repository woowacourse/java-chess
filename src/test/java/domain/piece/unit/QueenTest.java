package domain.piece.unit;

import domain.piece.CommonMovablePiece;
import domain.piece.Piece;
import domain.piece.property.TeamColor;
import domain.piece.unit.Queen;
import domain.position.Position;
import domain.position.XPosition;
import domain.position.YPosition;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class QueenTest {

    @ParameterizedTest
    @MethodSource("availablePositions")
    @DisplayName("Queen 은 상하좌우, 대각선으로 이동할 수 있다.")
    void moveQueen(Position target) {
        Piece piece = new Queen(TeamColor.WHITE);

        Assertions.assertThat(piece.availableMove(new Position(XPosition.B, YPosition.TWO), target))
                .isEqualTo(true);
    }

    private static Stream<Position> availablePositions() {
        return Stream.of(
                new Position(XPosition.B, YPosition.ONE),
                new Position(XPosition.B, YPosition.EIGHT),
                new Position(XPosition.A, YPosition.TWO),
                new Position(XPosition.H, YPosition.TWO),
                new Position(XPosition.A, YPosition.ONE),
                new Position(XPosition.H, YPosition.EIGHT),
                new Position(XPosition.C, YPosition.ONE),
                new Position(XPosition.A, YPosition.THREE)
        );
    }
}
