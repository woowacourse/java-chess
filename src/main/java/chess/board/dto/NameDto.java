package chess.board.dto;

import chess.piece.directional.normal.longrange.Bishop;
import chess.piece.directional.normal.King;
import chess.piece.Knight;
import chess.piece.directional.Pawn;
import chess.piece.Piece;
import chess.piece.directional.normal.longrange.Queen;
import chess.piece.directional.normal.longrange.Rook;
import chess.piece.Side;
import java.util.HashMap;
import java.util.Map;

public class NameDto {

    private static final Map<Class<? extends Piece>, Name> nameByPiece = new HashMap<>();
    private final String name;

    static {
        nameByPiece.put(King.class, Name.KING);
        nameByPiece.put(Queen.class, Name.QUEEN);
        nameByPiece.put(Bishop.class, Name.BISHOP);
        nameByPiece.put(Knight.class, Name.KNIGHT);
        nameByPiece.put(Rook.class, Name.ROOK);
        nameByPiece.put(Pawn.class, Name.PAWN);
    }

    public NameDto(Piece piece) {
        this.name = getNameBySide(nameByPiece.get(piece.getClass()), piece.getSide());
    }

    private String getNameBySide(final Name pieceName, final Side side) {
        if (Side.BLACK == side) {
            return pieceName.getUpperCaseValue();
        }
        return pieceName.getLowerCaseValue();
    }


    public String getName() {
        return name;
    }
}
