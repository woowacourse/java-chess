package chess.domain.game.command;

import chess.domain.game.ChessGame;
import chess.domain.game.Position;

public class MoveCommand implements Command {

    private final Position origin;
    private final Position destination;

    private MoveCommand(Position origin, Position destination) {
        this.origin = origin;
        this.destination = destination;
    }

    public static MoveCommand of(String origin, String destination) {
        return new MoveCommand(Position.from(origin), Position.from(destination));
    }

    @Override
    public void execute(ChessGame chessGame) {
        chessGame.move(origin, destination);
    }
}
