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
    private BoardInitializer() {
    }

    private static final Map<PieceType, List<Position>> pieceInitialPositions = Map.ofEntries(
            Map.entry(BLACK_PAWN, Arrays.stream(File.values()).map(file -> Position.of(file, SEVEN)).toList()),
            Map.entry(BLACK_ROOK, List.of(Position.of(A, EIGHT), Position.of(H, EIGHT))),
            Map.entry(BLACK_KNIGHT, List.of(Position.of(B, EIGHT), Position.of(G, EIGHT))),
            Map.entry(BLACK_BISHOP, List.of(Position.of(C, EIGHT), Position.of(F, EIGHT))),
            Map.entry(BLACK_QUEEN, List.of(Position.of(D, EIGHT))),
            Map.entry(BLACK_KING, List.of(Position.of(E, EIGHT))),
            Map.entry(WHITE_PAWN, Arrays.stream(File.values()).map(file -> Position.of(file, TWO)).toList()),
            Map.entry(WHITE_ROOK, List.of(Position.of(A, ONE), Position.of(H, ONE))),
            Map.entry(WHITE_KNIGHT, List.of(Position.of(B, ONE), Position.of(G, ONE))),
            Map.entry(WHITE_BISHOP, List.of(Position.of(C, ONE), Position.of(F, ONE))),
            Map.entry(WHITE_QUEEN, List.of(Position.of(D, ONE))),
            Map.entry(WHITE_KING, List.of(Position.of(E, ONE)))
    );

    public static Board init() {
        Map<Position, Piece> positions = new HashMap<>();
        pieceInitialPositions.forEach(((pieceType, position) -> {
            position.forEach(p -> positions.put(p, PieceFactory.create(pieceType)));
        }));

        return new Board(positions);
    }
}
