package chess.domain.player.repository;

import java.util.Arrays;

import chess.domain.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.movable.Pawn;
import chess.domain.piece.movable.multiple.Bishop;
import chess.domain.piece.movable.multiple.Queen;
import chess.domain.piece.movable.multiple.Rook;
import chess.domain.piece.movable.single.King;
import chess.domain.piece.movable.single.Knight;

public enum PieceConvertor {

    KING("King", King.getInstance()),
    QUEEN("Queen", Queen.getInstance()),
    BISHOP("Bishop", Bishop.getInstance()),
    ROOK("Rook", Rook.getInstance()),
    KNIGHT("Knight", Knight.getInstance()),
    ;

    private final String pieceName;
    private final Piece piece;

    PieceConvertor(final String pieceName, final Piece piece) {
        this.pieceName = pieceName;
        this.piece = piece;
    }

    public static Piece convertToPiece(final Color color, final String pieceName) {
        if (pieceName.equals("Pawn")) {
            return Pawn.getPawnByColor(color);
        }
        return Arrays.stream(values())
                .filter(it -> pieceName.equals(it.pieceName))
                .map(convertor -> convertor.piece)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 이름의 기물을 찾을 수 없습니다."));
    }
}
