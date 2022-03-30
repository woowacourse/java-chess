package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.command.Command;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import java.util.Map;

import chess.domain.ChessScore;
import chess.domain.position.Position;

public abstract class State {

    protected Board board;

    public static State create() {
        return new Ready(BoardInitializer.generate());
    }

    public static State create(Map<Position, Piece> board) {
        return new Ready(board);
    }

    public abstract State proceed(Command command);

    public abstract boolean isFinished();

    public abstract ChessScore generateScore();

    public abstract boolean isRunning();

    public abstract Color getColor();

    public Board getBoard() {
        return board;
    }
}
