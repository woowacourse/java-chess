package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.strategy.initializestategy.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private final List<LocationInitializer> locationInitializers =
            Arrays.asList(new PawnInitializer(), new RookInitializer(), new KingInitializer(), new QueenInitializer(),
            new BishopInitializer(), new KnightInitializer());
    private final Map<Position, Piece> chessBoard = new HashMap<>();

    public void initializeBoard() {
        locationInitializers.forEach(initializer -> chessBoard.putAll(initializer.initialize()));
    }

    public Map<Position, Piece> unwrap() {
        return chessBoard;
    }

}
