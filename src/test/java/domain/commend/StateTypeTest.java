package domain.commend;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.commend.exceptions.CommendTypeException;
import org.junit.jupiter.api.Test;

public class StateTypeTest {

    @Test
    void answer_Start() {
        assertThat(CommendType.answer("start")).isEqualTo(CommendType.START);
    }

    @Test
    void answer_End() {
        assertThat(CommendType.answer("end")).isEqualTo(CommendType.END);
    }

    @Test
    void answer_Throw() {
        assertThatThrownBy(() -> CommendType.answer("asd"))
                .isInstanceOf(CommendTypeException.class);
    }
}
