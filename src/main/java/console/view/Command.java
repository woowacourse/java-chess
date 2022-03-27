package console.view;

import chess.ChessBoard;
import chess.position.Position;

public interface Command {

    boolean isFinished();

    ChessBoard execute(ChessBoard chessBoard);

    default Position getFrom() {
        return null;
    }

    default Position getTo() {
        return null;
    }
}
