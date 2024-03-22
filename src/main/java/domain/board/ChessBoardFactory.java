package domain.board;

import domain.piece.Bishop;
import domain.piece.BlackPawn;
import domain.piece.Color;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Piece;
import domain.piece.Queen;
import domain.piece.Rook;
import domain.piece.WhitePawn;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class ChessBoardFactory {
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
            positionPiece.put(new Position(file, Rank.EIGHT), pieceGenerator.apply(Color.BLACK));
            positionPiece.put(new Position(file, Rank.ONE), pieceGenerator.apply(Color.WHITE));
        });

        for (File file : File.values()) {
            positionPiece.put(new Position(file, Rank.SEVEN), new BlackPawn());
            positionPiece.put(new Position(file, Rank.TWO), new WhitePawn());
        }
        return new ChessBoard(positionPiece);
    }
}
