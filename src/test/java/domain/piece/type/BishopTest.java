package domain.piece.type;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.board.Square;
import domain.piece.Camp;
import domain.piece.type.unrestricted.Bishop;

class BishopTest {

    @Test
    @DisplayName("bishop이 이동할 수 있는 칸의 좌표를 반환한다.")
    void bishopMoveTest() {
        Bishop bishop = new Bishop(Camp.WHITE, Type.BISHOP);
        assertThat(bishop.fetchMovePath(Square.of(1,3), Square.of(5,7))).contains(
                Square.of(2,4),
                Square.of(3,5),
                Square.of(4,6),
                Square.of(5,7)
        );
    }

    @Test
    @DisplayName("bishop이 이동할 수 있는 칸의 좌표를 반환한다.")
    void bishopMoveFailTest() {
        Bishop bishop = new Bishop(Camp.WHITE, Type.BISHOP);
        assertThatThrownBy(() -> bishop.fetchMovePath(Square.of(1, 3), Square.of(1, 4)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("움직일 수 없는 경로입니다.");
    }

    @Test
    @DisplayName("경로에 있는 기물들이 없고, targetSquare에 같은편 기물이 없으면 true를 반환한다.")
    void canMove() {
        Bishop whiteBishop = new Bishop(Camp.WHITE, Type.BISHOP);
        Map<Square,Camp> pathInfo = new HashMap<>();
        Square targetSquare = Square.of(7, 7);
        pathInfo.put(targetSquare, Camp.BLACK);
        pathInfo.put(Square.of(6,6), Camp.NONE);
        pathInfo.put(Square.of(5,5), Camp.NONE);
        pathInfo.put(Square.of(4,4), Camp.NONE);
        pathInfo.put(Square.of(3,3), Camp.NONE);

        boolean result = whiteBishop.canMove(pathInfo, targetSquare);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("경로에 있는 기물이 하나라도 있으면 false를 반환한다.")
    void canMoveFailByPath() {
        Bishop whiteBishop = new Bishop(Camp.WHITE, Type.BISHOP);
        Map<Square,Camp> pathInfo = new HashMap<>();
        Square targetSquare = Square.of(7, 7);
        pathInfo.put(targetSquare, Camp.BLACK);
        pathInfo.put(Square.of(6,6), Camp.NONE);
        pathInfo.put(Square.of(5,5), Camp.NONE);
        pathInfo.put(Square.of(4,4), Camp.WHITE);
        pathInfo.put(Square.of(3,3), Camp.NONE);

        boolean result = whiteBishop.canMove(pathInfo, targetSquare);

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("경로에 있는 기물이 없지만 targetSquare에 같은진영의 기물이 있으면 false를 반환한다.")
    void canMoveFailByTarget() {
        Bishop whiteBishop = new Bishop(Camp.WHITE, Type.BISHOP);
        Map<Square,Camp> pathInfo = new HashMap<>();
        Square targetSquare = Square.of(7, 7);
        pathInfo.put(targetSquare, Camp.WHITE);
        pathInfo.put(Square.of(6,6), Camp.NONE);
        pathInfo.put(Square.of(5,5), Camp.NONE);
        pathInfo.put(Square.of(4,4), Camp.NONE);
        pathInfo.put(Square.of(3,3), Camp.NONE);

        boolean result = whiteBishop.canMove(pathInfo, targetSquare);

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("경로에 있는 기물이 없고 targetSquare에 다른진영의 기물이 있으면 true를 반환한다.")
    void canMoveEatEnemy() {
        Bishop whiteBishop = new Bishop(Camp.WHITE, Type.BISHOP);
        Map<Square,Camp> pathInfo = new HashMap<>();
        Square targetSquare = Square.of(7, 7);
        pathInfo.put(targetSquare, Camp.BLACK);
        pathInfo.put(Square.of(6,6), Camp.NONE);
        pathInfo.put(Square.of(5,5), Camp.NONE);
        pathInfo.put(Square.of(4,4), Camp.NONE);
        pathInfo.put(Square.of(3,3), Camp.NONE);

        boolean result = whiteBishop.canMove(pathInfo, targetSquare);

        assertThat(result).isTrue();
    }
}
