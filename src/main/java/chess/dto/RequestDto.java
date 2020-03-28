package chess.dto;

import java.util.List;

import chess.state.Command;

public class RequestDto {
	private final Command command;
	private final List<String> parameters;

	public RequestDto(Command command, List<String> parameters) {
		this.command = command;
		this.parameters = parameters;
	}

	public static RequestDto of(Command command) {
		return new RequestDto(command, null);
	}

	public static RequestDto of(Command command, List<String> parameters) {
		return new RequestDto(command, parameters);
	}

	public Command getCommand() {
		return command;
	}

	public List<String> getParameters() {
		return parameters;
	}
}
