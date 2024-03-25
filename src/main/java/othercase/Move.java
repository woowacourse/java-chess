package othercase;

import domain.board.Position;

public class Move implements GameCommand {
    private final Position source;
    private final Position destination;

    public Move(final Position source, final Position destination) {
        this.source = source;
        this.destination = destination;
    }

    @Override
    public void execute(final GameController gameController) {
        gameController.movePiece(source, destination);
    }
}
