package chess.model.command;

import java.util.List;

public abstract class Command implements State {

    protected final String input;

    public Command(String input) {
        validateCommand(input);
        this.input = input;
    }

    private void validateCommand(String input) {
        if (!input.contains("move") && !"start".equals(input) && !"end".equals(input) && !"status".equals(input)) {
            throw new IllegalArgumentException("명령어는 start, move, end, status 중 하나여야합니다.");
        }
    }

    @Override
    public Command turnFinalState(String input) {
        return new End(input);
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public boolean isMove() {
        return false;
    }

    @Override
    public boolean isStatus() {
        return false;
    }

    @Override
    public List<String> getCommandPosition() {
        throw new IllegalArgumentException("명령어에서 위치를 얻을수 없습니다.");
    }
}
