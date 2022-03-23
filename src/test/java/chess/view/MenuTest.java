package chess.view;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


class MenuTest {

    @DisplayName("생성 확인")
    @ParameterizedTest
    @CsvSource(value = {"start:START", "Start:START", "START:START", "move:MOVE", "end:END"}, delimiter = ':')
    void create(String input, Menu expect) {
        assertThat(Menu.of(input)).isEqualTo(expect);
    }
}