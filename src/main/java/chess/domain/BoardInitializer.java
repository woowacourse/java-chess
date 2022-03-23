package chess.domain;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.Map;

public final class BoardInitializer {

    private BoardInitializer() {
    }

    public static void init(final Map<Position, Piece> value) {
        initPawn(value);
        initOtherPiece(value, Rank.EIGHT, Color.BLACK);
        initOtherPiece(value, Rank.ONE, Color.WHITE);
    }

    private static void initPawn(final Map<Position, Piece> value) {
        final var blackPawnRank = Rank.SEVEN;
        final var whitePawnRank = Rank.TWO;

        initPawn(value, whitePawnRank, Color.WHITE);
        initPawn(value, blackPawnRank, Color.BLACK);
    }

    private static void initPawn(final Map<Position, Piece> value, final Rank pawnRank, final Color color) {
        for (final File file : File.values()) {
            value.put(Position.of(file, pawnRank), new Pawn(color));
        }
    }

    private static void initOtherPiece(final Map<Position, Piece> value, final Rank rank, final Color color) {
        value.put(Position.of(File.A, rank), new Rook(color));
        value.put(Position.of(File.B, rank), new Knight(color));
        value.put(Position.of(File.C, rank), new Bishop(color));
        value.put(Position.of(File.D, rank), new Queen(color));
        value.put(Position.of(File.E, rank), new King(color));
        value.put(Position.of(File.F, rank), new Bishop(color));
        value.put(Position.of(File.G, rank), new Knight(color));
        value.put(Position.of(File.H, rank), new Rook(color));
    }
}
