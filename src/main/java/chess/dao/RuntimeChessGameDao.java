package chess.dao;

import chess.model.Color;
import chess.model.board.Square;
import chess.model.piece.Piece;
import chess.model.status.GameStatus;
import chess.model.status.Ready;
import java.util.HashMap;
import java.util.Map;

public class RuntimeChessGameDao {
    private Map<Square, Piece> table = new HashMap<>();
    private Color turn = Color.WHITE;
    private GameStatus status = new Ready();

    public Map<Square, Piece> getAllPieces() {
        return table;
    }

    public void saveAll(Map<Square, Piece> board, Color turn, GameStatus status) {
        this.table = board;
        this.turn = turn;
        this.status = status;
    }

    public Color getTurn() {
        return turn;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void updateStatus(GameStatus status) {
        this.status = status;
    }
}
