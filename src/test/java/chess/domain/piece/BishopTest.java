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
        Position to = new Position(toValue);
        Position from = new Position(fromValue);

        Bishop bishop = new Bishop(Team.BLACK);
        Bishop toPiece = new Bishop(Team.WHITE);

        // then
        assertThatNoException().isThrownBy(() -> bishop.movable(to, from, toPiece));
    }

    @DisplayName("이동 불가능")
    @ParameterizedTest
    @CsvSource(value = {"a1,b3", "b3,a1"})
    void movable_fasle(String toValue, String fromValue) {
        //given
        Position to = new Position(toValue);
        Position from = new Position(fromValue);

        Bishop bishop = new Bishop(Team.BLACK);
        Bishop toPiece = new Bishop(Team.WHITE);

        // then
        assertThatThrownBy(() -> bishop.movable(to, from, toPiece)).hasMessageContaining("움직일 수 있는 방향이 아닙니다.");
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
