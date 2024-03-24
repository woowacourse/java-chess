package chess.view.command;

public class StartCommand {

    private final boolean isStart;

    private StartCommand(boolean isStart) {
        this.isStart = isStart;
    }

    public static StartCommand from(String input) {
        if ("start".equals(input)) {
            return new StartCommand(true);
        }

        if ("end".equals(input)) {
            return new StartCommand(false);
        }

        throw new IllegalArgumentException("존재하지 않는 명령어입니다. start와 end 중 하나를 입력해주세요.");
    }

    public boolean isStart() {
        return isStart;
    }
}
