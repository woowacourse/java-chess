package chess.domain.state;

import chess.domain.Board;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.dto.ChessInputDto;

import java.util.Map;

public abstract class State {
    protected final Board board;

    State(final Board board) {
        this.board = board;
    }

    public abstract State start();
    public abstract State move(final ChessInputDto dto);
    public abstract State end();
    public abstract boolean isNotEnd();

    public final Map<Position, Piece> getBoard() {
        return Map.copyOf(board.getBoard());
    }
}
