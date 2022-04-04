package chess.console.command;

public final class Init extends Command {

    public Init(String input) {
        super(input);
    }

    @Override
    public Command turnState(String input) {
        if (input.contains("move")) {
            throw new IllegalArgumentException("[ERROR] 시작 명령어에는 move가 들어갈수 없습니다.");
        }
        if ("start".equals(input)) {
            return new Start(input);
        }
        if ("end".equals(input)) {
            return new End(input);
        }
        throw new IllegalArgumentException("[ERROR] 명령어는 start 또는 end만 올 수 있습니다.");
    }

}
