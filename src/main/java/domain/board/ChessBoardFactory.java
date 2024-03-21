package domain.board;

import domain.piece.Bishop;
import domain.piece.Color;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.Queen;
import domain.piece.Rook;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessBoardFactory {
    private static final List<Piece> specialPieces = List.of(
            new Rook(Color.BLACK),
            new Knight(Color.BLACK),
            new Bishop(Color.BLACK),
            new Queen(Color.BLACK),
            new King(Color.BLACK),
            new Bishop(Color.BLACK),
            new Knight(Color.BLACK),
            new Rook(Color.BLACK),
            new Rook(Color.WHITE),
            new Knight(Color.WHITE),
            new Bishop(Color.WHITE),
            new Queen(Color.WHITE),
            new King(Color.WHITE),
            new Bishop(Color.WHITE),
            new Knight(Color.WHITE),
            new Rook(Color.WHITE)
    );

    private ChessBoardFactory() {
    }

    public static ChessBoard createInitialChessBoard() {
        Map<Position, Piece> pieceMap = new HashMap<>();
        for (int order = 0; order < 8; order++) {
            pieceMap.put(new Position(File.fromOrder(order), Rank.EIGHT), specialPieces.get(order));
            pieceMap.put(new Position(File.fromOrder(order), Rank.SEVEN), new Pawn(Color.BLACK));
            pieceMap.put(new Position(File.fromOrder(order), Rank.TWO), new Pawn(Color.WHITE));
            pieceMap.put(new Position(File.fromOrder(order), Rank.ONE), specialPieces.get(order + 8));
        }
        return new ChessBoard(pieceMap);
    }
}
