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
import java.util.Map;
import java.util.function.Function;

public class ChessBoardFactory {
    private static final Map<Color, Rank> NON_PAWN_RANKS = Map.of(
            Color.BLACK, Rank.EIGHT,
            Color.WHITE, Rank.ONE);
    private static final Map<Color, Rank> PAWN_RANKS = Map.of(
            Color.BLACK, Rank.SEVEN,
            Color.WHITE, Rank.TWO);
    private static final Map<File, Function<Color, Piece>> INITIAL_FILE_PIECES = Map.of(
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
        Map<Position, Piece> positionPiece = new HashMap<>();
        INITIAL_FILE_PIECES.forEach((file, pieceGenerator) -> {
            positionPiece.put(new Position(file, NON_PAWN_RANKS.get(Color.BLACK)), pieceGenerator.apply(Color.BLACK));
            positionPiece.put(new Position(file, NON_PAWN_RANKS.get(Color.WHITE)), pieceGenerator.apply(Color.WHITE));
        });

        for (File file : File.values()) {
            positionPiece.put(new Position(file, PAWN_RANKS.get(Color.BLACK)), new Pawn(Color.BLACK));
            positionPiece.put(new Position(file, PAWN_RANKS.get(Color.WHITE)), new Pawn(Color.WHITE));
        }
        return new ChessBoard(positionPiece);
    }
}
