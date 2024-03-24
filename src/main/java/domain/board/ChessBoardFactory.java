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
import java.util.Map;
import java.util.function.Function;

public class ChessBoardFactory {
    private static final Map<File, Function<Color, Piece>> INITIAL_NON_PAWN_PIECES = Map.of(
            File.A, Rook::new,
            File.B, Knight::new,
            File.C, Bishop::new,
            File.D, Queen::new,
            File.E, King::new,
            File.F, Bishop::new,
            File.G, Knight::new,
            File.H, Rook::new);

    private ChessBoardFactory() {
    }

    public static ChessBoard createInitialChessBoard() {
        Map<Position, Piece> positionAndPieces = new HashMap<>();
        positionAndPieces.putAll(createNonPawnPieces());
        positionAndPieces.putAll(createPawnPieces());
        return new ChessBoard(positionAndPieces);
    }

    private static Map<Position, Piece> createNonPawnPieces() {
        Map<Position, Piece> nonPawnPieces = new HashMap<>();
        INITIAL_NON_PAWN_PIECES.forEach((file, pieceGenerator) -> {
            nonPawnPieces.put(new Position(file, Rank.EIGHT), pieceGenerator.apply(Color.BLACK));
            nonPawnPieces.put(new Position(file, Rank.ONE), pieceGenerator.apply(Color.WHITE));
        });
        return nonPawnPieces;
    }

    private static Map<Position, Piece> createPawnPieces() {
        Map<Position, Piece> pawnPieces = new HashMap<>();
        for (File file : File.values()) {
            pawnPieces.put(new Position(file, Rank.SEVEN), new BlackPawn());
            pawnPieces.put(new Position(file, Rank.TWO), new WhitePawn());
        }
        return pawnPieces;
    }
}
