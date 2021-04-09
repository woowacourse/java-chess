package chess.domain.piece.strategy;

import chess.domain.board.Board;
import chess.domain.board.InitBoardInitializer;
import chess.domain.board.Square;
import chess.domain.board.TestBoardInitializer;
import chess.domain.order.MoveOrder;
import chess.domain.piece.Pawn;
import chess.domain.piece.attribute.Color;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

class PawnMoveStrategyTest {
    private Board board;

    @BeforeEach
    void setUp() {
        board = InitBoardInitializer.getBoard();
    }

    @DisplayName("행마에 대한 검증 - 직선")
    @ParameterizedTest
    @CsvSource({"a2, a3, WHITE", "b7, b6, BLACK"})
    void canMoveStraightDirection(String from, String to, Color color) {
        Pawn pawn = new Pawn(color);
        MoveOrder moveOrder = board.createMoveOrder(Position.of(from), Position.of(to));
        assertThat(pawn.canMove(moveOrder)).isEqualTo(true);
    }

    @DisplayName("대각선에 있는 말을 잡을 수 있는지 테스트")
    @ParameterizedTest
    @CsvSource({"WHITE,a2,b3", "WHITE,e6,d7", "BLACK,b5,c4", "BLACK,h5,g4"})
    void pawnKillMoveTest(Color color, String from, String to) {
        Position fromPosition = Position.of(from);
        Position toPosition = Position.of(to);
        Pawn oppositePawn = new Pawn(color.opposite());

        Square fromSquare = new Square(fromPosition, new Pawn(color));
        Square toSquare = new Square(toPosition, oppositePawn);

        Board testBoard = TestBoardInitializer.createTestBoard(Arrays.asList(fromSquare, toSquare));
        testBoard.move(fromPosition, toPosition);

        boolean hasPiece = testBoard.getAliveSquares().stream()
                .anyMatch(square -> square.isSamePosition(fromPosition));
        assertThat(hasPiece).isFalse();
    }

    @DisplayName("첫 움직임일 때 폰이 2칸을 이동할 수 있는지")
    @ParameterizedTest
    @CsvSource({"c2, c4, WHITE", "g7, g5, BLACK"})
    void pawnMoveOverTwoSquaresAtFirstTurn(String from, String to, Color color) {
        Board testBoard = TestBoardInitializer.createTestBoard(Arrays.asList(new Square(Position.of(from), new Pawn(color))));

        assertThatCode(() -> testBoard.move(Position.of(from), Position.of(to)))
                .doesNotThrowAnyException();
    }

    @DisplayName("대각선에 말이 없을 때 대각선으로 이동하려하면 예외가 발생하는지")
    @Test
    void throwExceptionWhenDiagonalIsEmpty() {
        assertThatThrownBy(() -> board.move(Position.of("a2"), Position.of("b3")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("상대 말을 잡을 때에만 대각선으로 움직일 수 있습니다.");
    }

    @DisplayName("직선으로 3칸 이상 이동할 경우 예외")
    @ParameterizedTest
    @CsvSource({"c2, c5, WHITE", "g7, g4, BLACK"})
    void throwExceptionWhenMoveOverThreeSquares(String from, String to, Color color) {
        Pawn pawn = new Pawn(color);
        MoveOrder moveOrder = board.createMoveOrder(Position.of(from), Position.of(to));
        assertThatThrownBy(() -> pawn.canMove(moveOrder))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("폰이 움직일 수 있는 범위를 벗어났습니다.");
    }

    @DisplayName("첫 움직임이 아닐 때 2칸 이상 이동할 경우 예외")
    @ParameterizedTest
    @CsvSource({"c3, c5, WHITE", "g6, g4, BLACK"})
    void throwExceptionWhenMoveOverTwoSquaresNotFirstTurn(String from, String to, Color color) {
        Board testBoard = TestBoardInitializer.createTestBoard(Arrays.asList(new Square(Position.of(from), new Pawn(color))));

        assertThatThrownBy(() -> testBoard.move(Position.of(from), Position.of(to)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("폰이 움직일 수 있는 범위를 벗어났습니다.");
    }
}