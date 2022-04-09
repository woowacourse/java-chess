package chess.dto;

import chess.domain.board.Position;
import chess.domain.piece.Piece;
import java.util.Map;
import java.util.TreeMap;

public class ChessBoardDto {

    private final Map<String, Piece> board;

    private ChessBoardDto(Map<String, Piece> board) {
        this.board = board;
    }

    public static ChessBoardDto from(Map<Position, Piece> board) {
        Map<String, Piece> convertedBoard = new TreeMap<>();
        for (Position position : board.keySet()) {
            convertedBoard.put(position.stringName(), board.get(position));
        }
        return new ChessBoardDto(convertedBoard);
    }

    public Map<String, Piece> getBoard() {
        return board;
    }
}
