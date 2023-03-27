package chess.view;

import java.util.Scanner;

public final class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static Command readCommand() {
        return Command.from(SCANNER.next());
    }

    public static String getCoordinate() {
        return SCANNER.next();
    }

    public static String readSaveRoomName() {
        System.out.println("저장할 방 이름을 입력해 주세요.");
        return SCANNER.next();
    }

    public static String readLoadRoomName() {
        System.out.println("불러올 방 이름을 입력해 주세요.");
        return SCANNER.next();
    }
}

