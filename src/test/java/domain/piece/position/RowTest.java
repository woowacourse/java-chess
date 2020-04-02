package domain.piece.position;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RowTest {
	@DisplayName("두 개의 로우가 주어졌을 때 두 로우의 차이를 반환")
	@ParameterizedTest
	@CsvSource({"ONE,TWO,1", "ONE,FOUR,3", "FIVE,EIGHT,3", "TWO,ONE,-1", "EIGHT,ONE ,-7"})
	void gap_GivenTwoColumn_ReturnGap(Row thisRow, Row anotherRow, int gap) {
		assertThat(thisRow.gap(anotherRow)).isEqualTo(gap);
	}
}
