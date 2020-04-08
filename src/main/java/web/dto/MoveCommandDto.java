package web.dto;

public class MoveCommandDto {
	private String command;

	public MoveCommandDto(String moveCommand) {
		command = moveCommand;
	}

	public String getCommand() {
		return command;
	}
}
