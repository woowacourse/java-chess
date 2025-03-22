package chess.model;

import chess.model.element.Color;
import chess.model.piece.Piece;
import chess.model.piece.PieceType;
import chess.model.position.Position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BoardFactory {

    public ChessBoard generateBoard() {
        Map<Position, Piece> positionByPieceData = new HashMap<>();
        for (PieceType pieceType : PieceType.values()) {
            for (Color color : Color.values()) {
                List<Position> initPositionsByColor = pieceType.getInitPositionsByColor(color);
                for (Position position : initPositionsByColor) {
                    positionByPieceData.put(position, pieceType.getPieceByColor(color));
                }
            }
        }
        return new ChessBoard(positionByPieceData);
    }
}
