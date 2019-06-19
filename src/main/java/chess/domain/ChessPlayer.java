package chess.domain;

import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspoint.ChessPoint;

import java.util.Map;

public class ChessPlayer {
    private Map<ChessPoint, ChessPiece> chessPieces;

    private ChessPlayer(Map<ChessPoint, ChessPiece> chessPieces) {
        this.chessPieces = chessPieces;
    }

    public static ChessPlayer from(Map<ChessPoint, ChessPiece> chessPieces) {
        return new ChessPlayer(chessPieces);
    }

    public boolean contains(ChessPoint source) {
        return chessPieces.containsKey(source);
    }

    public void move(ChessPoint source, ChessPoint target) {

    }

    public void delete(ChessPoint target) {
        ChessPiece removedChessPiece = chessPieces.remove(target);
        if (removedChessPiece == null) {
            throw new IllegalArgumentException("");
        }
    }
}
