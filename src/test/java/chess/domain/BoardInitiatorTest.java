package chess.domain;

import chess.domain.pieces.*;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Map;
import java.util.stream.Stream;

import static chess.domain.Color.*;
import static org.assertj.core.api.Assertions.assertThat;

class BoardInitiatorTest {

    @ParameterizedTest
    @MethodSource("rookPositions")
    @DisplayName("rook의 초기 위치를 확인")
    void rooks_initialPosition(final Position position, final Color color) {
        Map<Position, Piece> pieces = new BoardInitializer().initialize();
        assertThat(pieces.get(position)).isEqualTo(new Piece(color, new Rook()));
    }

    private static Stream<Arguments> rookPositions() {
        return Stream.of(
                Arguments.of(
                        Position.of("a1"), WHITE
                ),
                Arguments.of(
                        Position.of("h1"), WHITE
                ),
                Arguments.of(
                        Position.of("a8"), BLACK
                ),
                Arguments.of(
                        Position.of("h8"), BLACK
                )
        );
    }

    @ParameterizedTest
    @MethodSource("knightPositions")
    @DisplayName("knight의 초기 위치를 확인")
    void knights_initialPosition(final Position position, final Color color) {
        Map<Position, Piece> pieces = new BoardInitializer().initialize();
        assertThat(pieces.get(position)).isEqualTo(new Piece(color, new Knight()));
    }

    private static Stream<Arguments> knightPositions() {
        return Stream.of(
                Arguments.of(
                        Position.of("b1"), WHITE
                ),
                Arguments.of(
                        Position.of("g1"), WHITE
                ),
                Arguments.of(
                        Position.of("b8"), BLACK
                ),
                Arguments.of(
                        Position.of("g8"), BLACK
                )
        );
    }

    @ParameterizedTest
    @MethodSource("bishopPositions")
    @DisplayName("bishop의 초기 위치를 확인")
    void bishops_initialPosition(final Position position, final Color color) {
        Map<Position, Piece> pieces = new BoardInitializer().initialize();
        assertThat(pieces.get(position)).isEqualTo(new Piece(color, new Bishop()));
    }

    private static Stream<Arguments> bishopPositions() {
        return Stream.of(
                Arguments.of(
                        Position.of("c1"), WHITE
                ),
                Arguments.of(
                        Position.of("f1"), WHITE
                ),
                Arguments.of(
                        Position.of("c8"), BLACK
                ),
                Arguments.of(
                        Position.of("f8"), BLACK
                )
        );
    }

    @ParameterizedTest
    @MethodSource("queenPositions")
    @DisplayName("queen의 초기 위치를 확인")
    void queens_initialPosition(final Position position, final Color color) {
        Map<Position, Piece> pieces = new BoardInitializer().initialize();
        assertThat(pieces.get(position)).isEqualTo(new Piece(color, new Queen()));
    }

    private static Stream<Arguments> queenPositions() {
        return Stream.of(
                Arguments.of(
                        Position.of("d1"), WHITE
                ),
                Arguments.of(
                        Position.of("d8"), BLACK
                )
        );
    }

    @ParameterizedTest
    @MethodSource("kingPositions")
    @DisplayName("king의 초기 위치를 확인")
    void king_initialPosition(final Position position, final Color color) {
        Map<Position, Piece> pieces = new BoardInitializer().initialize();
        assertThat(pieces.get(position)).isEqualTo(new Piece(color, new King()));
    }

    private static Stream<Arguments> kingPositions() {
        return Stream.of(
                Arguments.of(
                        Position.of("e1"), WHITE
                ),
                Arguments.of(
                        Position.of("e8"), BLACK
                )
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"a2", "b2", "c2", "d2", "e2", "f2", "g2", "h2"})
    @DisplayName("흰색 pawn의 초기 위치를 확인")
    void whitePawn_initialPosition(final String rawPosition) {
        Map<Position, Piece> pieces = new BoardInitializer().initialize();
        assertThat(pieces.get(Position.of(rawPosition))).isEqualTo(new Piece(WHITE, new Pawn()));
    }

    @ParameterizedTest
    @ValueSource(strings = {"a7", "b7", "c7", "d7", "e7", "f7", "g7", "h7"})
    @DisplayName("검은색 pawn의 초기 위치를 확인")
    void blackPawn_initialPosition(final String rawPosition) {
        Map<Position, Piece> pieces = new BoardInitializer().initialize();
        assertThat(pieces.get(Position.of(rawPosition))).isEqualTo(new Piece(BLACK, new Pawn()));
    }

}
