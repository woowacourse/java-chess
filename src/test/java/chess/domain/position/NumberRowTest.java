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



}