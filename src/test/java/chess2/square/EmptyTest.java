package chess2.square;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess2.domain.square.Empty;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EmptyTest {

    @DisplayName("모든 Empty 인스턴스는 서로 동일하다고 간주된다.")
    @Test
    void equals_sameInstance() {
        Empty actual = new Empty();
        Empty expected = new Empty();

        assertThat(actual).isEqualTo(expected);
    }
}