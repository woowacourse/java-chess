package chess.util;

import chess.domain.ChessBoard;
import chess.domain.piece.Color;
import chess.domain.position.File;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.piece.type.Bishop;
import chess.domain.piece.type.King;
import chess.domain.piece.type.Knight;
import chess.domain.piece.type.Pawn;
import chess.domain.piece.type.Queen;
import chess.domain.piece.type.Rook;
import java.util.HashMap;
import java.util.Map;

public class ChessBoardInitializer {

    private ChessBoardInitializer() {
    }

    public static ChessBoard init() {
        final Map<Position, Piece> pieces = new HashMap<>();

        createPieceWithoutPawn(pieces, Color.BLACK, Rank.EIGHT);
        createPawn(pieces, Color.BLACK, Rank.SEVEN);
        createPawn(pieces, Color.WHITE, Rank.TWO);
        createPieceWithoutPawn(pieces, Color.WHITE, Rank.ONE);

        return new ChessBoard(pieces);
    }

    private static void createPawn(final Map<Position, Piece> pieces, final Color color, final Rank rank) {
        pieces.put(new Position(File.A, rank), new Pawn(color));
        pieces.put(new Position(File.B, rank), new Pawn(color));
        pieces.put(new Position(File.C, rank), new Pawn(color));
        pieces.put(new Position(File.D, rank), new Pawn(color));
        pieces.put(new Position(File.E, rank), new Pawn(color));
        pieces.put(new Position(File.F, rank), new Pawn(color));
        pieces.put(new Position(File.G, rank), new Pawn(color));
        pieces.put(new Position(File.H, rank), new Pawn(color));
    }

    private static void createPieceWithoutPawn(final Map<Position, Piece> pieces, final Color color, final Rank rank) {
        pieces.put(new Position(File.A, rank), new Rook(color));
        pieces.put(new Position(File.B, rank), new Knight(color));
        pieces.put(new Position(File.C, rank), new Bishop(color));
        pieces.put(new Position(File.D, rank), new Queen(color));
        pieces.put(new Position(File.E, rank), new King(color));
        pieces.put(new Position(File.F, rank), new Bishop(color));
        pieces.put(new Position(File.G, rank), new Knight(color));
        pieces.put(new Position(File.H, rank), new Rook(color));
    }
}
