package domain.piece.slider;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.board.Square;
import domain.piece.Camp;
import domain.piece.Piece;
import domain.piece.nonslider.Empty;
import domain.piece.nonslider.Pawn;

class BishopTest {

    @Test
    @DisplayName("bishop이 이동할 수 있는 칸의 좌표를 반환한다.")
    void bishopMoveTest() {
        Bishop bishop = new Bishop(Camp.WHITE);
        assertThat(bishop.fetchMovableSquares(new Square(1, 3), new Square(5, 7))).contains(
            new Square(2, 4),
            new Square(3, 5),
            new Square(4, 6),
            new Square(5, 7)
        );
    }

    @Test
    @DisplayName("bishop이 이동할 수 있는 칸의 좌표를 반환한다.")
    void bishopMoveFailTest() {
        Bishop bishop = new Bishop(Camp.WHITE);
        assertThatThrownBy(() -> bishop.fetchMovableSquares(new Square(1, 3), new Square(1, 4)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("움직일 수 없는 경로입니다.");
    }

    @Test
    @DisplayName("경로에 있는 기물들이 없고, targetSquare에 같은편 기물이 없으면 true를 반환한다.")
    void canMove() {
        Bishop whiteBishop = new Bishop(Camp.WHITE);
        Map<Square, Piece> pathInfo = new HashMap<>();
        Square targetSquare = new Square(7, 7);
        pathInfo.put(targetSquare, new Pawn(Camp.BLACK));
        pathInfo.put(new Square(6, 6), Empty.getInstance());
        pathInfo.put(new Square(5, 5), Empty.getInstance());
        pathInfo.put(new Square(4, 4), Empty.getInstance());
        pathInfo.put(new Square(3, 3), Empty.getInstance());

        boolean result = whiteBishop.canMove(pathInfo, targetSquare);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("경로에 있는 기물이 하나라도 있으면 에러를 던진다. 반환한다.")
    void canMoveFailByPath() {
        Bishop whiteBishop = new Bishop(Camp.WHITE);
        Map<Square, Piece> pathInfo = new HashMap<>();
        Square targetSquare = new Square(7, 7);
        pathInfo.put(targetSquare, new Pawn(Camp.BLACK));
        pathInfo.put(new Square(6, 6), new Pawn(Camp.BLACK));
        pathInfo.put(new Square(5, 5), new Pawn(Camp.BLACK));
        pathInfo.put(new Square(4, 4), new Pawn(Camp.BLACK));
        pathInfo.put(new Square(3, 3), new Pawn(Camp.BLACK));

        assertThatThrownBy(() -> whiteBishop.canMove(pathInfo, targetSquare));
    }

    @Test
    @DisplayName("경로에 있는 기물이 없지만 targetSquare에 같은진영의 기물이 있으면 false를 반환한다.")
    void canMoveFailByTarget() {
        Bishop whiteBishop = new Bishop(Camp.WHITE);
        Map<Square, Piece> pathInfo = new HashMap<>();
        Square targetSquare = new Square(7, 7);
        pathInfo.put(targetSquare, new Pawn(Camp.WHITE));
        pathInfo.put(new Square(6, 6), Empty.getInstance());
        pathInfo.put(new Square(5, 5), Empty.getInstance());
        pathInfo.put(new Square(4, 4), Empty.getInstance());
        pathInfo.put(new Square(3, 3), Empty.getInstance());

        assertThatThrownBy(() -> whiteBishop.canMove(pathInfo, targetSquare));
    }

    @Test
    @DisplayName("경로에 있는 기물이 없고 targetSquare에 다른진영의 기물이 있으면 true를 반환한다.")
    void canMoveEatEnemy() {
        Bishop whiteBishop = new Bishop(Camp.WHITE);
        Map<Square, Piece> pathInfo = new HashMap<>();
        Square targetSquare = new Square(7, 7);
        pathInfo.put(targetSquare, new Pawn(Camp.BLACK));
        pathInfo.put(new Square(6, 6), Empty.getInstance());
        pathInfo.put(new Square(5, 5), Empty.getInstance());
        pathInfo.put(new Square(4, 4), Empty.getInstance());
        pathInfo.put(new Square(3, 3), Empty.getInstance());

        boolean result = whiteBishop.canMove(pathInfo, targetSquare);

        assertThat(result).isTrue();
    }
}
