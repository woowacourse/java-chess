package chess.service;

import chess.model.AbstractChessPiece;
import chess.model.ChessPieceColor;
import chess.model.ChessPieceType;
import chess.model.coordinate.Point;

import java.util.HashMap;
import java.util.Map;

public class BoardDTO {
    private final Map<String, String> board = new HashMap<>();

    public BoardDTO(Map<Point, AbstractChessPiece> board) {
        for (Map.Entry<Point, AbstractChessPiece> entry : board.entrySet()) {
            this.board.put(getKey(entry.getKey()), getValue(entry.getValue()));
        }
    }

    private String getKey(Point point) {
        String result = "";
        // 가로
        char x = (char) (point.getX() + 96);
        result += String.valueOf(x);
        // 세로
        result += String.valueOf(point.getY());
        return result;
    }

    private String getValue(AbstractChessPiece piece) {
        Map<ChessPieceType, String> nameMap = new HashMap<>();
        nameMap.put(ChessPieceType.PAWN, "P");
        nameMap.put(ChessPieceType.KNIGHT, "N");
        nameMap.put(ChessPieceType.BISHOP, "B");
        nameMap.put(ChessPieceType.KING, "K");
        nameMap.put(ChessPieceType.ROOK, "R");
        nameMap.put(ChessPieceType.QUEEN, "Q");

        Map<ChessPieceColor, String> colorMap = new HashMap<>();
        colorMap.put(ChessPieceColor.BLACK, "b");
        colorMap.put(ChessPieceColor.WHITE, "w");

        return colorMap.get(piece.getColor()) + nameMap.get(piece.getType());
    }

    public Map<String, String> getBoard() {
        return board;
    }
}
