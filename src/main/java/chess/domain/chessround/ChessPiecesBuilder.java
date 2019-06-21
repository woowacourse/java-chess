package chess.domain.chessround;

import chess.domain.chesspiece.*;
import chess.domain.chesspoint.ChessPoint;

import java.util.HashMap;
import java.util.Map;

public class ChessPiecesBuilder {
    private static ChessPiecesBuilder builder = null;

    private ChessPiecesBuilder() {
    }

    public static ChessPiecesBuilder getInstance() {
        if (builder == null) {
            builder = new ChessPiecesBuilder();
        }
        return builder;
    }

    public Map<ChessPoint, ChessPiece> initializeWhiteChessPieces() {
        Map<ChessPoint, ChessPiece> chessPieces = new HashMap<>();

        chessPieces.put(ChessPoint.of(1, 1), Rook.getInstance());
        chessPieces.put(ChessPoint.of(1, 2), Knight.getInstance());
        chessPieces.put(ChessPoint.of(1, 3), Bishop.getInstance());
        chessPieces.put(ChessPoint.of(1, 4), Queen.getInstance());
        chessPieces.put(ChessPoint.of(1, 5), King.getInstance());
        chessPieces.put(ChessPoint.of(1, 6), Bishop.getInstance());
        chessPieces.put(ChessPoint.of(1, 7), Knight.getInstance());
        chessPieces.put(ChessPoint.of(1, 8), Rook.getInstance());

        for (int column = ChessPoint.START; column <= ChessPoint.END; column++) {
            chessPieces.put(ChessPoint.of(2, column), WhitePawn.getInstance());
        }

        return chessPieces;
    }

    public Map<ChessPoint, ChessPiece> initializeBlackChessPieces() {
        Map<ChessPoint, ChessPiece> chessPieces = new HashMap<>();

        chessPieces.put(ChessPoint.of(8, 1), Rook.getInstance());
        chessPieces.put(ChessPoint.of(8, 2), Knight.getInstance());
        chessPieces.put(ChessPoint.of(8, 3), Bishop.getInstance());
        chessPieces.put(ChessPoint.of(8, 4), Queen.getInstance());
        chessPieces.put(ChessPoint.of(8, 5), King.getInstance());
        chessPieces.put(ChessPoint.of(8, 6), Bishop.getInstance());
        chessPieces.put(ChessPoint.of(8, 7), Knight.getInstance());
        chessPieces.put(ChessPoint.of(8, 8), Rook.getInstance());

        for (int column = ChessPoint.START; column <= ChessPoint.END; column++) {
            chessPieces.put(ChessPoint.of(7, column), BlackPawn.getInstance());
        }

        return chessPieces;
    }
}
