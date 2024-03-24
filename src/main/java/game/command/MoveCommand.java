package game.command;

import domain.square.Square;
import game.ChessGame;

public class MoveCommand implements ChessCommand {

    private final String sourceInput;
    private final String targetInput;

    public MoveCommand(final String sourceInput, final String targetInput) {
        this.sourceInput = sourceInput;
        this.targetInput = targetInput;
    }

    @Override
    public void execute(final ChessGame chessGame) {
        final Square source = Square.from(sourceInput);
        final Square target = Square.from(targetInput);

        chessGame.move(source, target);
    }
}
