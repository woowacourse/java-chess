package chess.domain.piece;

import static chess.domain.BoardFixtures.generateEmptyChessBoard;
import static chess.domain.BoardFixtures.setPiece;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.ChessBoard;
import chess.domain.Color;
import chess.domain.position.Position;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class QueenTest {

    private static final String SOURCE_POSITION = "d1";

    private static Stream<Arguments> generatePossiblePositions() {
        List<String> positions = List.of(
                "a1", "b1", "c1", "e1", "f1", "g1", "h1",
                "d2", "d3", "d4", "d5", "d6", "d7", "d8",
                "c2", "b3", "a4", "e2", "f3", "g4", "h5");

        return positions.stream()
                .map(Arguments::of);
    }

    private static Stream<Arguments> generateImpossiblePositions() {
        return Stream.of("b2", "a2", "a3", "f2", "g2", "h2", "g3", "h3", "h4")
                .map(Arguments::of);
    }

    @DisplayName("이동 가능한 경우 예외를 던지지 않는다.")
    @ParameterizedTest
    @MethodSource("generatePossiblePositions")
    void 이동_가능한_경우_예외를_던지지_않는다(Position targetPosition) {
        ChessBoard chessBoard = generateEmptyChessBoard();

        Queen queen = new Queen(Color.WHITE);
        setPiece(chessBoard, SOURCE_POSITION, queen);

        assertDoesNotThrow(
                () -> queen.validateMove(chessBoard.getBoard(), new Position(SOURCE_POSITION), targetPosition));
    }

    @DisplayName("이동 불가능한 위치인 경우 예외를 던진다.")
    @ParameterizedTest
    @MethodSource("generateImpossiblePositions")
    void 이동_불가능한_경우_예외를_던진다(Position targetPosition) {
        ChessBoard chessBoard = generateEmptyChessBoard();

        Queen queen = new Queen(Color.WHITE);
        setPiece(chessBoard, SOURCE_POSITION, queen);

        assertThatThrownBy(
                () -> queen.validateMove(chessBoard.getBoard(), new Position(SOURCE_POSITION), targetPosition))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이동 가능한 위치 중간에 기물이 위치한 경우 경우 예외를 던진다.")
    @ParameterizedTest
    @ValueSource(strings = {"d3", "d4", "d5", "d6", "d7", "d8", "b3", "a4", "f3", "g4", "h5"})
    void 이동_가능하고_기물이_위치한_경우_예외를_던진다(String target) {
        ChessBoard chessBoard = generateEmptyChessBoard();

        Queen queen = new Queen(Color.WHITE);
        setPiece(chessBoard, SOURCE_POSITION, queen);
        setPiece(chessBoard, target, queen);

        assertThatThrownBy(
                () -> queen.validateMove(chessBoard.getBoard(), new Position(SOURCE_POSITION), new Position(target)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("target 위치에 같은 진영의 기물이 위치한 경우 경우 예외를 던진다.")
    @ParameterizedTest
    @MethodSource("generatePossiblePositions")
    void 이동_가능하고_같은진영의_기물이_위치한_경우_예외를_던진다(String target) {
        ChessBoard chessBoard = generateEmptyChessBoard();

        Queen queen = new Queen(Color.WHITE);
        setPiece(chessBoard, SOURCE_POSITION, queen);
        setPiece(chessBoard, target, new Pawn(Color.WHITE));

        assertThatThrownBy(
                () -> queen.validateMove(chessBoard.getBoard(), new Position(SOURCE_POSITION), new Position(target)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}