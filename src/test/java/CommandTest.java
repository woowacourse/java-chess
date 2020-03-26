import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import chess.domain.Command;

/**
 *    class description
 *
 *    @author AnHyungJu, LeeHoBin
 */
public class CommandTest {
	@ParameterizedTest
	@NullAndEmptySource
	void null_test(String command) {
		assertThatThrownBy(() -> new Command(command))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("null이나 빈 값");
	}

	@Test
	void test() {
		String command = "start";

		assertThatThrownBy(() -> new Command(command))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("처음 한 번");
	}
}
