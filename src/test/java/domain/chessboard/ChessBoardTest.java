package domain.chessboard;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.coordinate.Coordinate;
import domain.piece.Blank;
import domain.piece.Knight;
import domain.piece.Pawn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

class ChessBoardTest {

    @DisplayName("상대 말을 제거한 뒤 이동한다.")
    @Test
    void moveAfterKill() {
        ChessBoard chessBoard = new ChessBoard();

        Coordinate whitePawnStart = Coordinate.from("b2");
        Coordinate whitePawnDestination = Coordinate.from("b4");
        chessBoard.playTurn(whitePawnStart, whitePawnDestination);

        Coordinate blackPawnStart = Coordinate.from("C7");
        Coordinate blackPawnDestination = Coordinate.from("C5");
        chessBoard.playTurn(blackPawnStart, blackPawnDestination);

        chessBoard.playTurn(whitePawnDestination, blackPawnDestination);

        assertThat(chessBoard.getBoard().get(Coordinate.from("C5")))
                .isInstanceOf(Pawn.class);
        assertThat(chessBoard.getBoard().get(Coordinate.from("b4"))).isInstanceOf(Blank.class);
    }

    @DisplayName("빈 칸으로 이동한다.")
    @Test
    void move() {
        ChessBoard chessBoard = new ChessBoard();

        Coordinate knightStart = Coordinate.from("b1");
        Coordinate knightDestination = Coordinate.from("a3");
        chessBoard.playTurn(knightStart.copied(), knightDestination);

        assertThat(chessBoard.getBoard().get(knightStart)).isInstanceOf(Blank.class);
        assertThat(chessBoard.getBoard().get(knightDestination)).isInstanceOf(Knight.class);
    }

    @DisplayName("상대의 말을 이동할 수 없다.")
    @Test
    void cantMoveOtherPiece() {
        ChessBoard chessBoard = new ChessBoard();

        Coordinate blackPawnStart = Coordinate.from("G7");
        Coordinate blackPawnDestination = Coordinate.from("G6");
        assertThatThrownBy(() -> chessBoard.playTurn(blackPawnStart, blackPawnDestination))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("상대의 말을 움직일 수 없습니다.");
    }

    @Order(4)
    @DisplayName("이동 경로에 말이 있으면 이동할 수 없다.")
    @Test
    void cantMoveWhenPieceInPath() {
        ChessBoard chessBoard = new ChessBoard();

        assertThatThrownBy(() -> chessBoard.playTurn(Coordinate.from("a1"), Coordinate.from("a3")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 경로에 말이 존재합니다.");
    }
}
