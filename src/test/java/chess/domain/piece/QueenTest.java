package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class QueenTest {

    @DisplayName("이동 가능 확인")
    @ParameterizedTest
    @CsvSource(value = {"a1,a5", "a5,a1", "d1,d5", "d5,d1", "a1,c3", "c3,a1", "a3,c1", "c1,a3"})
    void movable(String toValue, String fromValue) {
        // given
        Position to = new Position(toValue);
        Position from = new Position(fromValue);

        Queen queen = new Queen(Team.BLACK);
        Bishop toPiece = new Bishop(Team.WHITE);
        // then
        assertThatNoException().isThrownBy(() -> queen.movable(to, from, toPiece));
    }

    @DisplayName("이동 불가 확인")
    @Test
    void notMovable() {
        // given
        Position to = new Position("a1");
        Position from = new Position("b3");

        Queen queen = new Queen(Team.BLACK);
        Bishop toPiece = new Bishop(Team.WHITE);
        // then
        assertThatThrownBy(() -> queen.movable(to, from, toPiece)).hasMessageContaining("움직일 수 있는 방향이 아닙니다.");
    }

    @DisplayName("이름")
    @ParameterizedTest
    @CsvSource(value = {"BLACK,queen"})
    void name(Team team, String expect) {
        // given
        Queen queen = new Queen(team);

        // then
        assertThat(queen.getName()).isEqualTo(expect);
    }

    @DisplayName("퀸의 방향을 체크한다.")
    @ParameterizedTest
    @CsvSource(value = {"a1,c3,NORTH_EAST", "c3,a1,SOUTH_WEST"})
    void findDirection(Position from, Position to, Direction direction) {
        // given
        Queen queen = new Queen(Team.WHITE);

        // when
        Direction find = queen.findDirection(from, to);

        // then
        assertThat(find).isEqualTo(direction);
    }
}