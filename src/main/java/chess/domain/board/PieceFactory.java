package chess.domain.board;

import java.util.Arrays;
import java.util.function.Function;

public enum PieceFactory {

    KING("king", King::new),
    QUEEN("queen", Queen::new),
    BISHOP("bishop", Bishop::new),
    ROOK("rook", Rook::new),
    KNIGHT("knight", Knight::new),
    PAWN("pawn", Pawn::new),
    ;

    private final String type;
    private final Function<Color, Piece> pieceGenerator;


    PieceFactory(String type, Function<Color, Piece> pieceGenerator) {
        this.type = type;
        this.pieceGenerator = pieceGenerator;
    }

    public static Piece generate(String type, Color color) {
        return Arrays.stream(PieceFactory.values())
                .filter(pieceFactory -> pieceFactory.type.equals(type))
                .findAny()
                .map(pieceFactory -> pieceFactory.pieceGenerator.apply(color))
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 피스 정보입니다"));
    }

}
