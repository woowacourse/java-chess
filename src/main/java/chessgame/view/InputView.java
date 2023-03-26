package chessgame.view;

import java.util.Scanner;

public class InputView {
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
}
