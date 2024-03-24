package chess.view.command;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MoveCommand {

    private final boolean isEnd;
    private final List<String> data;

    public MoveCommand(boolean isEnd, List<String> data) {
        this.isEnd = isEnd;
        this.data = data;
    }

    public static MoveCommand from(String input) {
        if ("end".equals(input)) {
            return new MoveCommand(true, Collections.emptyList());
        }
        List<String> moveCommandInput = Arrays.asList(input.split(" "));
        validateMoveCommandInput(moveCommandInput);

        return new MoveCommand(false, List.of(moveCommandInput.get(1), moveCommandInput.get(2)));
    }

    private static void validateMoveCommandInput(List<String> moveCommandInput) {
        if (isInvalidFormat(moveCommandInput)) {
            throw new IllegalArgumentException("잘못된 입력입니다. move source target 형식으로 입력해주세요. ex) move a2 a4");
        }

        if (isNotMove(moveCommandInput)) {
            throw new IllegalArgumentException("존재하지 않는 명령어입니다. move 또는 end를 입력해주세요.");
        }
    }

    private static boolean isInvalidFormat(List<String> moveCommandInput) {
        return moveCommandInput.size() != 3;
    }

    private static boolean isNotMove(List<String> moveCommandInput) {
        return !"move".equals(moveCommandInput.get(0));
    }

    public boolean isEnd() {
        return isEnd;
    }

    public String source() {
        return data.get(0);
    }

    public String target() {
        return data.get(1);
    }
}
