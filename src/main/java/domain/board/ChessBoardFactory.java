package domain.board;

import domain.piece.Color;
import domain.piece.Piece;
import domain.piece.nonpawn.Bishop;
import domain.piece.nonpawn.King;
import domain.piece.nonpawn.Knight;
import domain.piece.nonpawn.Queen;
import domain.piece.nonpawn.Rook;
import domain.piece.pawn.BlackPawn;
import domain.piece.pawn.WhitePawn;
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
            pieceMap.put(new Position(File.fromOrder(order), Rank.SEVEN), new BlackPawn());
            pieceMap.put(new Position(File.fromOrder(order), Rank.TWO), new WhitePawn());
            pieceMap.put(new Position(File.fromOrder(order), Rank.ONE), specialPieces.get(order + 8));
        }
        return new ChessBoard(pieceMap);
    }
}
