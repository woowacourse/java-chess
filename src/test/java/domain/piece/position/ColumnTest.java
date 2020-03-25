package domain.piece.position;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class ColumnTest {
	@DisplayName("문자에 해당하는 숫자 반환")
	@ParameterizedTest
	@CsvSource(value = {"a:1", "b:2", "c:3", "h:8"}, delimiter = ':')
	void getNumber_GivenChar_ReturnNumber(char representation, int number) {
		assertThat(Column.of(representation).getNumber()).isEqualTo(number);
	}

	@DisplayName("잘못된 문자가 들어왔을 경우 InvalidPositionException 발생")
	@ParameterizedTest
	@ValueSource(chars = {'1', '%', 'p', 'A'})
	void getNumber_GivenChar_ReturnNumber(char representation) {
		assertThatThrownBy(() -> Column.of(representation))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.INVALID_COLUMN);
	}
}
