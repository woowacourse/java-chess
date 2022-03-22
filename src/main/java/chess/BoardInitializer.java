package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BoardInitializer {

    public List<Piece> initialize() {
        final List<Piece> pieces = new ArrayList<>();
        pieces.addAll(createKings(Position.KING));
        pieces.addAll(createQueens(Position.QUEEN));
        pieces.addAll(createBishops(Position.BISHOP));
        pieces.addAll(createKnights(Position.KNIGHT));
        pieces.addAll(createRooks(Position.Rook));
        pieces.addAll(createPawns(Position.PAWN));
        return pieces;
    }

    private List<Piece> createKings(final List<Position> positions) {
        return positions.stream()
                .map(King::new)
                .collect(Collectors.toList());
    }

    private List<Piece> createQueens(final List<Position> positions) {
        return positions.stream()
                .map(Queen::new)
                .collect(Collectors.toList());
    }

    private List<Piece> createBishops(final List<Position> positions) {
        return positions.stream()
                .map(Bishop::new)
                .collect(Collectors.toList());
    }

    private List<Piece> createKnights(final List<Position> positions) {
        return positions.stream()
                .map(Knight::new)
                .collect(Collectors.toList());
    }

    private List<Piece> createRooks(final List<Position> positions) {
        return positions.stream()
                .map(Rook::new)
                .collect(Collectors.toList());
    }

    private List<Piece> createPawns(final List<Position> positions) {
        return positions.stream()
                .map(Pawn::new)
                .collect(Collectors.toList());
    }
}
