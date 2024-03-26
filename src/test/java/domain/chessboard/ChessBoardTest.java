package domain.chessboard;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.coordinate.Coordinate;
import domain.coordinate.Position;
import domain.piece.BlackPawn;
import domain.piece.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessBoardTest {

    ChessBoard chessBoard;
    Coordinate start;
    Coordinate destination;

    @BeforeEach
    void setUp() {
        chessBoard = new ChessBoard();
    }

    @DisplayName("이동할 위치에 말이 존재해야 한다.")
    @Test
    void pieceExistence() {
        start = new Coordinate(2, 0);
        destination = new Coordinate(3, 0);

        assertThatThrownBy(() -> chessBoard.movePiece(start, destination, Color.BLACK))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이동할 말이 존재하지 않습니다.");
    }

    @DisplayName("색상이 다른 말을 이동할 수 없다.")
    @Test
    void cantMoveOtherColorPiece() {
        start = new Coordinate(6, 0);
        destination = new Coordinate(7, 0);

        assertThatThrownBy(() -> chessBoard.movePiece(start, destination, Color.BLACK))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("색상이 다른 말을 움직일 수 없습니다.");
    }

    @DisplayName("목적지에 같은 색상의 말이 존재하면 이동할 수 없다.")
    @Test
    void cantMoveWhenSameColorPieceInDestination() {
        // 흰색 Rook을 흰색 Pawn이 있는 바로 위 칸으로 이동한다.
        start = new Coordinate(7, 0);
        destination = new Coordinate(6, 0);

        assertThatThrownBy(() -> chessBoard.movePiece(start, destination, Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("같은 색의 말이 있는 곳으로는 이동할 수 없습니다.");
    }

    @DisplayName("목적지까지의 경로 상에 다른 말이 존재하면 이동할 수 없다.")
    @Test
    void cantMoveWhenPieceInPath() {
        // 흑색 Rook을 아래로 두 칸 이동시킨다. 이때,경로상에 흑색 Pawn이 존재한다.
        start = new Coordinate(0, 0);
        destination = new Coordinate(2, 0);

        assertThatThrownBy(() -> chessBoard.movePiece(start, destination, Color.BLACK))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 경로에 말이 존재합니다.");
    }

    /**
     RNBQKBNR
     .PPPPPPP
     ........
     ........
     .P......
     ........
     p.pppppp
     rnbqkbnr
     */
    @DisplayName("상대의 말을 잡고 그 위치로 이동한다.")
    @Test
    void moveAfterKill() {
        // 왼쪽에서 두 번째 흰색 폰을 두 칸 앞으로 이동시킨다.
        chessBoard.movePiece(new Coordinate(6, 1), new Coordinate(4, 1), Color.WHITE);

        // 맨 왼쪽의 검정 폰을 두 칸 앞으로 이동시킨다.
        chessBoard.movePiece(new Coordinate(1, 0), new Coordinate(3, 0), Color.BLACK);

        // 이동한 검정 폰은 오른쪽 아래 대각선에 있는 흰색 폰을 잡고 이동할 수 있다.
        chessBoard.movePiece(new Coordinate(3, 0), new Coordinate(4, 1), Color.BLACK);

        assertThat(chessBoard.getBoard().get(4).getPiece(Position.of(1))).isInstanceOf(BlackPawn.class);
    }
}
