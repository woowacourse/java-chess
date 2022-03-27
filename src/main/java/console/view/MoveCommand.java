package console.view;

import chess.ChessBoard;
import chess.position.Position;

public class MoveCommand implements Command {

    private final Position from;
    private final Position to;

    public MoveCommand(Position from, Position to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public ChessBoard execute(ChessBoard chessBoard) {
        chessBoard.move(from, to);
        return chessBoard;
    }

    @Override
    public Position getFrom() {
        return from;
    }

    @Override
    public Position getTo() {
        return to;
    }
}
