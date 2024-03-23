package controller.command;

import domain.board.ChessBoard;
import domain.position.Position;
import view.OutputView;

public class MoveOnCommand implements Command {
    private final Position source;
    private final Position target;

    public MoveOnCommand(final Position source, final Position target) {
        this.source = source;
        this.target = target;
    }

    @Override
    public void execute(final ChessBoard board, final OutputView outputView) {
        try {
            board.move(source, target);
            outputView.printBoard(board);
        } catch (Exception e) {
            outputView.printErrorMessage(e.getMessage());
        }
    }

    @Override
    public boolean isNotEnded() {
        return true;
    }
}
