package game.command;

import domain.square.Square;
import game.ChessGame;

import java.util.function.Consumer;

public class MoveCommand implements ChessCommand {

    private final String sourceInput;
    private final String targetInput;

    public MoveCommand(final String sourceInput, final String targetInput) {
        this.sourceInput = sourceInput;
        this.targetInput = targetInput;
    }

    @Override
    public void execute(final ChessGame chessGame, final Consumer<ChessGame> callBack) {
        final Square source = Square.from(sourceInput);
        final Square target = Square.from(targetInput);

        chessGame.move(source, target);

        callBack.accept(chessGame);
    }
}
