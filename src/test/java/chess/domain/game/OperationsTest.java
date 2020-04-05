package chess.domain.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OperationsTest {

	@DisplayName("getFirstArgument 사용 가능할 떄 테스트")
	@Test
	void getFirstArgument_normal_test() {
		Operations operations = new Operations(Arrays.asList("move", "a2", "a3"));
		assertThat(operations.getFirstArgument()).isEqualTo("a2");
	}

	@DisplayName("getFirstArgument 사용 불가능할 떄 예외처리")
	@Test
	void getFirstArgument_invalid_exception_test() {
		Operations operations = new Operations(Arrays.asList("status"));
		assertThatThrownBy(operations::getFirstArgument)
				.isInstanceOf(UnsupportedOperationException.class)
				.hasMessage("해당 메서드를 수행할 수 없습니다.");
	}

	@DisplayName("getSecondArgument 사용 가능할 떄 테스트")
	@Test
	void getSecondArgument_normal_test() {
		Operations operations = new Operations(Arrays.asList("move", "a2", "a3"));
		assertThat(operations.getSecondArgument()).isEqualTo("a3");
	}

	@DisplayName("getSecondArgument 사용 불가능할 떄 예외처리")
	@Test
	void getSecondArgument_invalid_exception_test() {
		Operations operations = new Operations(Arrays.asList("status"));
		assertThatThrownBy(operations::getSecondArgument)
				.isInstanceOf(UnsupportedOperationException.class)
				.hasMessage("해당 메서드를 수행할 수 없습니다.");

	}
}