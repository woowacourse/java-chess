//package chess.command;
//
//import java.util.Arrays;
//import java.util.function.Consumer;
//
//public enum CommandType {
//	START("start", (str) -> str == "command"),
//	MOVE("move", true),
//	END("end", false);
//
//	String command;
//	Consumer<String> hands;
//
//	CommandType(String command, final Consumer<String> hands) {
//		this.command = command;
//		this.hands = hands;
//	}
//
//	public static CommandType of(String command) {
//		return Arrays.stream(values())
//			.filter(commandType -> commandType.command.equals(command))
//			.findAny()
//			.orElseThrow(() -> new IllegalArgumentException("잘못된 커맨드를 입력했습니다."));
//	}
//
//	public boolean isGaming() {
//		return this.state;
//	}
//}
