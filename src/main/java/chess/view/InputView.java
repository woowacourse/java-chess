package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    
    private static final Scanner scanner = new Scanner(System.in);
    
    public List<String> readCommand() {
        String input = scanner.nextLine();
        return Arrays.stream(input.split(" ")).collect(Collectors.toList());
    }
    
    public int readGameNumber() {
        System.out.println("게임 번호를 입력해주세요.");
        String input = scanner.nextLine();
        System.out.println();
        return Integer.parseInt(input);
    }
    
    public String readStartOption() {
        System.out.println("게임을 새로 시작하려면 'new'를 입력해주세요.");
        System.out.println("이어서 게임을 진행하려면 'continue'를 입력해주세요.");
        String input = scanner.nextLine();
        System.out.println();
        return input;
    }
}
