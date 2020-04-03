package chess.utils;

import chess.domain.square.Square;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class InputParserTest {
    @ParameterizedTest
    @DisplayName("올바른 입력 파싱하는 메소드 테스트")
    @CsvSource({"start,start", "end,end", "move a1 a2,move", "status,status"})
    void rightParseInput(String input, String expected) {
        Assertions.assertThat(InputParser.parseState(input).getName()).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("명령어 자체가 틀렸을 때")
    @ValueSource(strings = {"starrt", "end!", "move! k1 k3"})
    void wrongParseInput(String input) {
        assertThatThrownBy(() -> InputParser.parseState(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("잘못된 명령어");
    }

    @Test
    @DisplayName("명령어는 옳은데 인자 갯수는 일치하지 않는 경우 테스트")
    void wrongMatchingCommandParseInput() {
        assertThatThrownBy(() -> InputParser.parseState("start a1 a2"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("3개이면 move");
    }

    @ParameterizedTest
    @DisplayName("명령어는 옳은데 1개나 3개가 아닌 단어들이 입력됐을 때")
    @ValueSource(strings = {"end f5 d6 k8", "start l1", "move a1"})
    void wrongNoOfParameterCommandParseInput(String input) {
        assertThatThrownBy(() -> InputParser.parseState(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1개 혹은 3개");
    }

    @Test
    @DisplayName("move에 관해 옳은 명령이 입력됐을때")
    void rightMoveParseInput() {
        List<Square> movableSquares = InputParser.parseMoveSourceDestination("move d1 f4");
        assertThat(movableSquares.get(0)).isEqualTo(Square.of("d1"));
        assertThat(movableSquares.get(1)).isEqualTo(Square.of("f4"));
    }

    @Test
    @DisplayName("move에 범위 바깥의 square 값이 입력됐을 때")
    void wrongMoveParseInput() {
        assertThatThrownBy(() -> InputParser.parseMoveSourceDestination("move d9 f4"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효한");

    }
}
