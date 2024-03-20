package chessboard;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import coordinate.Coordinate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import piece.Color;
import position.Column;
import position.Row;

class ChessBoardTest {

    ChessBoard chessBoard = new ChessBoard();

    @Order(1)
    @DisplayName("상대 말을 제거한 뒤 이동한다.")
    @Test
    void moveAfterKill() {
        Coordinate whitePawnStart = createCoordinate(6, 1);
        Coordinate whitePawnDestination = createCoordinate(4, 1);
        chessBoard.playTurn(whitePawnStart, whitePawnDestination, Color.WHITE);

        Coordinate blackPawnStart = createCoordinate(1, 0);
        Coordinate blackPawnDestination = createCoordinate(3, 0);
        chessBoard.playTurn(blackPawnStart, blackPawnDestination, Color.BLACK);

        chessBoard.playTurn(whitePawnDestination, blackPawnDestination, Color.WHITE);

        assertThat(chessBoard.getBoard().get(createCoordinate(3, 0)))
                .isNotNull();
        assertThat(chessBoard.getBoard().get(createCoordinate(4, 1)))
                .isNull();
    }

    @Order(2)
    @DisplayName("빈 칸으로 이동한다.")
    @Test
    void move() {
        Coordinate blackKnightStart = createCoordinate(0, 1);
        Coordinate blackKnightDestination = createCoordinate(2, 0);
        chessBoard.playTurn(blackKnightStart.copied(), blackKnightDestination, Color.BLACK);

        assertThat(chessBoard.getBoard().get(blackKnightStart)).isNull();
        assertThat(chessBoard.getBoard().get(blackKnightDestination)).isNotNull();
    }

    @Order(3)
    @DisplayName("상대의 말을 이동할 수 없다.")
    @Test
    void cantMoveOtherPiece() {
        Coordinate blackPawnStart = createCoordinate(1, 7);
        Coordinate blackPawnDestination = createCoordinate(3, 7);
        assertThatThrownBy(() -> chessBoard.playTurn(blackPawnStart, blackPawnDestination, Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("상대의 말을 움직일 수 없습니다.");
    }

    @Order(4)
    @DisplayName("이동 경로에 말이 있으면 이동할 수 없다.")
    @Test
    void cantMoveWhenPieceInPath() {
        assertThatThrownBy(() -> chessBoard.playTurn(createCoordinate(0, 0), createCoordinate(5, 0), Color.BLACK))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 경로에 말이 존재합니다.");
    }

    private Coordinate createCoordinate(int row, int column) {
        return new Coordinate(new Row(row), new Column(column));
    }
}
