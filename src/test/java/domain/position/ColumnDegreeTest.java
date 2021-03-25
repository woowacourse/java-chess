package domain.position;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ColumnDegreeTest {

    @DisplayName("배열 기준 체스 열 좌표 생성")
    @ParameterizedTest
    @CsvSource(value = {"d5", "a8", "d5", " f7", "d5", "b3", "d5", "h1"}, delimiter = ',')
    void createTest(String value) {
        assertThat(new ColumnDegree(value)).isEqualTo(new ColumnDegree(value));
    }

}