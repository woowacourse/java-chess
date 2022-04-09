package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KingTest {

    @DisplayName("올바른 이동")
    @Test
    void move() {
        // given
        Position to = new Position("a1");
        Position from = new Position("a2");

        King king = new King(Team.BLACK);
        Bishop toPiece = new Bishop(Team.WHITE);
        // then
        assertThatNoException().isThrownBy(() -> king.movable(to, from, toPiece));
    }

    @DisplayName("올바르지 않은 이동")
    @Test
    void move_x() {
        // given
        Position to = new Position("a1");
        Position from = new Position("a5");

        King king = new King(Team.BLACK);
        Bishop toPiece = new Bishop(Team.WHITE);

        // then
        assertThatThrownBy(() -> king.movable(to, from, toPiece)).hasMessageContaining("움직일 수 있는 방향이 아닙니다.");
    }

    @DisplayName("이름")
    @ParameterizedTest
    @CsvSource(value = {"BLACK,king"})
    void name(Team team, String expect) {
        // given
        King king = new King(team);

        // then
        assertThat(king.getName()).isEqualTo(expect);
    }


    @DisplayName("킹의 방향을 체크한다.")
    @ParameterizedTest
    @CsvSource(value = {"a2,a1,SOUTH", "a1,a2,NORTH"})
    void findDirection(Position from, Position to, Direction direction) {
        // given
        King king = new King(Team.BLACK);

        // when
        Direction find = king.findDirection(from, to);

        // then
        assertThat(find).isEqualTo(direction);
    }
}