package chess.domain.piece;

import java.util.List;

public class Pieces {

    private static final List<Piece> pieces = createPieces();

    private static List<Piece> createPieces() {
        return List.of(
                new BishopPiece(Color.WHITE), new BishopPiece(Color.BLACK),
                new KingPiece(Color.WHITE), new KingPiece(Color.BLACK),
                new KnightPiece(Color.WHITE), new KnightPiece(Color.BLACK),
                new PawnPiece(Color.WHITE), new PawnPiece(Color.BLACK),
                new QueenPiece(Color.WHITE), new QueenPiece(Color.BLACK),
                new RookPiece(Color.WHITE), new RookPiece(Color.BLACK), new EmptyPiece());
    }

    public static Piece find(final String name) {
        return pieces.stream()
                .filter(piece -> piece.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("[ERROR] 존재하지 않는 Piece 입니다."));
    }
}
