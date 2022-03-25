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

public class BishopTest {

    private static final Position BishopSourcePosition = new Position("c1");

    private static final Stream<Arguments> generatePossiblePositions() {
        return List.of("b2", "a3", "d2", "e3", "f4", "g5", "h6")
                .stream()
                .map(Arguments::of);
    }

    private static final Stream<Arguments> generateImpossiblePositions() {
        List<String> positions = List.of(
                "a1", "b1", "d1", "e1", "f1", "g1", "h1", "a2", "c2", "e2",
                "f2", "g2", "h2", "b3", "c3", "d3", "f3", "g3", "h3", "a4",
                "b4", "c4", "d4", "e4", "g4", "h4", "a5", "b5", "c5", "d5",
                "e5", "f5", "h5", "a6", "b6", "c6", "d6", "e6", "f6", "g6",
                "a7", "b7", "c7", "d7", "e7", "f7", "g7", "h7", "a8", "b8",
                "c8", "d8", "e8", "f8", "g8", "h8");

        return positions.stream()
                .map(Arguments::of);
    }

    @DisplayName("이동 가능한 경우 예외를 던지지 않는다.")
    @ParameterizedTest
    @MethodSource("generatePossiblePositions")
    void 이동_가능한_경우_예외를_던지지_않는다(Position targetPosition) {
        List<List<Piece>> board = BoardFixtures.generateEmptyChessBoard().getBoard();
        Bishop bishop = new Bishop(Color.WHITE);

        assertDoesNotThrow(() -> bishop.validateMove(board, BishopSourcePosition, targetPosition));
    }

    @DisplayName("이동 불가능한 위치인 경우 예외를 던진다.")
    @ParameterizedTest
    @MethodSource("generateImpossiblePositions")
    void 이동_불가능한_경우_예외를_던진다(Position targetPosition) {
        List<List<Piece>> board = BoardFixtures.generateEmptyChessBoard().getBoard();
        Bishop bishop = new Bishop(Color.WHITE);

        Assertions.assertThatThrownBy(() -> bishop.validateMove(board, BishopSourcePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이동 가능한 위치 중간에 기물이 위치한 경우 경우 예외를 던진다.")
    @ParameterizedTest
    @MethodSource("generatePossiblePositions")
    void 이동_가능하고_기물이_위치한_경우_예외를_던진다(Position targetPosition) {
        List<List<Piece>> board = BoardFixtures.generateInitChessBoard().getBoard();
        Bishop bishop = new Bishop(Color.WHITE);

        Assertions.assertThatThrownBy(() -> bishop.validateMove(board, BishopSourcePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("경로에 기물이 존재하여 이동할 수 없습니다.");
    }
}
