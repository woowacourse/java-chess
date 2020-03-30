package chess.domain;

import java.util.Arrays;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import chess.domain.board.Position;

/**
 *    명령어를 나타내는 클래스입니다.
 *
 *    @author AnHyungJu, LeeHoBin
 */
public class Command {
	private static final String MOVE = "move";
	private static final String STATUS = "status";
	private static final String END = "end";
	private static final int MINIMUM = 1;

	private String command;

	private Position source;

	private Position target;

	private Command(String command, Position source, Position target) {
		this.command = command;
		this.source = source;
		this.target = target;
	}

	public static Command of(String[] input) {
		validate(input);
		if (input[0].equals(MOVE)) {
			return new Command(input[0], Position.of(input[1]), Position.of(input[2]));
		}
		return new Command(input[0], null, null);
	}

	private static void validate(String[] input) {
		validateNullAndEmpty(input);
		validateInvalid(input);
	}

	private static void validateNullAndEmpty(String[] input) {
		if (Objects.isNull(input) || input.length < MINIMUM || Arrays.stream(input).anyMatch(StringUtils::isEmpty)) {
			throw new IllegalArgumentException("null이나 빈 값은 들어올 수 없습니다.");
		}
	}

	private static void validateInvalid(String[] input) {
		String command = input[0];
		if (!MOVE.equalsIgnoreCase(command) && !STATUS.equalsIgnoreCase(command) && !END.equalsIgnoreCase(command)) {
			throw new IllegalArgumentException("유효하지 않은 명령어 입니다.");
		}
	}

	public boolean isEnd() {
		return this.command.equalsIgnoreCase(END);
	}

	public boolean isMove() {
		return this.command.equalsIgnoreCase(MOVE);
	}

	public boolean isStatus() {
		return this.command.equalsIgnoreCase(STATUS);
	}

	public Position getSource() {
		return source;
	}

	public Position getTarget() {
		return target;
	}
}
