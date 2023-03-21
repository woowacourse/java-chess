package chess.domain.game;

import chess.domain.position.Position;

public class ChessGameCommand {
    private final ChessCommandType gameCommand;
    private final Position fromPosition;
    private final Position toPosition;

    public ChessGameCommand(ChessCommandType gameCommand, Position fromPosition, Position toPosition) {
        this.gameCommand = gameCommand;
        this.fromPosition = fromPosition;
        this.toPosition = toPosition;
    }

    public ChessGameCommand(ChessCommandType gameCommand) {
        this.gameCommand = gameCommand;
        this.fromPosition = null;
        this.toPosition = null;
    }

    public boolean isStart() {
        return gameCommand == ChessCommandType.START;
    }

    public boolean isMove() {
        return gameCommand == ChessCommandType.MOVE;
    }

    public Position getFromPosition() {
        return fromPosition;
    }

    public Position getToPosition() {
        return toPosition;
    }
}
