package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


public class BishopTest {

    @DisplayName("이동 가능")
    @ParameterizedTest
    @CsvSource(value = {"a1,c3", "c3,a1", "a3,c1", "c1,a3"})
    void movable(String toValue, String fromValue) {
        //given
        Position to = Position.of(toValue);
        Position from = Position.of(fromValue);

        Bishop bishop = new Bishop(Team.BLACK);

        // then
        assertThatNoException().isThrownBy(() -> bishop.movable(to, from));
    }

    @DisplayName("이동 불가능")
    @ParameterizedTest
    @CsvSource(value = {"a1,b3", "b3,a1"})
    void movable_fasle(String toValue, String fromValue) {
        //given
        Position to = Position.of(toValue);
        Position from = Position.of(fromValue);

        Bishop bishop = new Bishop(Team.BLACK);

        // then
        assertThatThrownBy(() -> bishop.movable(to, from)).hasMessageContaining("Bishop");
    }

    @DisplayName("이름")
    @ParameterizedTest
    @CsvSource(value = {"BLACK,B", "WHITE,b"})
    void name(Team team, String expect) {
        // given
        Bishop bishop = new Bishop(team);

        // then
        assertThat(bishop.getName()).isEqualTo(expect);
    }

    @DisplayName("비숍의 방향을 체크한다.")
    @ParameterizedTest
    @CsvSource(value = {"c3,a1,SOUTH_WEST", "a1,c3,NORTH_EAST"})
    void findDirection(Position from, Position to, Direction direction) {
        // given
        Bishop bishop = new Bishop(Team.WHITE);

        // when
        Direction find = bishop.findDirection(from, to);

        // then
        assertThat(find).isEqualTo(direction);
    }
}
