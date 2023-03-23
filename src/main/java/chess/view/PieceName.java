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

    public static String findNameByPiece(Piece piece) {
        final String initialName = pieceName.get(piece.getClass());

        return changeNameByTeam(initialName, piece.team());
    }

    private static String changeNameByTeam(final String name, final Team team) {
        if (Team.WHITE == team) {
            return name.toLowerCase();
        }
        return name.toUpperCase();
    }
}
