package domain.chessboard;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.coordinate.Coordinate;
import domain.piece.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

class ChessBoardTest {

    ChessBoard chessBoard = new ChessBoard();

    @Order(1)
    @DisplayName("상대 말을 제거한 뒤 이동한다.")
    @Test
    void moveAfterKill() {
        Coordinate whitePawnStart = Coordinate.from("b2");
        Coordinate whitePawnDestination = Coordinate.from("b4");
        chessBoard.playTurn(whitePawnStart, whitePawnDestination, Color.WHITE);

        Coordinate blackPawnStart = Coordinate.from("C7");
        Coordinate blackPawnDestination = Coordinate.from("C5");
        chessBoard.playTurn(blackPawnStart, blackPawnDestination, Color.BLACK);

        chessBoard.playTurn(whitePawnDestination, blackPawnDestination, Color.WHITE);

        assertThat(chessBoard.getBoard().get(Coordinate.from("C5")))
                .isNotNull();
        assertThat(chessBoard.getBoard().get(Coordinate.from("b4")))
                .isNull();
    }

    @Order(2)
    @DisplayName("빈 칸으로 이동한다.")
    @Test
    void move() {
        Coordinate blackKnightStart = Coordinate.from("A7");
        Coordinate blackKnightDestination = Coordinate.from("A6");
        chessBoard.playTurn(blackKnightStart.copied(), blackKnightDestination, Color.BLACK);

        assertThat(chessBoard.getBoard().get(blackKnightStart)).isNull();
        assertThat(chessBoard.getBoard().get(blackKnightDestination)).isNotNull();
    }

    @Order(3)
    @DisplayName("상대의 말을 이동할 수 없다.")
    @Test
    void cantMoveOtherPiece() {
        Coordinate blackPawnStart = Coordinate.from("G2");
        Coordinate blackPawnDestination = Coordinate.from("G3");
        assertThatThrownBy(() -> chessBoard.playTurn(blackPawnStart, blackPawnDestination, Color.BLACK))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("상대의 말을 움직일 수 없습니다.");
    }

    @Order(4)
    @DisplayName("이동 경로에 말이 있으면 이동할 수 없다.")
    @Test
    void cantMoveWhenPieceInPath() {
        assertThatThrownBy(() -> chessBoard.playTurn(Coordinate.from("a8"), Coordinate.from("a2"), Color.BLACK))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 경로에 말이 존재합니다.");
    }
}
