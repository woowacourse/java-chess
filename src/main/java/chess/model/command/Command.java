package chess.model.command;

import chess.model.position.Position;

public abstract class Command implements State {
    protected static final String START = "start";
    protected static final String MOVE = "move";
    protected static final String END = "end";
    protected static final String STATUS = "status";

    protected final String input;

    public Command(String input) {
        validateCommand(input);
        this.input = input;
    }

    private void validateCommand(String input) {
        if (input.contains(MOVE)) {
            return;
        }
        if (START.equals(input)) {
            return;
        }
        if (END.equals(input)) {
            return;
        }
        if (STATUS.equals(input)) {
            return;
        }
        throw new IllegalArgumentException("명령어는 start, move, end, status 중 하나여야합니다.");
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
    public Position getSourcePosition() {
        throw new IllegalArgumentException("명령어에서 위치를 얻을수 없습니다.");
    }

    @Override
    public Position getTargetPosition() {
        throw new IllegalArgumentException("명령어에서 위치를 얻을수 없습니다.");
    }
}
