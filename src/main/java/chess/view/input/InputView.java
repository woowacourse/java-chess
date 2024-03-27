package chess.view.input;

import chess.view.input.command.ClientCommand;
import chess.view.input.command.GameCommand;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputView {
    private static final Pattern COMMAND_PATTERN = Pattern.compile("^(\\w+)( [a-h][1-8] [a-h][1-8])?$");

    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public ClientCommand getClientCommand() {
        return parse(scanner.nextLine());
    }

    private ClientCommand parse(String input) {
        validateCommand(input);
        List<String> parts = List.of(input.trim().split(" "));
        return new ClientCommand(GameCommand.getGameCommand(parts.get(0)), parseArguments(parts));
    }

    private void validateCommand(String input) {
        Matcher matcher = COMMAND_PATTERN.matcher(input);
        if (!matcher.find()) {
            throw new IllegalArgumentException("올바르지 않는 명령어 입력입니다.");
        }
    }

    private List<String> parseArguments(List<String> parts) {
        return parts.subList(1, parts.size());
    }
}
