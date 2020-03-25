package chess.domain.chessBoard;

import chess.domain.chessPiece.ChessPiece;
import chess.domain.position.Position;

import java.util.Map;

public class ChessBoard {
    private final Map<Position, ChessPiece> chessPieces;

    public ChessBoard(Map<Position, ChessPiece> chessPieces) {
        this.chessPieces = chessPieces;
    }
}
