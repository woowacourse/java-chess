package chess.dto;

import chess.domain.ChessBoardPosition;
import chess.domain.piece.ChessPiece;
import java.util.HashMap;
import java.util.Map;

public class ChessBoardDto {
    private Map<ChessBoardPosition, ChessPiece> mapInformation;

    public ChessBoardDto(Map<ChessBoardPosition, ChessPiece> mapInformation) {
        this.mapInformation = mapInformation;
    }

    public static ChessBoardDto of(Map<ChessBoardPosition, ChessPiece> board) {
        return new ChessBoardDto(board);
    }

    public Map<ChessBoardPosition, ChessPiece> getMapInformation() {
        return mapInformation;
    }
}
