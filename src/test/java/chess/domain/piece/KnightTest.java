package chess.domain.piece;

import chess.domain.Board;
import chess.domain.PieceInfo;
import chess.domain.Position;
import chess.domain.Team;
import chess.domain.strategy.KnightMoveStrategy;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class KnightTest {

    private static Stream<Arguments> knightMoveTestParameters() {
        return Stream.of(
                Arguments.of(Position.of("d4"), Position.of("f5"), true),
                Arguments.of(Position.of("d4"), Position.of("b5"), true),
                Arguments.of(Position.of("d4"), Position.of("f3"), true),
                Arguments.of(Position.of("d4"), Position.of("b3"), true),
                Arguments.of(Position.of("d4"), Position.of("e6"), true),
                Arguments.of(Position.of("d4"), Position.of("c6"), true),
                Arguments.of(Position.of("d4"), Position.of("e2"), true),
                Arguments.of(Position.of("d4"), Position.of("c2"), true),
                Arguments.of(Position.of("d4"), Position.of("d5"), false)
        );
    }

    @DisplayName("나이트는 한 번에 수직으로 2칸과 수평으로 1칸 혹은 수평으로 2칸과 수직으로 1칸 이동 가능하다.")
    @ParameterizedTest
    @MethodSource("knightMoveTestParameters")
    void knightMoveTest(Position currentPosition, Position newPosition, boolean expectedIsMoved) {
        PieceInfo pieceInfo = new PieceInfo(currentPosition, Team.WHITE);
        Knight knight = new Knight(pieceInfo, new KnightMoveStrategy());
        Board board = new Board();

        boolean actualIsMoved = knight.move(newPosition, board);

        Assertions.assertThat(actualIsMoved).isEqualTo(expectedIsMoved);
    }
}
