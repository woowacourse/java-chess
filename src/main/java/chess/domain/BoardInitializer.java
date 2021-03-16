package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.strategy.initializestategy.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardInitializer {
    private static final List<LocationInitializer> locationInitializers;

    static {
        locationInitializers = Arrays.asList(new PawnInitializer(), new RookInitializer(), new KingInitializer(),
                new QueenInitializer(), new BishopInitializer(), new KnightInitializer());
    }

    public static Map<Position, Piece> initializeBoard() {
        final Map<Position, Piece> chessBoard = new HashMap<>();
        locationInitializers.forEach(initializer -> chessBoard.putAll(initializer.initialize()));
        return chessBoard;
    }
}
