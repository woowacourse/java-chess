package techcourse.fp.chess.view;

import java.util.List;
import java.util.Scanner;
import techcourse.fp.chess.dto.request.CommandRequest;

public final class InputView {

    private final Scanner scanner;

    public InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public String readInitCommand() {
        return scanner.nextLine().trim();
    }

    public String readSaveGameName() {
        System.out.println("저장할 게임의 이름을 입력해주세요.");
        return scanner.nextLine();
    }

    //TODO: 숫자 아닐시 예외처리
    public int readChessGameId() {
        System.out.println("실행하길 원하는 게임의 id를 입력해주세요");
        return Integer.parseInt(scanner.nextLine());
    }

    public CommandRequest readInPlayCommand() {
        final String input = scanner.nextLine();

        final List<String> split = List.of(input.split(" "));
        return CommandRequest.create(split);
    }
}
