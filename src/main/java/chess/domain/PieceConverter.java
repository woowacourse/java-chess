package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.multiple.Bishop;
import chess.domain.piece.multiple.Queen;
import chess.domain.piece.multiple.Rook;
import chess.domain.piece.pawn.Pawn;
import chess.domain.piece.single.King;
import chess.domain.piece.single.Knight;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;

public enum PieceConverter {

    KING("king", color -> new Piece(color, new King())),
    QUEEN("queen", color -> new Piece(color, new Queen())),
    BISHOP("bishop", color -> new Piece(color, new Bishop())),
    ROOK("rook", color -> new Piece(color, new Rook())),
    KNIGHT("knight", color -> new Piece(color, new Knight())),
    PAWN("pawn", color -> new Piece(color, new Pawn(color))),
    ;

    private final String pieceName;
    private final Function<Color, Piece> pieceCreator;

    PieceConverter(String pieceName, Function<Color, Piece> pieceCreator) {
        this.pieceName = pieceName;
        this.pieceCreator = pieceCreator;
    }

    public static Piece parseToPiece(String name, Color color) {
        Objects.requireNonNull(name, "name은 null이 들어올 수 없습니다.");
        return Arrays.stream(values())
                .filter(pieceConvertor -> pieceConvertor.pieceName.equals(name))
                .findAny()
                .map(pieceConvertor -> pieceConvertor.pieceCreator.apply(color))
                .orElseThrow(() -> new IllegalArgumentException("없는 기물 정보입니다."));
    }
}
