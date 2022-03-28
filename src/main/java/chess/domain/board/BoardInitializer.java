package chess.domain.board;

import chess.domain.piece.*;
import chess.domain.position.CoordinateX;
import chess.domain.position.Position;
import chess.domain.position.CoordinateY;

import java.util.HashMap;
import java.util.Map;

public final class BoardInitializer implements Initializable {

    @Override
    public Map<Position, Piece> init() {
        Map<Position, Piece> initialBoard = new HashMap<>();

        initPawn(initialBoard);
        initOtherPiece(initialBoard, CoordinateY.EIGHT, Color.BLACK);
        initOtherPiece(initialBoard, CoordinateY.ONE, Color.WHITE);

        return initialBoard;
    }

    private static void initPawn(final Map<Position, Piece> value) {
        CoordinateY blackPawnCoordinateY = CoordinateY.SEVEN;
        CoordinateY whitePawnCoordinateY = CoordinateY.TWO;

        initPawn(value, whitePawnCoordinateY, Color.WHITE);
        initPawn(value, blackPawnCoordinateY, Color.BLACK);
    }

    private static void initPawn(final Map<Position, Piece> value, final CoordinateY pawnCoordinateY, final Color color) {
        for (final CoordinateX coordinateX : CoordinateX.values()) {
            value.put(Position.of(coordinateX, pawnCoordinateY), new Pawn(color));
        }
    }

    private static void initOtherPiece(final Map<Position, Piece> value, final CoordinateY coordinateY, final Color color) {
        value.put(Position.of(CoordinateX.A, coordinateY), new Rook(color));
        value.put(Position.of(CoordinateX.B, coordinateY), new Knight(color));
        value.put(Position.of(CoordinateX.C, coordinateY), new Bishop(color));
        value.put(Position.of(CoordinateX.D, coordinateY), new Queen(color));
        value.put(Position.of(CoordinateX.E, coordinateY), new King(color));
        value.put(Position.of(CoordinateX.F, coordinateY), new Bishop(color));
        value.put(Position.of(CoordinateX.G, coordinateY), new Knight(color));
        value.put(Position.of(CoordinateX.H, coordinateY), new Rook(color));
    }
}
