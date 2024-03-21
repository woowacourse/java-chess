package domain.game;

import domain.position.File;
import domain.position.Position;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static domain.game.PieceType.*;
import static domain.position.File.*;
import static domain.position.Rank.*;

public class BoardInitializer {
    private  BoardInitializer() {
    }

    private static final Map<PieceType, List<Position>> pieceInitialPositions = Map.ofEntries(
            Map.entry(BLACK_PAWN, Arrays.stream(File.values()).map(file -> new Position(file, SEVEN)).toList()),
            Map.entry(BLACK_ROOK, List.of(new Position(A, EIGHT), new Position(H, EIGHT))),
            Map.entry(BLACK_KNIGHT, List.of(new Position(B, EIGHT), new Position(G, EIGHT))),
            Map.entry(BLACK_BISHOP, List.of(new Position(C, EIGHT), new Position(F, EIGHT))),
            Map.entry(BLACK_QUEEN, List.of(new Position(D, EIGHT))),
            Map.entry(BLACK_KING, List.of(new Position(E, EIGHT))),
            Map.entry(WHITE_PAWN, Arrays.stream(File.values()).map(file -> new Position(file, TWO)).toList()),
            Map.entry(WHITE_ROOK, List.of(new Position(A, ONE), new Position(H, ONE))),
            Map.entry(WHITE_KNIGHT, List.of(new Position(B, ONE), new Position(G, ONE))),
            Map.entry(WHITE_BISHOP, List.of(new Position(C, ONE), new Position(F, ONE))),
            Map.entry(WHITE_QUEEN, List.of(new Position(D, ONE))),
            Map.entry(WHITE_KING, List.of(new Position(E, ONE)))
    );

    public static Board init() {
        Map<Position, Piece> positions = new HashMap<>();
        pieceInitialPositions.forEach(((pieceType, position) -> {
            position.forEach(p -> positions.put(p, PieceFactory.create(pieceType)));
        }));

        return new Board(positions);
    }
}
