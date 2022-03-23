package chess.domain;

import chess.domain.pieces.*;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BoardTest {

    @Test
    @DisplayName("보드는 체스판을 입력받는다")
    void board_createdByGenerator() {
        Board board = new Board(new BoardInitiator());
        assertThat(board).isNotNull();
    }

    @ParameterizedTest
    @MethodSource("rookPositions")
    @DisplayName("rook의 초기 위치를 확인")
    void rooks_initialPosition(final Position position, final Color color) {
        Board board = new Board(new BoardInitiator());
        assertThat(board.piece(position).get()).isEqualTo(new Piece(color, new Rook()));
    }

    private static Stream<Arguments> rookPositions() {
        return Stream.of(
                Arguments.of(
                        Position.of("a1"), Color.WHITE
                ),
                Arguments.of(
                        Position.of("h1"), Color.WHITE
                ),
                Arguments.of(
                        Position.of("a8"), Color.BLACK
                ),
                Arguments.of(
                        Position.of("h8"), Color.BLACK
                )
        );
    }

    @ParameterizedTest
    @MethodSource("knightPositions")
    @DisplayName("knight의 초기 위치를 확인")
    void knights_initialPosition(final Position position, final Color color) {
        Board board = new Board(new BoardInitiator());
        assertThat(board.piece(position).get()).isEqualTo(new Piece(color, new Knight()));
    }

    private static Stream<Arguments> knightPositions() {
        return Stream.of(
                Arguments.of(
                        Position.of("b1"), Color.WHITE
                ),
                Arguments.of(
                        Position.of("g1"), Color.WHITE
                ),
                Arguments.of(
                        Position.of("b8"), Color.BLACK
                ),
                Arguments.of(
                        Position.of("g8"), Color.BLACK
                )
        );
    }

    @ParameterizedTest
    @MethodSource("bishopPositions")
    @DisplayName("bishop의 초기 위치를 확인")
    void bishops_initialPosition(final Position position, final Color color) {
        Board board = new Board(new BoardInitiator());
        assertThat(board.piece(position).get()).isEqualTo(new Piece(color, new Bishop()));
    }

    private static Stream<Arguments> bishopPositions() {
        return Stream.of(
                Arguments.of(
                        Position.of("c1"), Color.WHITE
                ),
                Arguments.of(
                        Position.of("f1"), Color.WHITE
                ),
                Arguments.of(
                        Position.of("c8"), Color.BLACK
                ),
                Arguments.of(
                        Position.of("f8"), Color.BLACK
                )
        );
    }

    @ParameterizedTest
    @MethodSource("queenPositions")
    @DisplayName("queen의 초기 위치를 확인")
    void queens_initialPosition(final Position position, final Color color) {
        Board board = new Board(new BoardInitiator());
        assertThat(board.piece(position).get()).isEqualTo(new Piece(color, new Queen()));
    }

    private static Stream<Arguments> queenPositions() {
        return Stream.of(
                Arguments.of(
                        Position.of("d1"), Color.WHITE
                ),
                Arguments.of(
                        Position.of("d8"), Color.BLACK
                )
        );
    }

    @ParameterizedTest
    @MethodSource("kingPositions")
    @DisplayName("king의 초기 위치를 확인")
    void king_initialPosition(final Position position, final Color color) {
        Board board = new Board(new BoardInitiator());
        assertThat(board.piece(position).get()).isEqualTo(new Piece(color, new King()));
    }

    private static Stream<Arguments> kingPositions() {
        return Stream.of(
                Arguments.of(
                        Position.of("e1"), Color.WHITE
                ),
                Arguments.of(
                        Position.of("e8"), Color.BLACK
                )
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"a2", "b2", "c2", "d2", "e2", "f2", "g2", "h2"})
    @DisplayName("흰색 pawn의 초기 위치를 확인")
    void whitePawn_initialPosition(final String rawPosition) {
        Board board = new Board(new BoardInitiator());
        assertThat(board.piece(Position.of(rawPosition)).get()).isEqualTo(new Piece(Color.WHITE, new Pawn()));
    }

    @ParameterizedTest
    @ValueSource(strings = {"a7", "b7", "c7", "d7", "e7", "f7", "g7", "h7"})
    @DisplayName("검은색 pawn의 초기 위치를 확인")
    void blackPawn_initialPosition(final String rawPosition) {
        Board board = new Board(new BoardInitiator());
        assertThat(board.piece(Position.of(rawPosition)).get()).isEqualTo(new Piece(Color.BLACK, new Pawn()));
    }

    @Test
    @DisplayName("존재하지 않는 source를 움직이면 예외를 발생한다")
    void thrown_sourceNotExist() {
        Board board = new Board(new BoardInitiator());
        assertThatThrownBy(() -> board.move("a3", "a4"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 말이 존재하지 않습니다.");
    }
}
