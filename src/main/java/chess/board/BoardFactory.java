package chess.board;

import chess.Position;
import chess.piece.Bishop;
import chess.piece.Color;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;
import java.util.HashMap;
import java.util.Map;

public class BoardFactory {

    public Map<Position, Piece> createInitialBoard() {
        Map<Position, Piece> board = new HashMap<>();

        board.putAll(createPawn());
        board.putAll(createKing());
        board.putAll(createQueen());
        board.putAll(createBishop());
        board.putAll(createRook());
        board.putAll(createKnight());

        return board;
    }

    private Map<Position, Piece> createPawn() {
        Map<Position, Piece> pieceMap = new HashMap<>();

        for (int file = 1; file <= 8; file++) {
            final Position whitePosition = new Position(file, 2);
            final Position blackPosition = new Position(file, 7);
            pieceMap.put(whitePosition, new Pawn(Color.WHITE));
            pieceMap.put(blackPosition, new Pawn(Color.BLACK));
        }

        return pieceMap;
    }

    private Map<Position, Piece> createKing() {
        Map<Position, Piece> pieceMap = new HashMap<>();

        final Position whitePosition = new Position(5, 1);
        pieceMap.put(whitePosition, new King(Color.WHITE));

        final Position blackPosition = new Position(5, 8);
        pieceMap.put(blackPosition, new King(Color.BLACK));

        return pieceMap;
    }

    private Map<Position, Piece> createKnight() {
        Map<Position, Piece> pieceMap = new HashMap<>();

        final Position whiteLeftPosition = new Position(2, 1);
        final Position whiteRightPosition = new Position(7, 1);
        final Position blackLeftPosition = new Position(2, 8);
        final Position blackRightPosition = new Position(7, 8);

        pieceMap.put(whiteLeftPosition, new Knight(Color.WHITE));
        pieceMap.put(whiteRightPosition, new Knight(Color.WHITE));
        pieceMap.put(blackLeftPosition, new Knight(Color.BLACK));
        pieceMap.put(blackRightPosition, new Knight(Color.BLACK));

        return pieceMap;
    }

    private Map<Position, Piece> createBishop() {
        Map<Position, Piece> pieceMap = new HashMap<>();

        final Position whiteLeftPosition = new Position(3, 1);
        final Position whiteRightPosition = new Position(6, 1);
        final Position blackLeftPosition = new Position(3, 8);
        final Position blackRightPosition = new Position(6, 8);

        pieceMap.put(whiteLeftPosition, new Bishop(Color.WHITE));
        pieceMap.put(whiteRightPosition, new Bishop(Color.WHITE));
        pieceMap.put(blackLeftPosition, new Bishop(Color.BLACK));
        pieceMap.put(blackRightPosition, new Bishop(Color.BLACK));

        return pieceMap;
    }

    private Map<Position, Piece> createQueen() {
        Map<Position, Piece> pieceMap = new HashMap<>();

        final Position whitePosition = new Position(4, 1);
        final Position blackPosition = new Position(4, 8);

        pieceMap.put(whitePosition, new Queen(Color.WHITE));
        pieceMap.put(blackPosition, new Queen(Color.BLACK));

        return pieceMap;
    }

    private Map<Position, Piece> createRook() {
        Map<Position, Piece> pieceMap = new HashMap<>();

        final Position whiteLeftPosition = new Position(1, 1);
        final Position whiteRightPosition = new Position(8, 1);
        final Position blackLeftPosition = new Position(1, 8);
        final Position blackRightPosition = new Position(8, 8);

        pieceMap.put(whiteLeftPosition, new Rook(Color.WHITE));
        pieceMap.put(whiteRightPosition, new Rook(Color.WHITE));
        pieceMap.put(blackLeftPosition, new Rook(Color.BLACK));
        pieceMap.put(blackRightPosition, new Rook(Color.BLACK));

        return pieceMap;
    }
}
