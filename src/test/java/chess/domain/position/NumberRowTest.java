package chess.domain.position;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NumberRowTest {
    @Test
    @DisplayName("문자로 생성")
    void create(){
        assertThatCode(() -> NumberRow.valueOf("1")).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("숫자로 생성")
    void create1(){
        assertThatCode(() -> NumberRow.valueOf(1)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("실패 - 범위를 벗어나는 숫자 입력")
    void create_fail1(){
        assertThatThrownBy(() -> NumberRow.valueOf("0")).isInstanceOf(IllegalArgumentException.class);
    }


}