package domain.piece.unit;

import static chess.domain.piece.property.Team.*;
import static domain.PositionFixtures.*;

import chess.domain.piece.unit.King;
import chess.domain.piece.unit.Piece;
import chess.domain.position.Position;
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
        Piece piece = new King(WHITE);

        Assertions.assertThat(piece.availableMove(B2, target, true)).isEqualTo(true);
    }

    private static Stream<Position> availablePositions() {
        return Stream.of(A1, A2, A3, B1, B3, C1, C2, C3);
    }

    @ParameterizedTest
    @MethodSource("disablePositions")
    @DisplayName("King 은 상하좌우 한 칸 이외에 이동할 수 없다.")
    void dontMoveKing(Position target) {
        Piece piece = new King(WHITE);

        Assertions.assertThat(piece.availableMove(B2, target, true)).isEqualTo(false);
    }

    private static Stream<Position> disablePositions() {
        return Stream.of(A4, B4, C4, D1, D2, D3, D4);
    }
}
