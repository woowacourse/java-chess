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

class KnightTest {

    private static final Position knightSourcePosition = new Position("d4");

    private static final Stream<Arguments> generatePossiblePositions() {
        return List.of("c2", "b3", "b5", "c6", "e6", "f5", "f3", "e2")
                .stream()
                .map(Arguments::of);
    }

    private static final Stream<Arguments> generateImpossiblePositions() {
        return List.of("c3", "d3", "e3", "c4", "e4", "c5", "d5", "e5", "b6", "d6", "f6", "b2", "d2", "f2", "b4", "f4")
                .stream()
                .map(Arguments::of);
    }

    @DisplayName("이동 가능한 경우 예외를 던지지 않는다.")
    @ParameterizedTest
    @MethodSource("generatePossiblePositions")
    void 이동_가능한_경우_예외를_던지지_않는다(Position targetPosition) {
        List<List<Piece>> board = BoardFixtures.generateEmptyChessBoard().getBoard();
        Knight knight = new Knight(Color.WHITE);

        assertDoesNotThrow(() -> knight.validateMove(board, knightSourcePosition, targetPosition));
    }

    @DisplayName("이동 불가능한 위치인 경우 예외를 던진다.")
    @ParameterizedTest
    @MethodSource("generateImpossiblePositions")
    void 이동_불가능한_경우_예외를_던진다(Position targetPosition) {
        List<List<Piece>> board = BoardFixtures.generateEmptyChessBoard().getBoard();
        Knight knight = new Knight(Color.WHITE);

        Assertions.assertThatThrownBy(() -> knight.validateMove(board, knightSourcePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class);
    }
}