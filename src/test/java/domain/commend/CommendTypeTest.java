package domain.commend;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.commend.exceptions.CommendTypeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CommendTypeTest {

    @ParameterizedTest
    @DisplayName("잘못된 input이 들어올 때")
    @ValueSource(strings = {"asd", "mova"})
    void input_Wrong(String input) {
        assertThatThrownBy(() -> CommendType.find(input))
            .isInstanceOf(CommendTypeException.class);
    }

    @Test
    @DisplayName("올바른 input이 들어올 때")
    void input_Right() {
        assertThat(CommendType.find("start")).isEqualTo(CommendType.START);
        assertThat(CommendType.find("move")).isEqualTo(CommendType.MOVE);
        assertThat(CommendType.find("end")).isEqualTo(CommendType.END);
        assertThat(CommendType.find("status")).isEqualTo(CommendType.STATUS);
    }
}
