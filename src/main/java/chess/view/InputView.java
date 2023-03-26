package chess.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputView {
    private static final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static final String MOVE_COMMAND_FORMAT = "move\\s[a-z][1-9]\\s[a-z][1-9]";
    private static final Pattern MOVE_COMMAND_PATTERN = Pattern.compile(MOVE_COMMAND_FORMAT);
    private static final String START_COMMAND = "start";
    private static final String END_COMMAND = "end";
    private static final String MOVE_COMMAND = "move";
    private static final String STATUS_COMMAND = "status";
    private static final String LOAD_COMMAND = "load";
    
    private InputView() {
        throw new IllegalStateException("인스턴스를 생성할 수 없는 객체입니다.");
    }
    
    public static String inputCommand() {
        try {
            String inputCommand = bufferedReader.readLine();
            validateInputCommand(inputCommand);
            return inputCommand;
        } catch (IOException e) {
            return inputCommand();
        }
    }
    
    private static void validateInputCommand(String inputCommand) {
        validateBlank(inputCommand);
        validateCommandForm(inputCommand);
    }
    
    private static void validateBlank(String inputCommand) {
        if (Objects.isNull(inputCommand) || inputCommand.isBlank()) {
            throw new IllegalArgumentException("빈 값 또는 null 값을 입력할 수 없습니다.");
        }
    }
    
    private static void validateCommandForm(String inputCommand) {
        String[] splitedInputCommand = inputCommand.split(" ");
        if (!isCorrectCommand(splitedInputCommand[0])) {
            throw new IllegalArgumentException("start, end, status, move, load 명령만 입력할 수 있습니다.");
        }
        
        if (MOVE_COMMAND.equals(splitedInputCommand[0])) {
            Matcher matcher = MOVE_COMMAND_PATTERN.matcher(inputCommand);
            if (!matcher.matches()) {
                throw new IllegalArgumentException("move 명령의 입력 형식이 잘못되었습니다. 다시 입력해주세요.");
            }
        }
    }
    
    private static boolean isCorrectCommand(String command) {
        return List.of(START_COMMAND, END_COMMAND, STATUS_COMMAND, MOVE_COMMAND, LOAD_COMMAND).contains(command);
    }
    
    public static <T> T repeatAtExceptionCase(Supplier<T> inputProcess) {
        try {
            return inputProcess.get();
        } catch (IllegalArgumentException illegalArgumentException) {
            OutputView.printErrorMessage(illegalArgumentException.getMessage());
            return repeatAtExceptionCase(inputProcess);
        }
    }
}
