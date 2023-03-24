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

import java.util.Map;
import java.util.function.Function;

public class PieceName {

    private static final Map<Class<? extends Piece>, String> pieceName = Map.of(
            King.class, "k",
            Queen.class, "q",
            Rook.class, "r",
            Knight.class, "n",
            Bishop.class, "b",
            Pawn.class, "p",
            Empty.class, "."
    );

    private static final Map<Team, Function<String, String>> changeByTeam = Map.of(
            Team.WHITE, String::toLowerCase,
            Team.BLACK, String::toUpperCase,
            Team.NONE, name -> name
    );

    public static String findByPiece(Piece piece) {
        final Team team = piece.team();
        final String initialName = pieceName.get(piece.getClass());

        return changeByTeam.get(team).apply(initialName);
    }
}
