package chess.controller.command;

public class WaitingCommandAction extends CommandAction{
    public WaitingCommandAction() {
        super(EMPTY_ACTION);
    }

    @Override
    public boolean isRunnable() {
        return true;
    }

    @Override
    public void execute(final Command command) {
        throw new UnsupportedOperationException("명령을 기다리는 상태에서는 게임을 실행할 수 없습니다.");
    }
}
