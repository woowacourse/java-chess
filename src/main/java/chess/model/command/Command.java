package chess.model.command;

public abstract class Command implements State {
    public static final String START = "start";
    public static final String MOVE = "move";
    public static final String END = "end";
    public static final String STATUS = "status";

    protected final String input;

    public Command(String input) {
        validateCommand(input);
        this.input = input;
    }

    private void validateCommand(String input) {
        if (!input.contains(MOVE) && !START.equals(input) && !END.equals(input) && !STATUS.equals(input)) {
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
    public String getSourcePosition() {
        throw new IllegalArgumentException("명령어에서 위치를 얻을수 없습니다.");
    }

    @Override
    public String getTargetPosition() {
        throw new IllegalArgumentException("명령어에서 위치를 얻을수 없습니다.");
    }
}
