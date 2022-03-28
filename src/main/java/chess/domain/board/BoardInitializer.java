package chess.domain.board;

import chess.domain.piece.*;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.HashMap;
import java.util.Map;

public final class BoardInitializer implements Initializable {

    @Override
    public Map<Position, Piece> init() {
        final Map<Position, Piece> initialBoard = new HashMap<>();

        initPawn(initialBoard);
        initOtherPiece(initialBoard, Rank.EIGHT, Color.BLACK);
        initOtherPiece(initialBoard, Rank.ONE, Color.WHITE);

        return initialBoard;
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
