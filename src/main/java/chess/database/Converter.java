package chess.database;

import java.lang.reflect.Constructor;
import java.util.Map;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;

public class Converter {

    private static final Map<Class<? extends Piece>, String> nameByClass = Map.of(
            Pawn.class, "Pawn",
            Rook.class, "Rook",
            Knight.class, "Knight",
            Bishop.class, "Bishop",
            Queen.class, "Queen",
            King.class, "King"
    );

    private static final Map<String, Class<? extends Piece>> classByName = Map.of(
            "Pawn", Pawn.class,
            "Rook", Rook.class,
            "Knight", Knight.class,
            "Bishop", Bishop.class,
            "Queen", Queen.class,
            "King", King.class
    );

    public static String getPieceName(Piece piece) {
        return nameByClass.get(piece.getClass());
    }

    public static Piece getPiece(String pieceName, Team team) throws ReflectiveOperationException {
        Class<? extends Piece> pieceClass = classByName.get(pieceName);
        Constructor<? extends Piece> constructor = pieceClass.getConstructor(Team.class);
        return constructor.newInstance(team);
    }
}
