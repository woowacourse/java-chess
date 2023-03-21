package chess.controller.command;

public class EndCommandAction extends CommandAction {
    public EndCommandAction() {
        super(ignored -> {
        });
    }

    @Override
    public boolean isRunnable() {
        return false;
    }

    @Override
    public void execute(final Command command) {
    }
}
