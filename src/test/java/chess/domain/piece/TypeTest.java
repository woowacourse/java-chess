package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TypeTest {
    @DisplayName("타입의 개수를 테스트")
    @Test
    public void size () {
        //given
        Type[] types = Type.values();

        //when
        int size = types.length;
        //then
        assertThat(size).isEqualTo(6);

    }
}
