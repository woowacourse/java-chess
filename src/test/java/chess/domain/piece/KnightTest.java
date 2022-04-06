package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class KnightTest {

    @DisplayName("이동 가능")
    @ParameterizedTest
    @CsvSource(value = {"c3,a2", "c3,a4", "c3,b1", "c3,b5", "c3,d1", "c3,d5", "c3,e2", "c3,e4"})
    void movable(String toValue, String fromValue) {
        //given
        Position to = new Position(toValue);
        Position from = new Position(fromValue);

        Knight knight = new Knight(Team.BLACK);
        Bishop toPiece = new Bishop(Team.WHITE);

        // then
        assertThatNoException().isThrownBy(() -> knight.movable(to, from, toPiece));
    }

    @DisplayName("이동 불가능")
    @ParameterizedTest
    @CsvSource(value = {"c3,a3", "c3,a5", "c3,b2", "c3,b6", "c3,d2", "c3,d6", "c3,e3", "c3,e5"})
    void movable_false(String toValue, String fromValue) {
        //given
        Position to = new Position(toValue);
        Position from = new Position(fromValue);

        Knight knight = new Knight(Team.BLACK);
        Bishop toPiece = new Bishop(Team.WHITE);
        // then
        assertThatThrownBy(() -> knight.movable(to, from, toPiece)).hasMessageContaining("움직일 수 있는 방향이 아닙니다.");
    }

    @DisplayName("이름")
    @ParameterizedTest
    @CsvSource(value = {"BLACK,knight"})
    void name(Team team, String expect) {
        // given
        Knight knight = new Knight(team);

        // then
        assertThat(knight.getName()).isEqualTo(expect);
    }

    @DisplayName("나이트의 방향을 체크한다.")
    @ParameterizedTest
    @CsvSource(value = {"a1,B3,NNE", "b3,a1,SSW"})
    void findDirection(Position from, Position to, Direction direction) {
        // given
        Knight knight = new Knight(Team.WHITE);

        // when
        Direction find = knight.findDirection(from, to);

        // then
        assertThat(find).isEqualTo(direction);
    }
}
