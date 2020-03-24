package chess;

import chess.domain.chessPiece.*;

import java.util.HashMap;
import java.util.Map;

public class ChessBoard {
    private final Map<Position, Piece> pieces;

    public ChessBoard() {
        this.pieces = ChessBoardFactory.create();
    }
}
