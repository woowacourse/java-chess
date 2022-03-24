package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
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
        Position to = new Position(toValue);
        Position from = new Position(fromValue);

        Bishop bishop = new Bishop();

        // then
        assertThat(bishop.movable(to, from)).isTrue();
    }

    @DisplayName("이동 불가능")
    @ParameterizedTest
    @CsvSource(value = {"a1,b3", "b3,a1"})
    void movable_fasle(String toValue, String fromValue) {
        Position to = new Position(toValue);
        Position from = new Position(fromValue);

        Bishop bishop = new Bishop();

        // then
        assertThat(bishop.movable(to, from)).isFalse();
    }
}
