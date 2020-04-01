package command;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class RunningCommand {
	private final static Map<String, Function<List<String>, RunningCommand>> commandMap;
	private static final String STATUS = "status";
	private static final String MOVE = "move";
	private static final String END = "end";
	private static final String DELIMITER = " ";

	static {
		commandMap = new HashMap<>();
		commandMap.put(STATUS, (splitCommands) -> of(STATUS));
		commandMap.put(MOVE, (splitCommands) -> of(MOVE, splitCommands.get(1), splitCommands.get(2)));
		commandMap.put(END, (splitCommands) -> of(END));
	}

	private final String command;
	private final String nowLocation;
	private final String destinationLocation;

	private RunningCommand(String command, String nowLocation, String destinationLocation) {
		this.command = command;
		this.nowLocation = nowLocation;
		this.destinationLocation = destinationLocation;
	}

	private static RunningCommand of(String move, String now, String destination) {
		return new RunningCommand(move, now, destination);
	}

	private static RunningCommand of(String status) {
		return of(status, "", "");
	}

	public static RunningCommand from(String inputCommand) {
		List<String> inputCommands = Arrays.asList(inputCommand.split(DELIMITER));

		return commandMap.keySet().stream()
			.filter(key -> key.equals(inputCommands.get(0)))
			.map(key -> commandMap.get(key).apply(inputCommands))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("명령어의 입력이 잘못되었습니다."));
	}

	public String getNowLocation() {
		if (!command.equals(MOVE)) {
			throw new UnsupportedOperationException("잘못된 호출입니다.");
		}
		return nowLocation;
	}

	public String getDestinationLocation() {
		if (!command.equals(MOVE)) {
			throw new UnsupportedOperationException("잘못된 호출입니다.");
		}
		return destinationLocation;
	}

	public boolean isNotEnd() {
		return !command.equals(END);
	}

	public boolean isMove() {
		return command.equals(MOVE);
	}

	public boolean isStatus() {
		return command.equals(STATUS);
	}
}
