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
	@ValueSource(strings = {"hello", "world", "1231"})
	void commandConstructorTest(String command) {
		assertThatThrownBy(() -> new Command(command.split(" ")))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("명령은 move, status, end");
	}

	@DisplayName("Command에 empty이 오면 에러를 내는 테스트")
	@ParameterizedTest
	@EmptySource
	void EmptyConstructorTest(String command) {
		assertThatThrownBy(() -> new Command(command.split(" ")))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("빈 값은 들어올 수 없습니다");
	}

	@DisplayName("source position을 정상적으로 갖고오는지 테스트")
	@Test
	void getMoveSourceTest() {
		Command command = new Command("move b1 b2".split(" "));
		assertThat(command.getMoveSource()).isEqualTo(Position.of("b1"));
	}

	@DisplayName("target position을 정상적으로 갖고오는지 테스트")
	@Test
	void getMoveTargetTest() {
		Command command = new Command("move b1 b2".split(" "));
		assertThat(command.getMoveTarget()).isEqualTo(Position.of("b2"));
	}

	@DisplayName("Command에 end가 있는지 테스트")
	@Test
	void isEndTest() {
		Command command = new Command("end".split(" "));
		assertThat(command.isEnd()).isTrue();
	}

	@DisplayName("Command에 status가 있는지 테스트")
	@Test
	void isStatusTest() {
		Command command = new Command("status".split(" "));
		assertThat(command.isStatus()).isTrue();
	}

	@DisplayName("Command에 move가 있는지 테스트")
	@Test
	void isMoveTest() {
		Command command = new Command("move b1 b2".split(" "));
		assertThat(command.isMove()).isTrue();
	}
}
