package domain.piece.position;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ColumnTest {
	@DisplayName("두 개의 컬럼이 주어졌을 때 두 컬럼의 차이를 반환")
	@ParameterizedTest
	@CsvSource({"A,B,1", "A,D,3", "E,H,3", "B,A,-1", "H,A,-7"})
	void gap_GivenTwoColumn_ReturnGap(Column thisColumn, Column anotherColumn, int gap) {
		assertThat(thisColumn.gap(anotherColumn)).isEqualTo(gap);
	}
}
