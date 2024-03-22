package domain.command;

import domain.ChessBoard;
import domain.Square;

public class MoveCommand implements ChessCommand {

    private final String sourceInput;
    private final String targetInput;

    public MoveCommand(final String sourceInput, final String targetInput) {
        this.sourceInput = sourceInput;
        this.targetInput = targetInput;
    }

    @Override
    public boolean run(final ChessBoard chessBoard) {
        final Square source = Square.from(sourceInput);
        final Square target = Square.from(targetInput);

        chessBoard.move(source, target);

        return true;
    }
}
