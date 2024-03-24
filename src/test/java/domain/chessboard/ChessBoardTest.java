package domain.chessboard;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.coordinate.Coordinate;
import domain.piece.BlackPawn;
import domain.piece.Knight;
import domain.piece.WhitePawn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessBoardTest {

    ChessBoard chessBoard;

    @BeforeEach
    void setUp() {
        chessBoard = new ChessBoard();
    }

    @DisplayName("흰색 말 부터 이동을 시작해야 한다.")
    @Test
    void startTest() {
        assertThatThrownBy(() -> movePieceInBoard(1, 1, 3, 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("상대의 말을 이동할 수 없습니다.");
    }

    @DisplayName("같은 색의 말을 두 번 연속 이동할 수 없다.")
    @Test
    void moveSameColorTwice() {
        movePieceInBoard(6, 2, 4, 2);
        assertThatThrownBy(() -> movePieceInBoard(6, 3, 5, 3))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("상대의 말을 이동할 수 없습니다.");
    }


    /*
    RNBQKBNR
    PPPPPPPP
    ........
    ........
    ........
    n.......
    pppppppp
    r.bqkbnr
    */
    @DisplayName("나이트는 목적지만 비어있으면 이동할 수 있다.")
    @Test
    void moveToEmptyCoordinate() {
        movePieceInBoard(7, 1, 5, 0);
        assertThat(chessBoard.getBoard().get(new Coordinate(7, 1))).isNull();
        assertThat(chessBoard.getBoard().get(new Coordinate(5, 0))).isInstanceOf(Knight.class);
    }

    /*
     RNBQKBNR
     .PPPPPPP
     ........
     ........
     pP......
     ........
     ..pppppp
     rnbqkbnr
    */
    @DisplayName("상대 말을 잡고 이동한다.")
    @Test
    void moveAfterKill() {
        movePieceInBoard(6, 1, 4, 1);
        movePieceInBoard(1, 0, 3, 0);
        movePieceInBoard(6, 0, 4, 0);
        movePieceInBoard(3, 0, 4, 1);

        assertThat(chessBoard.getBoard().get(new Coordinate(4, 0))).isInstanceOf(WhitePawn.class);
        assertThat(chessBoard.getBoard().get(new Coordinate(4, 1))).isInstanceOf(BlackPawn.class);
        assertThat(chessBoard.getBoard().get(new Coordinate(1, 0))).isNull();
        assertThat(chessBoard.getBoard().get(new Coordinate(6, 0))).isNull();
        assertThat(chessBoard.getBoard().get(new Coordinate(6, 1))).isNull();
    }

    @DisplayName("목적지에 같은 색의 말이 있으면 이동할 수 없다.")
    @Test
    void cantMoveWhenSameColorPieceIsInDestination() {
        // 흰색 퀸을, 흰색 폰이 있는 왼쪽 대각선 위로 이동시킨다.
        assertThatThrownBy(() -> movePieceInBoard(7, 3, 6, 2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("같은 색의 말이 있는 곳으로는 이동할 수 없습니다.");
    }

    @DisplayName("이동 경로에 말이 있으면 이동할 수 없다.")
    @Test
    void cantMoveWhenPieceIsInPath() {
        // 흰색 룩을, 2칸 위로 이동시킨다.
        // 흰색 룩의 1칸 위에 폰이 있기에 이동할 수 없다.
        assertThatThrownBy(() -> movePieceInBoard(7, 0, 5, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 경로에 말이 존재합니다.");
    }

    @DisplayName("해당 말이 이동할 수 없는 위치를 지정한다.")
    @Test
    void cantMoveWhenPieceCannotMove() {
        // 흰색 비숍을 바로 위칸으로 이동시킨다.
        // 비숍은 대각선만 이동 가능하기에, 이동할 수 없는 위치이다.
        assertThatThrownBy(() -> movePieceInBoard(7, 2, 6, 2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동할 수 없는 방향입니다.");
    }

    private void movePieceInBoard(int startRow, int startColumn, int destinationRow, int destinationColumn) {
        Coordinate start = new Coordinate(startRow, startColumn);
        Coordinate destination = new Coordinate(destinationRow, destinationColumn);

        chessBoard.playTurn(start, destination);
    }
}
