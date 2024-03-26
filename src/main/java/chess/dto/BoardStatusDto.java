package chess.dto;

import chess.domain.Position;
import chess.domain.State;
import chess.domain.piece.abstractPiece.Piece;
import java.util.Map;

public class BoardStatusDto {
    private final Map<Position, Piece> board;
    private final State state;

    public BoardStatusDto(Map<Position, Piece> board, State state) {
        this.board = board;
        this.state = state;
    }

    public Map<Position, Piece> board() {
        return board;
    }

    public State status() {
        return state;
    }
}
