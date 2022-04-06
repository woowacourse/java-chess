package chess.dao;

import chess.domain.Team;
import chess.domain.piece.Bishop;
import chess.domain.piece.Blank;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class PieceFactory {

    private static final Map<String, BiFunction<Team, Position, Piece>> temp = new HashMap<>() {{
        put("Pawn", Pawn::new);
        put("Knight", Knight::new);
        put("King", King::new);
        put("Bishop", Bishop::new);
        put("Queen", Queen::new);
        put("Rook", Rook::new);
        put("Blank", Blank::new);
    }};

    public static Piece of(String type, String col, int row, String team) {
        return temp.get(type).apply(Team.valueOf(team), Position.from(col + row));
    }
}
