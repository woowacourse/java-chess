package chess.domain.command;

public class Status implements Command {
    @Override
    public Command execute(String command) {
        throw new UnsupportedOperationException("점수 출력을 한 뒤에는 또 출력할 수 없습니다.");
    }

    @Override
    public boolean isStart() {
        return false;
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public boolean isMove() {
        return false;
    }

    @Override
    public boolean isStatus() {
        return true;
    }
}
