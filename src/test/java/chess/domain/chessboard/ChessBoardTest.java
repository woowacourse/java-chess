package chess.domain.chessboard;

import chess.domain.piece.Empty;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static chess.PieceFixture.BLACK_PAWN_THREE_C;
import static chess.PieceFixture.WHITE_PAWN_TWO_B;
import static chess.PositionFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

class ChessBoardTest {

    ChessBoard chessBoard;

    static Stream<Arguments> pawnMoveToEmpty() {
        return Stream.of(
                Arguments.of(TWO_B, THREE_B)
                , Arguments.of(TWO_B, FOUR_B)
                , Arguments.of(SEVEN_B, SIX_B)
                , Arguments.of(SEVEN_B, FIVE_B)
        );
    }

    static Stream<Arguments> pawnMoveToEnemy() {
        return Stream.of(
                Arguments.of(TWO_B, THREE_C)
                , Arguments.of(THREE_C, TWO_B)
        );
    }

    @BeforeEach
    void beforeEach() {
        chessBoard = new ChessBoardFactory().createInitialBoard();
    }

    @Nested
    @DisplayName("moveWithCapture 메서드는")
    class moveWithCapture {

        @ParameterizedTest(name = "빈 칸으로 이동하면 Empty 객체를 반환한다")
        @MethodSource("chess.domain.chessboard.ChessBoardTest#pawnMoveToEmpty")
        void test1(Position from, Position to) {
            assertThat(chessBoard.moveWithCapture(from, to)).isInstanceOf(Empty.class);
        }

        @ParameterizedTest(name = "적이 있는 칸으로 이동하면 적 객체를 반환한다")
        @MethodSource("chess.domain.chessboard.ChessBoardTest#pawnMoveToEnemy")
        void test2() {
            chessBoard = new ChessBoard(Map.of(TWO_B, WHITE_PAWN_TWO_B, THREE_C, BLACK_PAWN_THREE_C));
            assertThat(chessBoard.moveWithCapture(TWO_B, THREE_C)).isEqualTo(BLACK_PAWN_THREE_C);
        }
    }
}
