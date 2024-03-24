package domain.chessboard;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.coordinate.Coordinate;
import domain.piece.Color;
import domain.position.Column;
import domain.position.Row;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

class ChessBoardTest {

    ChessBoard chessBoard = new ChessBoard();

    @Order(1)
    @DisplayName("상대 말을 제거한 뒤 이동한다.")
    @Test
    void moveAfterKill() {
        Coordinate whitePawnStart = createCoordinate(2, 2);
        Coordinate whitePawnDestination = createCoordinate(4, 2);
        chessBoard.playTurn(whitePawnStart, whitePawnDestination, Color.WHITE);

        Coordinate blackPawnStart = createCoordinate(7, 3);
        Coordinate blackPawnDestination = createCoordinate(5, 3);
        chessBoard.playTurn(blackPawnStart, blackPawnDestination, Color.BLACK);

        chessBoard.playTurn(whitePawnDestination, blackPawnDestination, Color.WHITE);

        assertThat(chessBoard.getBoard().get(createCoordinate(5, 3)))
                .isNotNull();
        assertThat(chessBoard.getBoard().get(createCoordinate(4, 2)))
                .isNull();
    }

    @Order(2)
    @DisplayName("빈 칸으로 이동한다.")
    @Test
    void move() {
        Coordinate blackKnightStart = createCoordinate(7, 1);
        Coordinate blackKnightDestination = createCoordinate(6, 1);
        chessBoard.playTurn(blackKnightStart.copied(), blackKnightDestination, Color.BLACK);

        assertThat(chessBoard.getBoard().get(blackKnightStart)).isNull();
        assertThat(chessBoard.getBoard().get(blackKnightDestination)).isNotNull();
    }

    @Order(3)
    @DisplayName("상대의 말을 이동할 수 없다.")
    @Test
    void cantMoveOtherPiece() {
        Coordinate blackPawnStart = createCoordinate(2, 7);
        Coordinate blackPawnDestination = createCoordinate(3, 7);
        assertThatThrownBy(() -> chessBoard.playTurn(blackPawnStart, blackPawnDestination, Color.BLACK))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("상대의 말을 움직일 수 없습니다.");
    }

    @Order(4)
    @DisplayName("이동 경로에 말이 있으면 이동할 수 없다.")
    @Test
    void cantMoveWhenPieceInPath() {
        assertThatThrownBy(() -> chessBoard.playTurn(createCoordinate(8, 1), createCoordinate(2, 1), Color.BLACK))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 경로에 말이 존재합니다.");
    }

    private Coordinate createCoordinate(int row, int column) {
        return new Coordinate(new Row(row), new Column(column));
    }
}
