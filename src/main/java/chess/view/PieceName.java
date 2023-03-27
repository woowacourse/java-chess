package chess.view;

import chess.domain.piece.Bishop;
import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.team.Team;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class PieceName {

    private static final Map<Class<? extends Piece>, String> pieceToName;
    private static final Map<Class<? extends Piece>, String> pieceTofullName;
    private static final Map<Team, Function<String, String>> changeByTeam;

    static {
        pieceToName = new HashMap<>();
        pieceToName.put(King.class, "k");
        pieceToName.put(Queen.class, "q");
        pieceToName.put(Rook.class, "r");
        pieceToName.put(Knight.class, "n");
        pieceToName.put(Bishop.class, "b");
        pieceToName.put(Pawn.class, "p");
        pieceToName.put(Empty.class, ".");

        pieceTofullName = new HashMap<>();
        pieceTofullName.put(King.class, "KING");
        pieceTofullName.put(Queen.class, "QUEEN");
        pieceTofullName.put(Bishop.class, "BISHOP");
        pieceTofullName.put(Knight.class, "KNIGHT");
        pieceTofullName.put(Rook.class, "ROOK");
        pieceTofullName.put(Pawn.class, "PAWN");
        pieceTofullName.put(Empty.class, "EMPTY");

        changeByTeam = new HashMap<>();
        changeByTeam.put(Team.WHITE, String::toLowerCase);
        changeByTeam.put(Team.BLACK, String::toUpperCase);
        changeByTeam.put(Team.NONE, name -> name);
    }

    public static String findByPiece(final Piece piece) {
        final Team team = piece.team();
        final String initialName = pieceToName.get(piece.getClass());

        return changeByTeam.get(team).apply(initialName);
    }

    public static String findByFullName(final Piece piece) {
        return pieceTofullName.get(piece.getClass());
    }
}
