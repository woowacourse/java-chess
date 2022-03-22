package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class RowTest {

    @Test
    @DisplayName("행은 1~8의 범위를 가짐")
    void row_mustInOneToEight() {
        List<Integer> values = Stream.of(Row.values())
                .map(Row::getValue)
                .collect(Collectors.toList());

        assertThat(values).doesNotContain(9);
    }
}
