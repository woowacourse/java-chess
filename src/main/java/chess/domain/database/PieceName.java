package chess.domain.database;

import java.lang.reflect.Constructor;
import java.util.Arrays;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;

public enum PieceName {

    PAWN("Pawn", Pawn.class),
    ROOK("Rook", Rook.class),
    KNIGHT("Knight", Knight.class),
    BISHOP("Bishop", Bishop.class),
    QUEEN("Queen", Queen.class),
    KING("King", King.class);

    private final String name;
    private final Class<? extends Piece> pieceClass;

    PieceName(final String name, final Class<? extends Piece> pieceClass) {
        this.name = name;
        this.pieceClass = pieceClass;
    }

    public static String from(final Piece piece) {
        Class<? extends Piece> pieceClass = piece.getClass();
        return Arrays.stream(PieceName.values())
                .filter(it -> it.pieceClass == pieceClass)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 클래스는 존재하지 않습니다."))
                .name;
    }

    public static Piece toPiece(final String name, final Team team) throws ReflectiveOperationException {
        Class<? extends Piece> pieceClass = Arrays.stream(PieceName.values())
                .filter(it -> it.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 이름을 가진 기물은 존재하지 않습니다."))
                .pieceClass;
        Constructor<? extends Piece> constructor = pieceClass.getConstructor(Team.class);
        return constructor.newInstance(team);
    }
}
