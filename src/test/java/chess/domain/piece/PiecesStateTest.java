package chess.domain.piece;

import chess.config.BoardConfig;
import chess.domain.board.Board;
import chess.domain.piece.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PiecesStateTest {
    @Test
    @DisplayName("#initialize() : should return initialized Board")
    void initiaize() {
        Board board = Board.initiaize();
        Map<String, String> serialized = board.serialize();
        assertThat(serialized.size()).isEqualTo(32);
        assertPawn(serialized, BoardConfig.LINE_START + 1, "p");
        assertPawn(serialized, BoardConfig.LINE_END - 1, "P");
        assertEdge(serialized, BoardConfig.LINE_START);
        assertEdge(serialized, BoardConfig.LINE_END);
    }

    @ParameterizedTest
    @MethodSource({"getCasesForMovePieceSucceed"})
    void movePieceSucceed(Position from, Position to) {
        PiecesState pieces = PiecesState.initialize();
        pieces = pieces.movePiece(from, to);

        PiecesState testPieces = TestPiecesState.initialize();
        PiecesState movedPieces = testPieces.movePiece(from, to);

        assertThat(pieces).isEqualTo(movedPieces);

    }

    @ParameterizedTest
    @MethodSource({"getCasesForMovePieceFail"})
    void movePieceFail(Position from, Position to) {
        PiecesState pieces = PiecesState.initialize();
        assertThatThrownBy(() ->pieces.movePiece(from, to))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format(PiecesState.CAN_NOT_MOVE_ERROR, from, to));
    }

    @ParameterizedTest
    @MethodSource({"getCasesForIsNotFilled"})
    void isNotFilled(Position position, boolean expected) {
        PiecesState pieces = PiecesState.initialize();
        assertThat(pieces.isNotFilled(position)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource({"getCasesForHasHindranceInBetween"})
    void hasHindranceInBetween(Position from, Position to, boolean expected) {
        PiecesState pieces = PiecesState.initialize();
        boolean hasHindranceInBetween = pieces.hasHindranceInStraightBetween(from, to);
        assertThat(hasHindranceInBetween).isEqualTo(expected);
    }

    private static Stream<Arguments> getCasesForHasHindranceInBetween() {
        return Stream.of(
                Arguments.of(Position.of(1,2), Position.of(1,7), false),
                Arguments.of(Position.of(1,2), Position.of(6,7), false)
//                Arguments.of(Position.of(1,2), Position.of(6,8), false)
        );
    }

    private static Stream<Arguments> getCasesForIsNotFilled() {
        return Stream.of(
                Arguments.of(Position.of(1,1), false),
                Arguments.of(Position.of(1,3), true)
        );
    }

    private static Stream<Arguments> getCasesForMovePieceFail() {
        return Stream.of(
                Arguments.of(Position.of(1,2), Position.of(1,5))
        );
    }

    private static Stream<Arguments> getCasesForMovePieceSucceed() {
        return Stream.of(
                Arguments.of(Position.of(1,2), Position.of(1,3)),
                Arguments.of(Position.of(1,2), Position.of(1,4)),
                Arguments.of(Position.of(2,1), Position.of(3,3))
        );
    }

    private void assertPawn(Map<String, String> serialized, int row, String p) {
        for (int column = BoardConfig.LINE_START; column <= BoardConfig.LINE_END; column++) {
            String position = String.valueOf(column) + String.valueOf(row);
            assertTrue(serialized.containsKey(position));
            String name = serialized.get(position);
            assertThat(name).isEqualTo(p);
        }
    }

    private void assertEdge(Map<String, String> serialized, int row) {
        for (int column = BoardConfig.LINE_START; column <= BoardConfig.LINE_END; column++) {
            String position = String.valueOf(column) + String.valueOf(row);
            assertTrue(serialized.containsKey(position));
        }
    }
}