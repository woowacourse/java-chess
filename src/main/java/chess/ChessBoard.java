package chess;

import chess.piece.Piece;
import chess.piece.PieceType;
import chess.position.Position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessBoard {

    Map<Position, Piece> positionByPieceData;

    private ChessBoard(Map<Position, Piece> boardData) {
        this.positionByPieceData = boardData;
    }

    public static ChessBoard createInitBoard() {
        Map<Position, Piece> positionByPieceData = new HashMap<>();
        for (PieceType pieceType : PieceType.values()) {
            List<Position> blackInitPositions = pieceType.getBlackInitPositions();
            for (Position blackInitPosition : blackInitPositions) {
                positionByPieceData.put(blackInitPosition, pieceType.getPiece());
            }
            List<Position> whiteInitPositions = pieceType.getWhiteInitPositions();
            for (Position whiteInitPosition : whiteInitPositions) {
                positionByPieceData.put(whiteInitPosition, pieceType.getPiece());
            }
        }
        return new ChessBoard(positionByPieceData);
    }

    public Map<Position, Piece> getPositionByPieceData() {
        return positionByPieceData;
    }
}
