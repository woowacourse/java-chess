package domain.piece.unit;

import static chess.domain.piece.property.Team.*;
import static domain.PositionFixtures.*;

import chess.domain.piece.unit.Piece;
import chess.domain.piece.unit.Queen;
import chess.domain.position.Position;
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
        Piece piece = new Queen(WHITE);

        Assertions.assertThat(piece.availableMove(B2, target, true)).isEqualTo(true);
    }

    private static Stream<Position> availablePositions() {
        return Stream.of(A1, A2, A3, B1, B8, C1, H2, H8);
    }
}
