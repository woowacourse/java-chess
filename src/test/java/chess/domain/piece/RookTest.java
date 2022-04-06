package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RookTest {

    @DisplayName("이동 가능 확인")
    @ParameterizedTest
    @CsvSource(value = {"a1,a5", "a5,a1", "d1,d5", "d5,d1"})
    void movable(String toValue, String fromValue) {
        // given
        Position to = new Position(toValue);
        Position from = new Position(fromValue);

        Rook rook = new Rook(Team.BLACK);
        Bishop toPiece = new Bishop(Team.WHITE);
        // then
        assertThatNoException().isThrownBy(() -> rook.movable(to, from, toPiece));
    }

    @DisplayName("이동 불가 확인")
    @Test
    void notMovable() {
        // given
        Position to = new Position("a1");
        Position from = new Position("b3");

        Rook rook = new Rook(Team.BLACK);
        Bishop toPiece = new Bishop(Team.WHITE);
        // then
        assertThatThrownBy(() -> rook.movable(to, from, toPiece)).hasMessageContaining("움직일 수 있는 방향이 아닙니다.");
    }

    @DisplayName("이름")
    @ParameterizedTest
    @CsvSource(value = {"BLACK,rook"})
    void name(Team team, String expect) {
        // given
        Rook rook = new Rook(team);

        // then
        assertThat(rook.getName()).isEqualTo(expect);
    }

    @DisplayName("룩의 방향을 체크한다.")
    @ParameterizedTest
    @CsvSource(value = {"a1,a3,NORTH", "a3,a1,SOUTH"})
    void findDirection(Position from, Position to, Direction direction) {
        // given
        Rook rook = new Rook(Team.WHITE);

        // when
        Direction find = rook.findDirection(from, to);

        // then
        assertThat(find).isEqualTo(direction);
    }
}