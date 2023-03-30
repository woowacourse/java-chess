package chessgame.view;

import java.util.Scanner;

public class InputView {
    private static final String CONTINUE_COMMAND = "y";
    private static final String NO_CONTINUE_COMMAND = "n";

    private final Scanner scanner = new Scanner(System.in);

    public String readCommand() {
        return scanner.nextLine();
    }

    public String readGameName() {
        String name = scanner.nextLine();
        validateGameName(name);
        return name;
    }

    private void validateGameName(String name) {
        if(name.isBlank() || name.isEmpty()){
            throw new IllegalArgumentException("게임 이름으로 공백을 입력할 수 없습니다.");
        }
    }

    public String readContinueCommand() {
        String continueCommand = scanner.nextLine();
        validateContinueCommand(continueCommand);
        return continueCommand;
    }

    private boolean validateContinueCommand(String continueCommand) {
        if(continueCommand.equals(CONTINUE_COMMAND) || continueCommand.equals(NO_CONTINUE_COMMAND)){
            return true;
        }
        throw new IllegalArgumentException("y,n만 입력 가능합니다.");
    }
}
