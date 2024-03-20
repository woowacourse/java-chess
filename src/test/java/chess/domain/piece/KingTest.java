package chess.domain.piece;

import chess.domain.Board;
import chess.domain.PieceInfo;
import chess.domain.Position;
import chess.domain.Team;
import chess.domain.strategy.KingMoveStrategy;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class KingTest {

    private static Stream<Arguments> kingCanMoveTestParameters() {
        return Stream.of(
                Arguments.of(chess.domain.Position.of("d4"), Position.of("d3"), true),
                Arguments.of(Position.of("d4"), Position.of("d5"), true),
                Arguments.of(Position.of("d4"), Position.of("c4"), true),
                Arguments.of(Position.of("d4"), Position.of("e4"), true),
                Arguments.of(Position.of("d4"), Position.of("c3"), true),
                Arguments.of(Position.of("d4"), Position.of("c5"), true),
                Arguments.of(Position.of("d4"), Position.of("e3"), true),
                Arguments.of(Position.of("d4"), Position.of("e5"), true),
                Arguments.of(Position.of("d4"), Position.of("b3"), false)
        );
    }

    @DisplayName("킹은 한 번에 수직 혹은 수평 혹은 대각선으로 한 칸 이동 가능하다.")
    @ParameterizedTest
    @MethodSource("kingCanMoveTestParameters")
    void kingMoveTest(Position currentPosition, Position newPosition, boolean expectedIsMoved) {
        PieceInfo pieceInfo = new PieceInfo(currentPosition, Team.WHITE);
        King king = new King(pieceInfo, new KingMoveStrategy());
        Board board = new Board();

        boolean actualIsMoved = king.move(newPosition, board, false);

        Assertions.assertThat(actualIsMoved).isEqualTo(expectedIsMoved);
    }

    //TODO: True를 반환하는게 진짜 Position이 옮겨지는것인가 - Board 구현 시 해결될 듯?
}
