package chess.view;

import static java.lang.Character.toLowerCase;
import static java.lang.Character.toUpperCase;

import chess.domain.Team;
import chess.domain.pieces.EmptyPiece;
import chess.domain.pieces.Piece;
import chess.domain.pieces.nonsliding.King;
import chess.domain.pieces.nonsliding.Knight;
import chess.domain.pieces.pawn.Pawn;
import chess.domain.pieces.sliding.Bishop;
import chess.domain.pieces.sliding.Queen;
import chess.domain.pieces.sliding.Rook;
import java.util.Arrays;

public enum PieceType {
    PAWN(Pawn.class, 'P'),
    KNIGHT(Knight.class, 'N'),
    BISHOP(Bishop.class, 'B'),
    ROOK(Rook.class, 'R'),
    QUEEN(Queen.class, 'Q'),
    KING(King.class, 'K'),
    EMPTY(EmptyPiece.class, '.');

    private final Class<? extends Piece> pieceType;
    private final char name;

    PieceType(final Class<? extends Piece> pieceType, final char name) {
        this.pieceType = pieceType;
        this.name = name;
    }

    public static char render(final Piece piece, final Team team) {
        PieceType findPieceType = Arrays.stream(PieceType.values())
                .filter(value -> isSameType(value, piece))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("찾을 수 없는 타입의 기물입니다."));

        return checkTeam(findPieceType, team);
    }

    public static boolean isSameType(PieceType pieceType, final Piece piece) {
        return pieceType.pieceType.isInstance(piece);
    }

    private static char checkTeam(final PieceType findPieceType, final Team team) {
        if (team == Team.NEUTRALITY) {
            return findPieceType.name;
        }
        if (team == Team.BLACK) {
            return toUpperCase(findPieceType.name);
        }
        return toLowerCase(findPieceType.name);
    }
}
