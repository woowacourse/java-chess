package chess;

import chess.piece.ChessPiece;

import java.util.List;
import java.util.Map;

public class ChessPositions {
    private final Map<Position, ChessPiece> positions;

    public ChessPositions(Map<Position, ChessPiece> positions) {
        this.positions = positions;
    }
}
