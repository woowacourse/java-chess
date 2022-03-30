package chess.domain.command;

import chess.domain.ChessGame;
import chess.domain.board.Position;

public class Move extends Command {
    private final Position sourcePosition;
    private final Position targetPosition;

    protected Move(Position sourcePosition, Position targetPosition) {
        super(Type.MOVE);
        this.sourcePosition = sourcePosition;
        this.targetPosition = targetPosition;
    }

    @Override
    public void execute(ChessGame chessGame) {
        chessGame.move(this.sourcePosition, this.targetPosition);
    }
}
