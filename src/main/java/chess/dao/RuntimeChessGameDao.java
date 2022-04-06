package chess.dao;

import chess.model.Color;
import chess.model.board.Square;
import chess.model.piece.Piece;
import chess.model.status.Status;
import java.util.HashMap;
import java.util.Map;

public class RuntimeChessGameDao {
    private Map<Square, Piece> table = new HashMap<>();
    private Color turn = Color.WHITE;
    private Status status = Status.READY;

    public Map<Square, Piece> getAllPieces() {
        return table;
    }

    public void saveAll(Map<Square, Piece> board, Color turn, Status status) {
        this.table = board;
        this.turn = turn;
        this.status = status;
    }

    public Color getTurn() {
        return turn;
    }

    public Status getStatus() {
        return status;
    }

    public void updateStatus(Status status) {
        this.status = status;
    }
}
