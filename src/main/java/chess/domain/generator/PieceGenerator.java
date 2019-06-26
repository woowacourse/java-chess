package chess.domain.generator;

import chess.domain.piece.Piece;
import chess.domain.Team;
import chess.domain.piece.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class PieceGenerator {
    private static final Map<String, Function<Team, Piece>> pieceGenerator = new HashMap<>();

    static {
        pieceGenerator.put("p", Pawn::new);
        pieceGenerator.put("r", Rook::new);
        pieceGenerator.put("n", Knight::new);
        pieceGenerator.put("b", Bishop::new);
        pieceGenerator.put("k", King::new);
        pieceGenerator.put("q", Queen::new);
    }

    public static Piece generatePiece(String pieceName, String team) {
        return pieceGenerator
                .get(pieceName)
                .apply(Team.valueOf(team));
    }
}
