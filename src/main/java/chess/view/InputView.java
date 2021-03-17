package chess.view;

import chess.domain.exceptions.TerminateException;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String GAME_INIT = "체스 게임을 시작합니다.";
    private static final String GAME_INIT_COMMAND = "게임 시작은 start, 종료는 end 명령을 입력하세요.";
    private static final String INIT_END = "END";
    private static final List<String> INIT_COMMANDS = Arrays.asList("start", "end", "move");
    private static final List<String> COMMANDS = Arrays.asList("start", "end", "move");

    public static boolean receiveInitialPlayerResponse() {
        printInitMessage();

        try {
            String answer = scanner.nextLine();
            checkInitResponse(answer);
            return true;
        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.println(illegalArgumentException.getMessage());
            return receiveInitialPlayerResponse();
        }
    }

    private static void checkInitResponse(String answer) {
        checkValidAnswer(answer);
        checkTerminateGame(answer);
    }

    private static void printInitMessage() {
        System.out.println(GAME_INIT);
        System.out.println(GAME_INIT_COMMAND);
    }

    private static void checkValidAnswer(String answer) {
        if (!INIT_COMMANDS.contains(answer)) {
            throw new IllegalArgumentException("유효하지 않은 입력입니다.");
        }
    }

    private static void checkTerminateGame(String answer) {
        if (INIT_COMMANDS.get(1).equals(answer)) {
            throw new TerminateException("");
        }
    }
}
