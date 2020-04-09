package chess.DTO;

import chess.Board;
import chess.piece.Piece;
import chess.position.Position;

import java.util.HashMap;
import java.util.Map;

public class BoardDTO {
    private Map<String, String> board;

    public BoardDTO(Board board) {
        Map<Position, Piece> originalPieces = board.getPieces();
        Map<String, String> result = new HashMap<>();

        for (Position position : originalPieces.keySet()) {
            String pieceKey = originalPieces.get(position).getKey();
            result.put(position.getKey(), pieceKey);
        }
        this.board = result;
    }

    public void setBoard(Map<String, String> board) {
        this.board = board;
    }

    public Map<String, String> getBoard() {
        return this.board;
    }
}
