package chess.domain.attribute;

import static chess.domain.attribute.File.A;
import static chess.domain.attribute.File.B;
import static chess.domain.attribute.File.E;
import static chess.domain.attribute.File.F;
import static chess.domain.attribute.File.G;
import static chess.domain.attribute.File.H;
import static chess.domain.attribute.Rank.EIGHT;
import static chess.domain.attribute.Rank.ONE;
import static chess.domain.attribute.Rank.SEVEN;
import static chess.domain.attribute.Rank.THREE;
import static chess.domain.attribute.Rank.TWO;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SquareTest {

    @DisplayName("현재 위치에서 위로 이동한다.")
    @Test
    void moveUp() {
        Square square = Square.of(F, TWO);

        Assertions.assertThat(square.moveUp()).isEqualTo(Square.of(F, THREE));
    }

    @DisplayName("현재 위치에서 아래로 이동한다.")
    @Test
    void moveDown() {
        Square square = Square.of(F, TWO);

        Assertions.assertThat(square.moveDown()).isEqualTo(Square.of(F, ONE));
    }

    @DisplayName("현재 위치에서 왼쪽으로 이동한다.")
    @Test
    void moveLeft() {
        Square square = Square.of(F, TWO);

        Assertions.assertThat(square.moveLeft()).isEqualTo(Square.of(E, TWO));
    }

    @DisplayName("현재 위치에서 오른쪽으로 이동한다.")
    @Test
    void moveRight() {
        Square square = Square.of(F, TWO);

        Assertions.assertThat(square.moveRight()).isEqualTo(Square.of(G, TWO));
    }

    @DisplayName("현재 위치에서 왼쪽_위로 이동한다.")
    @Test
    void moveLeftUp() {
        Square square = Square.of(F, TWO);

        Assertions.assertThat(square.moveLeftUp()).isEqualTo(Square.of(E, THREE));
    }

    @DisplayName("현재 위치에서 왼쪽_아래로 이동한다.")
    @Test
    void moveLeftDown() {
        Square square = Square.of(F, TWO);

        Assertions.assertThat(square.moveLeftDown()).isEqualTo(Square.of(E, ONE));
    }

    @DisplayName("현재 위치에서 오른쪽_위로 이동한다.")
    @Test
    void moveRightUp() {
        Square square = Square.of(F, TWO);

        Assertions.assertThat(square.moveRightUp()).isEqualTo(Square.of(G, THREE));
    }

    @DisplayName("현재 위치에서 오른쪽_아래로 이동한다.")
    @Test
    void moveRightDown() {
        Square square = Square.of(F, TWO);

        Assertions.assertThat(square.moveRightDown()).isEqualTo(Square.of(G, ONE));
    }

    @DisplayName("위로 이동할 수 있는지 반환한다.")
    @Test
    void canMoveUp() {
        assertAll(
                () -> assertTrue(Square.of(F, ONE)::canMoveUp),
                () -> assertFalse(Square.of(F, EIGHT)::canMoveUp)
        );
    }

    @DisplayName("아래로 이동할 수 있는지 반환한다.")
    @Test
    void canMoveDown() {
        assertAll(
                () -> assertTrue(Square.of(F, EIGHT)::canMoveDown),
                () -> assertFalse(Square.of(F, ONE)::canMoveDown)
        );
    }

    @DisplayName("왼쪽으로 이동할 수 있는지 반환한다.")
    @Test
    void canMoveLeft() {
        assertAll(
                () -> assertTrue(Square.of(H, TWO)::canMoveLeft),
                () -> assertFalse(Square.of(A, TWO)::canMoveLeft)
        );
    }

    @DisplayName("오른쪽으로 이동할 수 있는지 반환한다.")
    @Test
    void canMoveRight() {
        assertAll(
                () -> assertTrue(Square.of(A, TWO)::canMoveRight),
                () -> assertFalse(Square.of(H, TWO)::canMoveRight)
        );
    }

    @DisplayName("왼쪽_아래로 이동할 수 있는지 반환한다.")
    @Test
    void canMoveLeftDown() {
        assertAll(
                () -> assertTrue(Square.of(B, TWO)::canMoveLeftDown),
                () -> assertFalse(Square.of(A, ONE)::canMoveLeftDown)
        );
    }

    @DisplayName("왼쪽_위로 이동할 수 있는지 반환한다.")
    @Test
    void canMoveLeftUp() {
        assertAll(
                () -> assertTrue(Square.of(B, SEVEN)::canMoveLeftUp),
                () -> assertFalse(Square.of(A, EIGHT)::canMoveLeftUp)
        );
    }

    @DisplayName("오른쪽_아래로 이동할 수 있는지 반환한다.")
    @Test
    void canMoveRightDown() {
        assertAll(
                () -> assertTrue(Square.of(F, TWO)::canMoveRightDown),
                () -> assertFalse(Square.of(H, ONE)::canMoveRightDown)
        );
    }

    @DisplayName("오른쪽_위로 이동할 수 있는지 반환한다.")
    @Test
    void canMoveRightUp() {
        assertAll(
                () -> assertTrue(Square.of(G, SEVEN)::canMoveRightUp),
                () -> assertFalse(Square.of(H, EIGHT)::canMoveRightUp)
        );
    }

    /*
    * * * * * * * * 8    * * * * * * * * 8
    * * * * * * * * 7    * * * * * * * * 7
    * * * * * * * * 6    * * * * * * * * 6
    * * * * * * * * 5    * * * * * * * * 5
    * * * * * * * * 4 -> * * * * * * * * 4
    * * * * * * * * 3    * * * * * * * * 3
    * * * * * * * * 2    * * * * * * * * 2
    o * * * * * * * 1    * * * * * * * * 1
    a b c d e f g h      a b c d e f g h
    */
    @DisplayName("움직일 방법을 보내면 해당 칸으로 움직일 수 있는지 없는지를 반환한다.")
    @Test
    void canMove() {
        assertAll(
                () -> assertTrue(Square.of(A, ONE).canMove(Movement.UP)),
                () -> assertFalse(Square.of(A, ONE).canMove(Movement.LEFT)),
                () -> assertFalse(Square.of(A, ONE).canMove(Movement.LEFT_UP)),
                () -> assertFalse(Square.of(A, ONE).canMove(Movement.DOWN)),
                () -> assertFalse(Square.of(A, ONE).canMove(Movement.LEFT_DOWN)),
                () -> assertFalse(Square.of(A, ONE).canMove(Movement.LEFT_LEFT_DOWN))
        );
    }
}
