package chess.domain.game.command;

import chess.domain.game.ChessGame;
import chess.domain.position.Position;

public class MoveCommand implements ChessGameCommand {

    private final Position fromPosition;
    private final Position toPosition;

    public MoveCommand(Position fromPosition, Position toPosition) {
        this.fromPosition = fromPosition;
        this.toPosition = toPosition;
    }

    @Override
    public void execute(ChessGame chessGame) {
        chessGame.move(fromPosition, toPosition);
    }
}
