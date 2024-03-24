package chess.model.board;

import chess.model.piece.Bishop;
import chess.model.piece.King;
import chess.model.piece.Knight;
import chess.model.piece.Pawn;
import chess.model.piece.Piece;
import chess.model.piece.Queen;
import chess.model.piece.Rook;
import chess.model.piece.Side;
import chess.model.position.ChessPosition;
import chess.model.position.File;
import chess.model.position.Rank;
import java.util.HashMap;
import java.util.Map;

public class ChessBoardInitializer {
    private ChessBoardInitializer() {
    }

    public static Map<ChessPosition, Piece> create() {
        Map<ChessPosition, Piece> board = new HashMap<>();
        board.putAll(createSpecialPieces(Side.BLACK));
        board.putAll(createPawns(Side.BLACK));
        board.putAll(createSpecialPieces(Side.WHITE));
        board.putAll(createPawns(Side.WHITE));
        return board;
    }

    private static Map<ChessPosition, Piece> createSpecialPieces(Side side) {
        Rank rank = convertSpecialPieceRankWithSide(side);
        return Map.of(
                new ChessPosition(File.A, rank), new Rook(side),
                new ChessPosition(File.B, rank), new Knight(side),
                new ChessPosition(File.C, rank), new Bishop(side),
                new ChessPosition(File.D, rank), new Queen(side),
                new ChessPosition(File.E, rank), new King(side),
                new ChessPosition(File.F, rank), new Bishop(side),
                new ChessPosition(File.G, rank), new Knight(side),
                new ChessPosition(File.H, rank), new Rook(side)
        );
    }

    private static Map<ChessPosition, Piece> createPawns(Side side) {
        Rank rank = convertPawnRanksWithSide(side);
        return Map.of(
                new ChessPosition(File.A, rank), new Pawn(side),
                new ChessPosition(File.B, rank), new Pawn(side),
                new ChessPosition(File.C, rank), new Pawn(side),
                new ChessPosition(File.D, rank), new Pawn(side),
                new ChessPosition(File.E, rank), new Pawn(side),
                new ChessPosition(File.F, rank), new Pawn(side),
                new ChessPosition(File.G, rank), new Pawn(side),
                new ChessPosition(File.H, rank), new Pawn(side)
        );
    }

    private static Rank convertSpecialPieceRankWithSide(Side side) {
        Rank rank = Rank.ONE;
        if (side == Side.BLACK) {
            rank = Rank.EIGHT;
        }
        return rank;
    }

    private static Rank convertPawnRanksWithSide(Side side) {
        Rank rank = Rank.TWO;
        if (side == Side.BLACK) {
            rank = Rank.SEVEN;
        }
        return rank;
    }
}
