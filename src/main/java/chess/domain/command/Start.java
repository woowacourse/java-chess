package chess.domain.command;

public class Start implements Command {
    @Override
    public Command execute(final String command) {
        if ("move".equals(command)) {
            return new Move();
        }
        if ("end".equals(command)) {
            return new End();
        }
        if ("status".equals(command)) {
            return new Status();
        }
        throw new UnsupportedOperationException("지원하지 않는 명령어입니다.");
    }

    @Override
    public boolean isStart() {
        return true;
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
}
