package chess.domain.piece;

import chess.domain.Board;
import chess.domain.PieceInfo;
import chess.domain.Position;
import chess.domain.Team;
import chess.domain.strategy.QueenMoveStrategy;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class QueenTest {

    private static Stream<Arguments> queenMoveTestParameters() {
        return Stream.of(
                Arguments.of(Position.of("d4"), Position.of("a1"), true),
                Arguments.of(Position.of("d4"), Position.of("b6"), true),
                Arguments.of(Position.of("d4"), Position.of("e5"), true),
                Arguments.of(Position.of("d4"), Position.of("g1"), true),
                Arguments.of(Position.of("d4"), Position.of("d1"), true),
                Arguments.of(Position.of("d4"), Position.of("a4"), true),
                Arguments.of(Position.of("d4"), Position.of("d8"), true),
                Arguments.of(Position.of("d4"), Position.of("h4"), true),
                Arguments.of(Position.of("d4"), Position.of("b3"), false)
        );
    }

    @DisplayName("퀸은 한 번에 수직 혹은 수평 혹은 대각선으로 여러 칸 이동 가능하다.")
    @ParameterizedTest
    @MethodSource("queenMoveTestParameters")
    void queenMoveTest(Position currentPosition, Position newPosition, boolean expectedIsMoved) {
        PieceInfo pieceInfo = new PieceInfo(currentPosition, Team.WHITE);
        Queen queen = new Queen(pieceInfo, new QueenMoveStrategy());
        Board board = new Board();

        boolean actualIsMoved = queen.move(newPosition, board);

        Assertions.assertThat(actualIsMoved).isEqualTo(expectedIsMoved);
    }

    //TODO: True를 반환하는게 진짜 Position이 옮겨지는것인가 - Board 구현 시 해결될 듯?
}
