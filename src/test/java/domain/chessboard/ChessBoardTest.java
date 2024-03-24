package domain.chessboard;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.coordinate.Coordinate;
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
                .isNotNull();
        assertThat(chessBoard.getBoard().get(Coordinate.from("b4")))
                .isNull();
    }

    @DisplayName("빈 칸으로 이동한다.")
    @Test
    void move() {
        ChessBoard chessBoard = new ChessBoard();

        Coordinate blackKnightStart = Coordinate.from("A2");
        Coordinate blackKnightDestination = Coordinate.from("A4");
        chessBoard.playTurn(blackKnightStart.copied(), blackKnightDestination);

        assertThat(chessBoard.getBoard().get(blackKnightStart)).isNull();
        assertThat(chessBoard.getBoard().get(blackKnightDestination)).isNotNull();
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
