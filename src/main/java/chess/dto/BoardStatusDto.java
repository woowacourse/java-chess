package chess.dto;

import chess.domain.Position;
import chess.domain.Status;
import chess.domain.piece.abstractPiece.Piece;
import java.util.Map;

public class BoardStatusDto {
    private final Map<Position, Piece> board;
    private final Status status;

    public BoardStatusDto(Map<Position, Piece> board, Status status) {
        this.board = board;
        this.status = status;
    }

    public Map<Position, Piece> board() {
        return board;
    }

    public Status status() {
        return status;
    }
}
