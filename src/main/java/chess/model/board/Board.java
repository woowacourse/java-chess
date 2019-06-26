package chess.model.board;

import chess.model.Square;
import chess.model.unit.Piece;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Board {
    private Map<Square, Piece> board = new HashMap<>();

    public void initialize(BoardInitializer initializer) {
        board = initializer.initialize();
    }

    private void setPiece(Square square, Piece piece) {
        board.put(square, piece);
    }

    public Piece getPiece(Square square) {
        return board.get(square);
    }

    public boolean isNullPiece(Square square) {
        return board.get(square) == null;
    }

    public boolean isSameSide(Square beginSquare, Square endSquare) {
        if (!isNullPiece(beginSquare) && !isNullPiece(endSquare))
            return board.get(beginSquare).isSameSide(board.get(endSquare));
        return false;
    }

    public List<Piece> getPieces(Predicate<Piece> pieceCondition) {
        return board.values().stream().filter(pieceCondition).collect(Collectors.toList());
    }

    public Map<String, String> createPieceMap() {
        Map<String, String> pieceMap = new HashMap<>();
        for (Map.Entry<Square, Piece> squarePieceEntry : board.entrySet()) {
            pieceMap.put(squarePieceEntry.getKey().toString(), squarePieceEntry.getValue().toString());
        }
        return pieceMap;
    }
}