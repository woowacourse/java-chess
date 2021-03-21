package domain;

import domain.piece.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class IndexMachineTest {
    @DisplayName("a3를 (5, 0)로 변환 가능하다.")
    @ParameterizedTest
    @CsvSource(value = {"a3:5:0", "c5:3:2"}, delimiter = ':')
    void index_machine_test(String input, int x, int y) {
        Position position = IndexMachine.convertPosition(input);
        assertThat(position.getRow()).isEqualTo(x);
        assertThat(position.getColumn()).isEqualTo(y);
    }
}