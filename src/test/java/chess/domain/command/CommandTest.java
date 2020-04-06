package chess.domain.command;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import chess.domain.board.Position;

/**
 *    class description
 *
 *    @author AnHyungJu, LeeHoBin
 */
public class CommandTest {
	@DisplayName("Command가 end, status, move 만 오도록 에러메세지를 내는지 테스트")
	@ParameterizedTest
	@ValueSource(strings = {"hello", "hihi", "1231"})
	void commandConstructorTest(String command) {
		assertThatThrownBy(() -> new Command(command))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("명령은 move, status, end");
	}

	@DisplayName("Command의 길이가 1과 3이 아니라면 에러메세지를 내는지 테스트")
	@ParameterizedTest
	@ValueSource(strings = {"hello world", "move b1 b2 b3"})
	void validateLengthTest(String command) {
		assertThatThrownBy(() -> new Command(command))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("Command에 empty이 오면 에러를 내는 테스트")
	@ParameterizedTest
	@EmptySource
	void EmptyConstructorTest(String command) {
		assertThatThrownBy(() -> new Command(command))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("빈 값은 들어올 수 없습니다");
	}

	@DisplayName("source position을 정상적으로 갖고오는지 테스트")
	@Test
	void getMoveSourceTest() {
		Command command = new Command("move b1 b2");
		assertThat(command.getSourceCommand()).isEqualTo(Position.of("b1"));
	}

	@DisplayName("target position을 정상적으로 갖고오는지 테스트")
	@Test
	void getMoveTargetTest() {
		Command command = new Command("move b1 b2");
		assertThat(command.getTargetCommand()).isEqualTo(Position.of("b2"));
	}

	@DisplayName("Command에 end가 있는지 테스트")
	@Test
	void isEndTest() {
		Command command = new Command("end");
		assertThat(command.isEnd()).isTrue();
	}

	@DisplayName("Command에 status가 있는지 테스트")
	@Test
	void isStatusTest() {
		Command command = new Command("status");
		assertThat(command.isStatus()).isTrue();
	}

	@DisplayName("Command에 move가 있는지 테스트")
	@Test
	void isMoveTest() {
		Command command = new Command("move b1 b2");
		assertThat(command.isMove()).isTrue();
	}
}
