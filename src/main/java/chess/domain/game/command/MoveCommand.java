package chess.domain.game.command;


import chess.domain.game.state.ChessGame;
import chess.domain.position.Position;

public class MoveCommand implements ChessGameCommand {

    private final Position fromPosition;
    private final Position toPosition;

    public MoveCommand(Position fromPosition, Position toPosition) {
        this.fromPosition = fromPosition;
        this.toPosition = toPosition;
    }

    @Override
    public ChessGame execute(ChessGame chessGame) {
        return chessGame.move(fromPosition, toPosition);
    }
}
