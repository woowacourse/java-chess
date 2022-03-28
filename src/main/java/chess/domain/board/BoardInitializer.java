package chess.domain.board;

import chess.domain.piece.*;
import chess.domain.position.CoordinateX;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.HashMap;
import java.util.Map;

public final class BoardInitializer implements Initializable {

    @Override
    public Map<Position, Piece> init() {
        Map<Position, Piece> initialBoard = new HashMap<>();

        initPawn(initialBoard);
        initOtherPiece(initialBoard, Rank.EIGHT, Color.BLACK);
        initOtherPiece(initialBoard, Rank.ONE, Color.WHITE);

        return initialBoard;
    }

    private static void initPawn(final Map<Position, Piece> value) {
        Rank blackPawnRank = Rank.SEVEN;
        Rank whitePawnRank = Rank.TWO;

        initPawn(value, whitePawnRank, Color.WHITE);
        initPawn(value, blackPawnRank, Color.BLACK);
    }

    private static void initPawn(final Map<Position, Piece> value, final Rank pawnRank, final Color color) {
        for (final CoordinateX coordinateX : CoordinateX.values()) {
            value.put(Position.of(coordinateX, pawnRank), new Pawn(color));
        }
    }

    private static void initOtherPiece(final Map<Position, Piece> value, final Rank rank, final Color color) {
        value.put(Position.of(CoordinateX.A, rank), new Rook(color));
        value.put(Position.of(CoordinateX.B, rank), new Knight(color));
        value.put(Position.of(CoordinateX.C, rank), new Bishop(color));
        value.put(Position.of(CoordinateX.D, rank), new Queen(color));
        value.put(Position.of(CoordinateX.E, rank), new King(color));
        value.put(Position.of(CoordinateX.F, rank), new Bishop(color));
        value.put(Position.of(CoordinateX.G, rank), new Knight(color));
        value.put(Position.of(CoordinateX.H, rank), new Rook(color));
    }
}
