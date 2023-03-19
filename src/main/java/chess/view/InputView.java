package chess.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public class InputView {
    private static final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    
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
            throw new IllegalArgumentException("start, end, move 명령만 입력할 수 있습니다.");
        }
        
        if ("move".equals(splitedInputCommand[0]) && (splitedInputCommand.length != 3)) {
                throw new IllegalArgumentException("move 명령은 출발 좌표와 도착 좌표를 입력해야 합니다.");
            
        }
    }
    
    private static boolean isCorrectCommand(String command) {
        return List.of("start", "end", "move").contains(command);
    }
    
    public static String inputInitCommand() {
        try {
            String inputInitCommand = bufferedReader.readLine();
            validateInputInitCommand(inputInitCommand);
            return inputInitCommand;
        } catch (IOException e) {
            return inputInitCommand();
        }
    }
    
    private static void validateInputInitCommand(String inputInitCommand) {
        validateBlank(inputInitCommand);
        validateInitCommandForm(inputInitCommand);
    }
    
    private static void validateInitCommandForm(String inputInitCommand) {
        String[] splitedInputCommand = inputInitCommand.split(" ");
        if (!isCorrectInitCommand(splitedInputCommand[0])) {
            throw new IllegalArgumentException("게임 첫 시작에선 start, end 명령만 입력할 수 있습니다.");
        }
    }
    
    private static boolean isCorrectInitCommand(String command) {
        return List.of("start", "end").contains(command);
    }
    
    public static <T> T repeatAtExceptionCase(Supplier<T> inputProcess) {
        try {
            return inputProcess.get();
        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.println(illegalArgumentException.getMessage());
            return repeatAtExceptionCase(inputProcess);
        }
    }
}
