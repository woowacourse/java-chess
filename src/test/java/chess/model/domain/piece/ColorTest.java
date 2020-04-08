package chess.model.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ColorTest {


    @DisplayName("다음 차례 확인")
    @Test
    void nextTurnIfEmptyMySelf() {
        Assertions.assertThat(Color.BLACK.nextTurnIfEmptyMySelf()).isEqualTo(Color.WHITE);
        assertThat(Color.WHITE.nextTurnIfEmptyMySelf()).isEqualTo(Color.BLACK);
    }

    @DisplayName("이전 차례 확인")
    @Test
    void previousTurnIfEmptyMySelf() {
        assertThat(Color.BLACK.previousTurnIfEmptyMySelf()).isEqualTo(Color.WHITE);
        assertThat(Color.WHITE.previousTurnIfEmptyMySelf()).isEqualTo(Color.BLACK);
    }
}
