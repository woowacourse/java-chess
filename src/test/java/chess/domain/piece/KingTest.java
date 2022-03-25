package chess.domain.piece;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.BoardFixtures;
import chess.domain.Color;
import chess.domain.position.Position;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class KingTest {

    private static final Position kingSourcePosition = new Position("d4");

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
        List<List<Piece>> board = BoardFixtures.generateEmptyChessBoard().getBoard();
        King king = new King(Color.WHITE);

        assertDoesNotThrow(() -> king.validateMove(board, kingSourcePosition, targetPosition));
    }

    @DisplayName("이동 불가능한 위치인 경우 예외를 던진다.")
    @ParameterizedTest
    @MethodSource("generateImpossiblePositions")
    void 이동_불가능한_경우_예외를_던진다(Position targetPosition) {
        List<List<Piece>> board = BoardFixtures.generateEmptyChessBoard().getBoard();
        King king = new King(Color.WHITE);

        Assertions.assertThatThrownBy(() -> king.validateMove(board, kingSourcePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
