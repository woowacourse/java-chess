package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);
    private static final String MOVE_REGEX = "move\\s([a-zA-Z])(\\d)\\s([a-zA-Z])(\\d)";
    private static final Pattern MOVE_PATTERN = Pattern.compile(MOVE_REGEX);


    public GameCommand readGameCommand() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
        String command = scanner.nextLine();
        validate(command);
        return GameCommand.from(command);
    }

    public MoveArguments readMoveArguments() {
        String input = scanner.nextLine();
        validateMoveCommand(input);
        List<String> arguments = Arrays.stream(input.split(" "))
                .skip(1)
                .flatMap(s -> Arrays.stream(s.split("")))
                .toList();
        return new MoveArguments(arguments.get(0), parseInt(arguments.get(1)),
                arguments.get(2), parseInt(arguments.get(3)));
    }

    private void validateMoveCommand(final String input) {
        Matcher matcher = MOVE_PATTERN.matcher(input);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("입력 형식은 'move source위치 target위치' 입니다.");
        }
    }

    private void validate(String command) {
        if (Objects.isNull(command) || command.isBlank()) {
            throw new IllegalArgumentException("입력값은 공백일 수 없습니다.");
        }
    }

    private int parseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Rank가 숫자 형식이 아닙니다.");
        }
    }
}
