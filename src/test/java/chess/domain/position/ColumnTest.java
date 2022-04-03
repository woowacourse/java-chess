package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class ColumnTest {

    @Test
    @DisplayName("범위가 넘는 컬럼값은 존재하지 않음")
    void column_mustInAToH() {
        List<String> names = Stream.of(Column.values())
                .map(Enum::name)
                .collect(Collectors.toList());

        assertThat(names).doesNotContain("Z");
    }

    @Test
    @DisplayName("정렬된 전체 컬럼을 가지고 옴")
    void allColumns_sortedByAscending() {
        List<String> orderedNames = Stream.of(Column.values())
                .map(Enum::name)
                .collect(Collectors.toList());

        assertThat(orderedNames).containsExactly("A", "B", "C", "D", "E", "F", "G", "H");
    }

    @Test
    @DisplayName("시작 컬럼과 목표 컬럼 사이에 있는 컬럼들을 반환한다")
    void calculate_columnPaths() {
        assertThat(Column.B.columnPaths(Column.F)).containsExactly(Column.C, Column.D, Column.E);
    }

    @Test
    @DisplayName("시작 컬럼과 목표 컬럼 사이에 있는 컬럼들을 순서에 맞게 반환한다")
    void calculate_columnPathsInRightOrder() {
        assertThat(Column.F.columnPaths(Column.B)).containsExactly(Column.E, Column.D, Column.C);
    }
}
