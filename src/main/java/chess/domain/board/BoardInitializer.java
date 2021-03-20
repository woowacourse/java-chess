package chess.domain.board;

import chess.domain.piece.Blank;
import chess.domain.piece.Piece;
import chess.domain.position.Horizontal;
import chess.domain.position.Position;
import chess.domain.position.Vertical;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class BoardInitializer {

    private static final List<LocationInitializer> locationInitializers;
    private static final List<String> HORIZONTAL_RANGE = Arrays.stream(Horizontal.values()).map(Horizontal::getSymbol)
        .collect(Collectors.toList());
    private static final List<String> VERTICAL_RANGE = Arrays.stream(Vertical.values()).map(Vertical::getSymbol)
        .collect(Collectors.toList());

    static {
        locationInitializers = Arrays.asList(new PawnInitializer(), new RookInitializer(), new KingInitializer(),
                new QueenInitializer(), new BishopInitializer(), new KnightInitializer());
    }

    public static Map<Position, Piece> initializeBoard() {
        final Map<Position, Piece> chessBoard = new TreeMap<>();
        for (final String horizontal : HORIZONTAL_RANGE) {
            VERTICAL_RANGE.forEach(vertical -> chessBoard.put(new Position(horizontal, vertical), Blank.getInstance()));
        }
        locationInitializers.forEach(initializer -> chessBoard.putAll(initializer.initialize()));
        return chessBoard;
    }


}
