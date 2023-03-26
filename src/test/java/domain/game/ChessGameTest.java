package domain.game;

import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.Position;
import domain.piece.Side;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ChessGameTest {
    private Map<Position, Piece> chessBoard;
    private ChessGame chessGame;

    @BeforeEach
    void setUp() {
        chessBoard = new ChessBoardGenerator().generate();
        chessGame = new ChessGame(new Board(chessBoard), Side.WHITE, GameState.RUN);
    }

    @DisplayName("b2b4로 입력이 들어오면 white pawn은 b4로 움직인다.")
    @Test
    void moveTest() {
        Piece sourcePiece = chessBoard.get(Position.of("b", "2"));
        //when
        chessGame.move(Position.of("b", "2"), Position.of("b", "4"));
        //then
        assertThat(chessBoard.get(Position.of("b", "4"))).isEqualTo(sourcePiece);
    }

    @DisplayName("white진영 순서에서 black진영의 말을 움직이려고 하면 예외가 발생한다.")
    @Test
    void whiteInvalidTurnExceptionTest() {
        //then
        assertThatThrownBy(() -> chessGame.move(Position.of("b", "7"), Position.of("b", "6")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("다른 진영의 말은 움직일 수 없습니다.");
    }

    @DisplayName("black진영 순서에서 white진영의 말을 움직이려고 하면 예외가 발생한다.")
    @Test
    void blackInvalidTurnExceptionTest() {
        //given
        chessGame.move(Position.of("b", "2"), Position.of("b", "3"));
        //when
        assertThatThrownBy(() -> chessGame.move(Position.of("b", "3"), Position.of("b", "4")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("다른 진영의 말은 움직일 수 없습니다.");
    }

    @DisplayName("움직이려는 말이 empty면 예외가 발생한다.")
    @Test
    void moveEmptyPieceExceptionTest() {
        //given
        assertThatThrownBy(() -> chessGame.move(Position.of("b", "3"), Position.of("b", "4")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Position.of("b", "3") + "에 움직일 수 있는 말이 없습니다.");
    }

    /**
     * RNBQKBNR
     * PPPPPPPP
     * ........
     * ........
     * ........
     * ........
     * pppppppp
     * rnbqkbnr
     */
    @DisplayName("target position에 king이 있고, 유효한 움직임이면 게임은 종료된다.")
    @Test
    void isKingDeadTest() {
        //given
        chessBoard.put(Position.of("d", "2"), new Pawn(Side.BLACK));
        Board board = new Board(chessBoard);
        chessGame = new ChessGame(board, Side.WHITE, GameState.RUN);
        chessGame.move(Position.of("b", "2"), Position.of("b", "3"));
        chessGame.move(Position.of("d", "2"), Position.of("e", "1"));
        //when
        assertThat(chessGame.isPlayable()).isEqualTo(false);
        //then
    }

    @DisplayName("target position에 king이 없고, 유효한 움직임이면 게임은 종료되지 않는다.")
    @Test
    void kingAliveTest() {
        chessGame.move(Position.of("b", "2"), Position.of("b", "3"));
        assertThat(chessGame.isPlayable()).isEqualTo(true);
    }

    @DisplayName("왕이 죽은 경우 왕이 살아있는 진영이 승리한다.")
    @Test
    void calculateWinnerWhenKingDeadTest() {
        //given
        chessBoard.put(Position.of("e", "1"), new Pawn(Side.BLACK));
        chessGame = new ChessGame(new Board(chessBoard), Side.WHITE, GameState.KING_DEAD);
        //when
        Side winSide = chessGame.calculateWinner();
        //then
        assertThat(winSide).isEqualTo(Side.BLACK);
    }

}