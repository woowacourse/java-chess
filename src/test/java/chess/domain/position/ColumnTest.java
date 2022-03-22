package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class ColumnTest {

    @Test
    @DisplayName("범위가 넘는 컬럼값은 존재하지 않는다")
    void column_mustInAToH() {
        List<String> names = Stream.of(Column.values())
                .map(Enum::name)
                .collect(Collectors.toList());

        assertThat(names).doesNotContain("Z");
    }
}
