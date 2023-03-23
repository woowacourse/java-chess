package chess.ui;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.util.stream.Collectors.toList;

public final class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    private static boolean getStartCommandResult() {
        String inputCommand;
        try {
            inputCommand = SCANNER.nextLine();
            return isNotInputCommandStart(inputCommand);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getStartCommandResult();
        }
    }

    private static boolean isNotInputCommandStart(final String inputCommand) {
        validateInputCommand(inputCommand);
        return inputCommand.equals("start");
    }

    private static void validateInputCommand(final String inputCommand) {
        if (!inputCommand.equals("start") && isNotInputCommandEnd(inputCommand)) {
            throw new IllegalArgumentException("start 또는 end만 입력할 수 있습니다.");
        }
    }

    private static boolean isNotInputCommandEnd(final String inputCommand) {
        return !inputCommand.equals("end");
    }

    public static List<String> getCommands() {
        List<String> commands = Arrays.stream(SCANNER.nextLine().split(" "))
                .collect(toList());
        validateCommandSize(commands);
//        validateCommandCategory(commands);
        return commands;
    }

    private static void validateCommandSize(List<String> commands) {
        if (commands.size() == 0) {
            throw new IllegalArgumentException("커맨드를 입력해야 합니다.");
        }
        if (commands.get(0).equals("end") && commands.size() > 1) {
            throw new IllegalArgumentException("end 뒤에는 명령어를 붙일 수 없습니다.");
        }
        if (commands.size() > 3) {
            throw new IllegalArgumentException("정의되지 않은 명령어를 입력할 수 없습니다.");
        }
    }

    private static void validateCommandCategory(List<String> commands) {
        if (!commands.get(0).equals("move") && !commands.get(0).equals("end")) {
            throw new IllegalArgumentException("move 또는 end가 아닌 커맨드는 입력할 수 없습니다.");
        }
    }

}
