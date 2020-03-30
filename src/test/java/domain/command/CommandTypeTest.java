package domain.command;

import domain.command.exceptions.CommandTypeException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class CommandTypeTest {

	@Test
	void getInstance_Start() {
		assertThat(CommandType.getInstance("start")).isEqualTo(CommandType.START);
	}

	@Test
	void getInstance_Move() {
		assertThat(CommandType.getInstance("move a1 a2")).isEqualTo(CommandType.MOVE);
	}

	@Test
	void getInstance_Status() {
		assertThat(CommandType.getInstance("status")).isEqualTo(CommandType.STATUS);
	}

	@Test
	void getInstance_End() {
		assertThat(CommandType.getInstance("end")).isEqualTo(CommandType.END);
	}

	@Test
	void getInstance_InvalidCommand_ThrowException() {
		assertThatThrownBy(() -> CommandType.getInstance("invalid"))
				.isInstanceOf(CommandTypeException.class);
	}
}