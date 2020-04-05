package domain.command;

import java.util.Arrays;
import java.util.function.Predicate;

public enum Command {
	START(command -> command.equals("start")),
	END(command -> command.equals("end")),
	MOVE(command -> command.contains("move")),
	STATUS(command -> command.equals("status"));

	public static final int MOVE_ARGUMENT_COUNT = 3;

	public Predicate<String> isEquals;

	Command(Predicate<String> isEquals) {
		this.isEquals = isEquals;
	}

	public static Command of(String inputCommand) {
		return Arrays.stream(values())
			.filter(command -> command.isEquals.test(inputCommand))
			.findFirst()
			.orElseThrow(() -> new InvalidCommandException(InvalidCommandException.INVALID_COMMAND_TYPE));
	}

	public boolean isStart() {
		return START.equals(this);
	}

	public boolean isMove() {
		return MOVE.equals(this);
	}

	public boolean isStatus() {
		return STATUS.equals(this);
	}

	public boolean isEnd() {
		return END.equals(this);
	}

	public boolean isNotEnd() {
		return !isEnd();
	}
}
