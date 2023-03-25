package chess.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public class InputView {

    private static final int COMMAND_INDEX = 0;
    private static final int ROW_INDEX = 1;
    private static final int COLUMN_INDEX = 0;
    private static final int SOURCE_COORDINATE_INDEX = 1;
    private static final int DESTINATION_COORDINATE_INDEX = 2;

    private static final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    private InputView() {
        throw new IllegalStateException("인스턴스를 생성할 수 없는 객체입니다.");
    }

    public static String[] inputCommand() {
        try {
            String inputCommand = bufferedReader.readLine();
            validateInputCommand(inputCommand);
            return inputCommand.split(" ");
        } catch (IOException e) {
            return inputCommand();
        }
    }

    private static void validateInputCommand(String inputCommand) {
        validateBlank(inputCommand);
        validateStartOrEndCommandForm(inputCommand);
    }

    private static void validateBlank(String inputCommand) {
        if (Objects.isNull(inputCommand) || inputCommand.isBlank()) {
            throw new IllegalArgumentException("빈 값 또는 null 값을 입력할 수 없습니다.");
        }
    }

    private static void validateStartOrEndCommandForm(String inputCommand) {
        String[] splitedInputCommand = inputCommand.split(" ");
        if (isCorrectCommand(splitedInputCommand[0])) {
            throw new IllegalArgumentException("start, end, move 명령만 입력할 수 있습니다.");
        }

        if ("move".equals(splitedInputCommand[0]) && (splitedInputCommand.length != 3)) {
            throw new IllegalArgumentException("move 명령은 출발 좌표와 도착 좌표를 입력해야 합니다.");

        }
    }

    private static boolean isCorrectCommand(String command) {
        return !List.of("start", "end", "move").contains(command);
    }

    public static <T> T repeat(Supplier<T> inputProcess) {
        try {
            return inputProcess.get();
        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.println(illegalArgumentException.getMessage());
            return repeat(inputProcess);
        }
    }

    public static String extractCommand(String[] input) {
        return input[COMMAND_INDEX];
    }

    public static String[] extractSource(String[] coordinates) {
        return coordinates[SOURCE_COORDINATE_INDEX].split("");
    }

    public static String[] extractDestination(String[] coordinates) {
        return coordinates[DESTINATION_COORDINATE_INDEX].split("");
    }

    public static String extractColumn(String[] coordinate) {
        return coordinate[COLUMN_INDEX];
    }

    public static String extractRow(String[] coordinate) {
        return coordinate[ROW_INDEX];
    }
}
