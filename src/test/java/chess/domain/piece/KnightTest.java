package chess.domain.piece;

import static chess.domain.BoardFixtures.generateEmptyChessBoard;
import static chess.domain.BoardFixtures.setPiece;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.ChessBoard;
import chess.domain.Color;
import chess.domain.position.Position;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class KnightTest {

    private static final String SOURCE_POSITION = "d4";

    private static Stream<Arguments> generatePossiblePositions() {
        return Stream.of("c2", "b3", "b5", "c6", "e6", "f5", "f3", "e2")
                .map(Arguments::of);
    }

    private static Stream<Arguments> generateImpossiblePositions() {
        return Stream.of("c3", "d3", "e3", "c4", "e4", "c5", "d5", "e5", "b6", "d6", "f6", "b2", "d2", "f2", "b4", "f4")
                .map(Arguments::of);
    }

    @DisplayName("이동 가능한 경우 예외를 던지지 않는다.")
    @ParameterizedTest
    @MethodSource("generatePossiblePositions")
    void 이동_가능한_경우_예외를_던지지_않는다(String target) {
        ChessBoard chessBoard = generateEmptyChessBoard();

        Knight knight = new Knight(Color.WHITE);
        setPiece(chessBoard, SOURCE_POSITION, knight);

        assertDoesNotThrow(
                () -> knight.validateMove(chessBoard.getBoard(), new Position(SOURCE_POSITION), new Position(target)));
    }

    @DisplayName("이동 불가능한 위치인 경우 예외를 던진다.")
    @ParameterizedTest
    @MethodSource("generateImpossiblePositions")
    void 이동_불가능한_경우_예외를_던진다(String target) {
        ChessBoard chessBoard = generateEmptyChessBoard();

        Knight knight = new Knight(Color.WHITE);
        setPiece(chessBoard, SOURCE_POSITION, knight);

        assertThatThrownBy(
                () -> knight.validateMove(chessBoard.getBoard(), new Position(SOURCE_POSITION), new Position(target)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("target 위치에 같은 진영의 기물이 위치한 경우 경우 예외를 던진다.")
    @ParameterizedTest
    @MethodSource("generatePossiblePositions")
    void 이동_가능하고_같은진영의_기물이_위치한_경우_예외를_던진다(String target) {
        ChessBoard chessBoard = generateEmptyChessBoard();

        Knight knight = new Knight(Color.WHITE);
        setPiece(chessBoard, SOURCE_POSITION, knight);
        setPiece(chessBoard, target, new Pawn(Color.WHITE));

        assertThatThrownBy(
                () -> knight.validateMove(chessBoard.getBoard(), new Position(SOURCE_POSITION), new Position(target)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}