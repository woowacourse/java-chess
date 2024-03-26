package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessBoardCreator {
    private final Map<Piece, List<Position>> whitePiecesSetting;
    private final Map<Piece, List<Position>> blackPiecesSetting;

    private ChessBoardCreator(Map<Piece, List<Position>> whitePiecesSetting
            , Map<Piece, List<Position>> blackPiecesSetting) {
        this.whitePiecesSetting = new HashMap<>(whitePiecesSetting);
        this.blackPiecesSetting = new HashMap<>(blackPiecesSetting);
    }

    public static ChessBoardCreator normalGameCreator() {
        return new ChessBoardCreator(NormalPieceSetting.whitePiecesSetting(), NormalPieceSetting.blackPiecesSetting());
    }

    public ChessBoard create() {
        Map<Position, Piece> positionPiece = new HashMap<>();
        positionPiece.putAll(initializePiecesArrangeMent(whitePiecesSetting));
        positionPiece.putAll(initializePiecesArrangeMent(blackPiecesSetting));
        return new ChessBoard(positionPiece);
    }

    private Map<Position, Piece> initializePiecesArrangeMent(Map<Piece, List<Position>> arrangeMent) {
        Map<Position, Piece> positionPiece = new HashMap<>();
        arrangeMent.entrySet().stream()
                .map(piecePositions -> mapPositionToPiece(piecePositions.getKey(), piecePositions.getValue()))
                .forEach(positionPiece::putAll);
        return positionPiece;
    }

    private Map<Position, Piece> mapPositionToPiece(Piece pieceType, List<Position> positions) {
        Map<Position, Piece> positionPiece = new HashMap<>();
        positions.forEach(position -> positionPiece.put(position, pieceType));
        return positionPiece;
    }
}
