package domain;

import domain.piece.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class IndexMachineTest {
    @DisplayName("a3를 (5, 0)로 변환 가능하다.")
    @ParameterizedTest
    @CsvSource(value = {"a3:5:0", "c5:3:2"}, delimiter = ':')
    void index_machine_test(String input, int x, int y) {
        Position position = IndexMachine.convertPosition(input);
        assertThat(position.getRow()).isEqualTo(x);
        assertThat(position.getColumn()).isEqualTo(y);
    }

    @DisplayName("잘못된 위치가 들어오는 경우 예외처리한다.")
    @Test
    void invalid_input() {
        assertThatThrownBy(() -> IndexMachine.convertPosition("x2"))
                .isInstanceOf(NoSuchElementException.class);
    }
}