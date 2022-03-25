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

class QueenTest {

    private static final Position queenSourcePosition = new Position("d1");

    private static final Stream<Arguments> generatePossiblePositions() {
        List<String> positions = List.of(
                "a1", "b1", "c1", "e1", "f1", "g1", "h1",
                "d2", "d3", "d4", "d5", "d6", "d7", "d8",
                "c2", "b3", "a4", "e2", "f3", "g4", "h5");

        return positions.stream()
                .map(Arguments::of);
    }

    private static final Stream<Arguments> generateImPossiblePositions() {
        return List.of("b2", "a2", "a3", "f2", "g2", "h2", "g3", "h3", "h4")
                .stream()
                .map(Arguments::of);
    }

    @DisplayName("이동 가능한 경우 예외를 던지지 않는다.")
    @ParameterizedTest
    @MethodSource("generatePossiblePositions")
    void 이동_가능한_경우_예외를_던지지_않는다(Position targetPosition) {
        List<List<Piece>> board = BoardFixtures.generateEmptyChessBoard().getBoard();
        Queen queen = new Queen(Color.WHITE);

        assertDoesNotThrow(() -> queen.validateMove(board, queenSourcePosition, targetPosition));
    }

    @DisplayName("이동 불가능한 위치인 경우 예외를 던진다.")
    @ParameterizedTest
    @MethodSource("generateImPossiblePositions")
    void 이동_불가능한_경우_예외를_던진다(Position targetPosition) {
        List<List<Piece>> board = BoardFixtures.generateEmptyChessBoard().getBoard();
        Queen queen = new Queen(Color.WHITE);

        Assertions.assertThatThrownBy(() -> queen.validateMove(board, queenSourcePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이동 가능한 위치 중간에 기물이 위치한 경우 경우 예외를 던진다.")
    @ParameterizedTest
    @MethodSource("generatePossiblePositions")
    void 이동_가능하고_기물이_위치한_경우_예외를_던진다(Position targetPosition) {
        List<List<Piece>> board = BoardFixtures.generateInitChessBoard().getBoard();
        Queen queen = new Queen(Color.WHITE);

        Assertions.assertThatThrownBy(() -> queen.validateMove(board, queenSourcePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("경로에 기물이 존재하여 이동할 수 없습니다.");
    }
}