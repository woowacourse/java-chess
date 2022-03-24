package chess.command;

public class Command implements State {

    protected final String input;

    public Command(String input) {
        validateCommand(input);
        this.input = input;
    }

    private void validateCommand(String input) {
        if(!input.contains("move") && !"start".equals(input) && !"end".equals(input)){
            throw new IllegalArgumentException("명령어는 start, move, end중 하나여야합니다.");
        }
    }

    @Override
    public Command turnState(String input) {
        if ("start".equals(input)) {
            return new Start(input);
        }
        if ("end".equals(input)) {
            return new End(input);
        }
        throw new IllegalArgumentException("처음엔 start or end만 입력해야합니다.");
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
