package chess.piece;

import static chess.piece.detail.Color.BLACK;
import static chess.piece.detail.Color.WHITE;

import java.util.ArrayList;
import java.util.List;

public class Pieces {

    private static final List<Piece> pieces = new ArrayList<>();

    static {
        pieces.add(new King(BLACK));
        pieces.add(new King(WHITE));
        pieces.add(new Queen(BLACK));
        pieces.add(new Queen(WHITE));
        pieces.add(new Bishop(BLACK));
        pieces.add(new Bishop(WHITE));
        pieces.add(new Knight(BLACK));
        pieces.add(new Knight(WHITE));
        pieces.add(new Rook(BLACK));
        pieces.add(new Rook(WHITE));
        pieces.add(new Pawn(BLACK));
        pieces.add(new Pawn(WHITE));
    }

    public static Piece valueOf(String name) {
        final String[] split = name.split("-");

        return pieces.stream()
                .filter(piece -> piece.getName().name().equals(split[1]))
                .filter(piece -> piece.getColor().name().equals(split[0]))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 기물입니다."));
    }
}
