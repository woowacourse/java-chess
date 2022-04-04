package chess.domain.piece;

import chess.domain.Color;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;

public enum PieceConvertor {
    KING("king", King::new),
    QUEEN("queen", Queen::new),
    BISHOP("bishop", Bishop::new),
    ROOK("rook", Rook::new),
    KNIGHT("knight", Knight::new),
    PAWN("pawn", Pawn::new),
    ;

    private final String value;
    private final Function<Color, Piece> function;

    PieceConvertor(String value, Function<Color, Piece> function) {
        this.value = value;
        this.function = function;
    }


    public static Piece convert(String input) {
        List<String> pieceCharacteristics = List.of(input.split("-"));

        PieceConvertor convertedPiece = Arrays.stream(PieceConvertor.values())
                .filter(pieceConvertor -> pieceConvertor.value.equals(pieceCharacteristics.get(1)))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("해당 타입의 기물은 존재하지 않습니다."));

        return convertedPiece.function.apply(Color.valueOf(pieceCharacteristics.get(0).toUpperCase()));
    }
}
