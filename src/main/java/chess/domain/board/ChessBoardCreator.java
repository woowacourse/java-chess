package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.position.PiecesFirstPosition;
import chess.domain.position.Position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessBoardCreator {
    public ChessBoard create() {
        Map<Position, Piece> positionPiece = new HashMap<>();
        positionPiece.putAll(initializePiecesArrangeMent(PiecesFirstPosition.whitePiecesArrangeMent()));
        positionPiece.putAll(initializePiecesArrangeMent(PiecesFirstPosition.blackPiecesArrangeMent()));
        return new ChessBoard(positionPiece);
    }

    Map<Position, Piece> initializePiecesArrangeMent(Map<Piece, List<Position>> arrangeMent) {
        Map<Position, Piece> positionPiece = new HashMap<>();
        arrangeMent.entrySet().stream()
                .map(piecePositions -> mapPositionToPiece(piecePositions.getKey(), piecePositions.getValue()))
                .forEach(positionPiece::putAll);
        return positionPiece;
    }

    //TODO: 의미 있는 메서드명 생각해보기
    private Map<Position, Piece> mapPositionToPiece(Piece pieceType, List<Position> positions) {
        Map<Position, Piece> positionPiece = new HashMap<>();
        positions.forEach(position -> positionPiece.put(position, pieceType));
        return positionPiece;
    }
}
