package chess.domain.board;

import chess.domain.board.factory.BoardFactory;
import chess.domain.board.position.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.helper.BoardFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BoardTest {

    Board board;

    @BeforeEach
    void boardInit() {
        board = Board.makeNewGame(new BoardFactory());
    }

    @Test
    @DisplayName("move() : 비어있는 출발점에서 시작하면 IllegalArgumentException 가 발생합니다.")
    void test_move_empty_IllegalArgumentException() {

        //given
        final Position emptyPosition = new Position(4, 4);
        final Position to = new Position(1, 1);

        //when & then
        assertThatThrownBy(() -> board.move(emptyPosition, to))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("출발점에 말이 없습니다.");
    }

    @Test
    @DisplayName("move() : Pawn 이동을 확인할 수 있다.")
    void test_movePawn() {
        final Position from = new Position(1, 2);
        final Position to = new Position(1, 4);

        assertTrue(board.chessBoard().containsKey(from));
        assertFalse(board.chessBoard().containsKey(to));

        board.move(from, to);

        assertFalse(board.chessBoard().containsKey(from));
        assertTrue(board.chessBoard().containsKey(to));
    }

    @Test
    @DisplayName("move() : 이동할 때 중간에 기물이 존재한다면 IllegalStateException이 발생합니다.")
    void test_move_validateObstacle_exception() throws Exception {
        //given
        final Position from = new Position(1, 1);
        final Position to = new Position(1, 3);

        //when & then
        assertThatThrownBy(() -> board.move(from, to))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("중간에 다른 기물이 존재합니다.");
    }

    @Test
    @DisplayName("move() : 연속으로 같은 색의 말이 이동할 경우 IllegalArgumentException 가 밸상합니다.")
    void test_move_serialMove_IllegalArgumentException() throws Exception {
        //given
        board.move(new Position(2, 2), new Position(2, 4));

        final Position from = new Position(2, 4);
        final Position to = new Position(2, 5);

        //when & then

        assertThatThrownBy(() -> board.move(from, to))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("차례에 맞는 말을 선택해 주세요");
    }

    @Test
    @DisplayName("bringBackPreviousGame() : 이전에 진행했던 게임을 다시 Board로 만들 수 있습니다.")
    void test_bringBackPreviousGame() throws Exception {
        //given
        final Map<Position, Piece> board = BoardFixture.createBoard();
        final Turn turn = new Turn(Color.BLACK);

        //when
        final Board previousGame = Board.bringBackPreviousGame(board, turn, 1L);

        final Map<Position, Piece> savedChessBoard = previousGame.chessBoard();
        final Turn savedTurn = previousGame.turn();

        //then
        assertAll(
                () -> assertEquals(savedChessBoard, board),
                () -> assertEquals(turn, savedTurn)
        );
    }

    @Test
    @DisplayName("makeNewGame() : 새로운 게임을 만들 수 있다.")
    void test_makeNewGame() throws Exception {
        //when
        final Board newGame = Board.makeNewGame(new BoardFactory());

        final Map<Position, Piece> newChessBoard = newGame.chessBoard();
        final Turn savedTurn = newGame.turn();

        //then
        assertAll(
                () -> assertNotNull(newChessBoard),
                () -> assertEquals(savedTurn, new Turn(Color.WHITE))
        );
    }
}
