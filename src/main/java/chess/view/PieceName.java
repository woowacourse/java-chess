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
    private static final Map<String, Piece> nameToPiece;
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

        nameToPiece = new HashMap<>();
        nameToPiece.put("k", new King(Team.WHITE));
        nameToPiece.put("K", new King(Team.BLACK));
        nameToPiece.put("q", new Queen(Team.WHITE));
        nameToPiece.put("Q", new Queen(Team.BLACK));
        nameToPiece.put("r", new Rook(Team.WHITE));
        nameToPiece.put("R", new Rook(Team.BLACK));
        nameToPiece.put("n", new Knight(Team.WHITE));
        nameToPiece.put("N", new Knight(Team.BLACK));
        nameToPiece.put("b", new Bishop(Team.WHITE));
        nameToPiece.put("B", new Bishop(Team.BLACK));
        nameToPiece.put("p", new Pawn(Team.WHITE));
        nameToPiece.put("P", new Pawn(Team.BLACK));
        nameToPiece.put(".", new Empty(Team.NONE));

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

    public static Piece findByName(final String name) {
        return nameToPiece.get(name);
    }
}
