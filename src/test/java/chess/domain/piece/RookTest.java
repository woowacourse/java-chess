package chess.domain.piece;

import chess.domain.Board;
import chess.domain.PieceInfo;
import chess.domain.Position;
import chess.domain.Team;
import chess.domain.strategy.RookMoveStrategy;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class RookTest {

    private static Stream<Arguments> rookMoveTestParameters() {
        return Stream.of(
                Arguments.of(Position.of("d4"), Position.of("d1"), true),
                Arguments.of(Position.of("d4"), Position.of("a4"), true),
                Arguments.of(Position.of("d4"), Position.of("d8"), true),
                Arguments.of(Position.of("d4"), Position.of("h4"), true),
                Arguments.of(Position.of("d4"), Position.of("b3"), false)
        );
    }

    @DisplayName("룩은 한 번에 수직 혹은 수평으로 여러 칸 이동 가능하다.")
    @ParameterizedTest
    @MethodSource("rookMoveTestParameters")
    void rookMoveTest(Position currentPosition, Position newPosition, boolean expectedIsMoved) {
        PieceInfo pieceInfo = new PieceInfo(currentPosition, Team.WHITE);
        Rook rook = new Rook(pieceInfo, new RookMoveStrategy());
        Board board = Board.initialize();

        boolean actualIsMoved = rook.move(newPosition, board);

        Assertions.assertThat(actualIsMoved).isEqualTo(expectedIsMoved);
    }

    //TODO: True를 반환하는게 진짜 Position이 옮겨지는것인가 - Board 구현 시 해결될 듯?
}
