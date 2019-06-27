package chess.domain.path;

import java.util.function.Supplier;

public enum PathFactory {
    WHITE_PAWN(WhitePawnPath::getInstance),
    BLACK_PAWN(BlackPawnPath::getInstance),
    BISHOP(BishopPath::getInstance),
    KING(KingPath::getInstance),
    KNIGHT(KnightPath::getInstance),
    QUEEN(QueenPath::getInstance),
    ROOK(RookPath::getInstance);

    private Supplier<Path> path;

    PathFactory(Supplier<Path> path) {
        this.path = path;
    }

    public Path create() {
        return path.get();
    }
}
