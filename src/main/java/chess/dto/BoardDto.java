package chess.dto;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import chess.domain.position.Position;

import java.util.LinkedHashMap;
import java.util.Map;

public class BoardDto {
//    private final Map<String, String> board;

//    public BoardDto(Pieces pieces) {
//        Map<String, String> result = new LinkedHashMap<>();
//
//        for(Piece piece : pieces.getPieces()) {
//            Position position = piece.getPosition();
//            String pieceName;
//            if (piece.isSameColor(Color.BLACK)) {
//                pieceName = "B" + piece.display().toUpperCase();
//            } else {
//                pieceName = "W" + piece.display().toUpperCase();
//            }
//            String positionName = position.column().value() + position.row().value();
//            result.put(positionName, pieceName);
//        }
//        this.board = result;
//    }
//    public Map<String, String> getBoard() {
//        return board;
//    }
}
