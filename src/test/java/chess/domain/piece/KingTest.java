package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.Board;
import chess.domain.BoardFixtures;
import chess.domain.Color;
import chess.domain.position.Position;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class KingTest {

    private static final King KING = new King(Color.WHITE);
    private static final Position KING_SOURCE_POSITION = new Position("d4");

    private static Stream<Arguments> generatePossiblePositions() {
        return Stream.of("c3", "d3", "e3", "c4", "e4", "c5", "d5", "e5")
                .map(Arguments::of);
    }

    private static Stream<Arguments> generateImpossiblePositions() {
        return Stream.of("b2", "c2", "d2", "e2", "f2", "b3", "f3", "b4", "f4", "b5", "f5", "b6", "c6", "d6", "e6", "f6")
                .map(Arguments::of);
    }

    @DisplayName("이동 가능한 경우 예외를 던지지 않는다.")
    @ParameterizedTest
    @MethodSource("generatePossiblePositions")
    void 이동_가능한_경우_예외를_던지지_않는다(Position targetPosition) {
        Board board = BoardFixtures.generateEmptyChessBoard().getBoard();

        board.place(KING_SOURCE_POSITION, KING);

        assertDoesNotThrow(() -> KING.validateMove(board, KING_SOURCE_POSITION, targetPosition));
    }

    @DisplayName("이동 불가능한 위치인 경우 예외를 던진다.")
    @ParameterizedTest
    @MethodSource("generateImpossiblePositions")
    void 이동_불가능한_경우_예외를_던진다(Position targetPosition) {
        Board board = BoardFixtures.generateEmptyChessBoard().getBoard();

        board.place(KING_SOURCE_POSITION, KING);

        Assertions.assertThatThrownBy(() -> KING.validateMove(board, KING_SOURCE_POSITION, targetPosition))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("target 위치에 같은 진영의 기물이 위치한 경우 경우 예외를 던진다.")
    @ParameterizedTest
    @MethodSource("generatePossiblePositions")
    void 이동_가능하고_같은진영의_기물이_위치한_경우_예외를_던진다(Position targetPosition) {
        Board board = BoardFixtures.generateEmptyChessBoard().getBoard();
        Pawn pawn = new Pawn(Color.WHITE);

        board.place(KING_SOURCE_POSITION, KING);
        board.place(targetPosition, pawn);

        assertThatThrownBy(() -> KING.validateMove(board, KING_SOURCE_POSITION, targetPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("같은 진영 기물은 공격할 수 없습니다.");
    }
}
