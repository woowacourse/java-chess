package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);


    public List<String> inputMovePosition() {
        System.out.println("원하는 위치를 입력해주세요.");
        String input = scanner.nextLine();
        return Arrays.stream(input.split(" ")).toList();
    }

    public List<String> inputMovePiece() {
        System.out.println("원하는 말의 위치를 입력해주세요.");
        String input = scanner.nextLine();

        return Arrays.stream(input.split(" ")).toList();
    }
}
