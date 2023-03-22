package domain.piece.slider;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.board.Square;
import domain.piece.Camp;
import domain.piece.empty.Empty;
import domain.piece.pawn.Pawn;

class BishopTest {

    @Test
    @DisplayName("bishop이 이동할 수 있는 칸의 좌표를 반환한다.")
    void bishopMoveTest() {
        Bishop bishop = new Bishop(Camp.WHITE);
        assertThat(bishop.fetchMovePath(new Square(1,3), new Square(5,7))).contains(
                new Square(2,4),
                new Square(3,5),
                new Square(4,6),
                new Square(5,7)
        );
    }

    @Test
    @DisplayName("bishop이 이동할 수 있는 칸의 좌표를 반환한다.")
    void bishopMoveFailTest() {
        Bishop bishop = new Bishop(Camp.WHITE);
        assertThatThrownBy(() -> bishop.fetchMovePath(new Square(1, 3), new Square(1, 4)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("움직일 수 없는 경로입니다.");
    }

    @Test
    @DisplayName("경로에 있는 기물들이 없고, targetSquare에 같은편 기물이 없으면 true를 반환한다.")
    void canMove() {
        Bishop whiteBishop = new Bishop(Camp.WHITE);
        Map<Square, Slider> pathInfo = new HashMap<>();
        Square targetSquare = new Square(7, 7);
        pathInfo.put(targetSquare, new Pawn(Camp.BLACK));
        pathInfo.put(new Square(6,6), Empty.getInstance());
        pathInfo.put(new Square(5,5), Empty.getInstance());
        pathInfo.put(new Square(4,4), Empty.getInstance());
        pathInfo.put(new Square(3,3), Empty.getInstance());

        boolean result = whiteBishop.canMove(pathInfo, targetSquare);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("경로에 있는 기물이 하나라도 있으면 false를 반환한다.")
    void canMoveFailByPath() {
        Bishop whiteBishop = new Bishop(Camp.WHITE);
        Map<Square,Camp> pathInfo = new HashMap<>();
        Square targetSquare = new Square(7, 7);
        pathInfo.put(targetSquare, Camp.BLACK);
        pathInfo.put(new Square(6,6), Camp.EMPTY);
        pathInfo.put(new Square(5,5), Camp.EMPTY);
        pathInfo.put(new Square(4,4), Camp.WHITE);
        pathInfo.put(new Square(3,3), Camp.EMPTY);

        boolean result = whiteBishop.canMove(pathInfo, targetSquare);

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("경로에 있는 기물이 없지만 targetSquare에 같은진영의 기물이 있으면 false를 반환한다.")
    void canMoveFailByTarget() {
        Bishop whiteBishop = new Bishop(Camp.WHITE);
        Map<Square,Camp> pathInfo = new HashMap<>();
        Square targetSquare = new Square(7, 7);
        pathInfo.put(targetSquare, Camp.WHITE);
        pathInfo.put(new Square(6,6), Camp.EMPTY);
        pathInfo.put(new Square(5,5), Camp.EMPTY);
        pathInfo.put(new Square(4,4), Camp.EMPTY);
        pathInfo.put(new Square(3,3), Camp.EMPTY);

        boolean result = whiteBishop.canMove(pathInfo, targetSquare);

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("경로에 있는 기물이 없고 targetSquare에 다른진영의 기물이 있으면 true를 반환한다.")
    void canMoveEatEnemy() {
        Bishop whiteBishop = new Bishop(Camp.WHITE);
        Map<Square,Camp> pathInfo = new HashMap<>();
        Square targetSquare = new Square(7, 7);
        pathInfo.put(targetSquare, Camp.BLACK);
        pathInfo.put(new Square(6,6), Camp.EMPTY);
        pathInfo.put(new Square(5,5), Camp.EMPTY);
        pathInfo.put(new Square(4,4), Camp.EMPTY);
        pathInfo.put(new Square(3,3), Camp.EMPTY);

        boolean result = whiteBishop.canMove(pathInfo, targetSquare);

        assertThat(result).isTrue();
    }
}
