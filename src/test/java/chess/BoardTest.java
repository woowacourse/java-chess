package chess;

import chess.piece.Piece;
import chess.piece.Rook;
import chess.piece.Team;
import chess.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static chess.position.File.A;
import static chess.position.File.H;
import static chess.position.Rank.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BoardTest {
    private Board board;
    private Rook rook;

    @BeforeEach
    void setup() {
        Map<Position, Piece> pieces = new HashMap<>();
        rook = new Rook(Team.BLACK);
        pieces.put(Position.of(A, ONE), rook);
        board = new Board(pieces);
    }

    @DisplayName("Board객체 생성 테스트")
    @Test
    void constructTest() {
        assertThat(board).isInstanceOf(Board.class);
    }

    @DisplayName("잘못된 위치를 입력했을때 예외가 발생하는지 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"a9", "i3"})
    void invalidPositionInputTest(String input) {
        assertThatThrownBy(() -> Position.of(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("위치 입력값이 올바르지 않습니다.");
    }

    private static Stream<Arguments> inputAndPositionProvider() {
        return Stream.of(
                Arguments.of("A1", Position.of(A, ONE)),
                Arguments.of("H5", Position.of(H, FIVE))
        );
    }

    @DisplayName("File값으로 대문자를 입력했을때에도 올바른 위치를 생성하는지 테스트")
    @ParameterizedTest
    @MethodSource("inputAndPositionProvider")
    void upperCaseTest(String input, Position position) {
        assertThat(Position.of(input)).isEqualTo(position);
    }

    @DisplayName("move메서드를 실행하면 Map이 수정되는지 테스트")
    @Test
    void moveTest() {
        Position from = Position.of(A, ONE);
        Position to = Position.of(A, TWO);
        board.move(from, to);
        assertThat(board.getPiece(to)).isEqualTo(rook);
    }
}
