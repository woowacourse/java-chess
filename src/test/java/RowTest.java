import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static java.util.stream.Collectors.toMap;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RowTest {

    @Test
    @DisplayName("가로는 a~h의 알파벳으로 생성된다.")
    void makeRowTest() {
        assertThatCode(() -> {
            Row.valueOf("a");
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("a~h가 아닌 알파벳으로 만들어진 가로는 존재하지 않는다.")
    void testValueOf() {
        assertThatThrownBy(() -> Row.valueOf("z")).isInstanceOf(IllegalArgumentException.class);
    }

}
