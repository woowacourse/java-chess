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
import org.junit.jupiter.params.provider.ValueSource;

public class BishopTest {

    private static final Position bishopSourcePosition = new Position("c1");

    private static Stream<Arguments> generatePossiblePositions() {
        return Stream.of("b2", "a3", "d2", "e3", "f4", "g5", "h6")
                .map(Arguments::of);
    }

    private static Stream<Arguments> generateImpossiblePositions() {
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

        board.get(bishopSourcePosition.getRankIndex()).set(bishopSourcePosition.getFileIndex(), bishop);

        assertDoesNotThrow(() -> bishop.validateMove(board, bishopSourcePosition, targetPosition));
    }

    @DisplayName("이동 불가능한 위치인 경우 예외를 던진다.")
    @ParameterizedTest
    @MethodSource("generateImpossiblePositions")
    void 이동_불가능한_경우_예외를_던진다(Position targetPosition) {
        List<List<Piece>> board = BoardFixtures.generateEmptyChessBoard().getBoard();
        Bishop bishop = new Bishop(Color.WHITE);

        board.get(bishopSourcePosition.getRankIndex()).set(bishopSourcePosition.getFileIndex(), bishop);

        Assertions.assertThatThrownBy(() -> bishop.validateMove(board, bishopSourcePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이동 가능한 위치 중간에 기물이 위치한 경우 경우 예외를 던진다.")
    @ParameterizedTest
    @ValueSource(strings = {"a3", "e3", "f4", "g5", "h6"})
    void 이동_가능하고_기물이_위치한_경우_예외를_던진다(String target) {
        List<List<Piece>> board = BoardFixtures.generateInitChessBoard().getBoard();
        Bishop bishop = new Bishop(Color.WHITE);

        board.get(bishopSourcePosition.getRankIndex()).set(bishopSourcePosition.getFileIndex(), bishop);

        Assertions.assertThatThrownBy(() -> bishop.validateMove(board, bishopSourcePosition, new Position(target)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("target 위치에 같은 진영의 기물이 위치한 경우 경우 예외를 던진다.")
    @ParameterizedTest
    @MethodSource("generatePossiblePositions")
    void 이동_가능하고_같은진영의_기물이_위치한_경우_예외를_던진다(Position targetPosition) {
        List<List<Piece>> board = BoardFixtures.generateEmptyChessBoard().getBoard();
        Bishop bishop = new Bishop(Color.WHITE);

        board.get(bishopSourcePosition.getRankIndex()).set(bishopSourcePosition.getFileIndex(), bishop);
        board.get(targetPosition.getRankIndex()).set(targetPosition.getFileIndex(), new Pawn(Color.WHITE));

        Assertions.assertThatThrownBy(() -> bishop.validateMove(board, bishopSourcePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
