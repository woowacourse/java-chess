package chess.domain.piece;

import chess.domain.board.File;
import chess.domain.side.Side;

import java.util.Collections;
import java.util.List;

public enum Role {
    VACANT_PIECE(VacantPiece::new, List.of(File.values())),
    PAWN(Pawn::new, Collections.EMPTY_LIST),
    INITIAL_PAWN(InitialPawn::new, List.of(File.values())),
    ROOK(Rook::new, List.of(File.A, File.H)),
    KNIGHT(Knight::new, List.of(File.B, File.G)),
    BISHOP(Bishop::new, List.of(File.C, File.F)),
    QUEEN(Queen::new, List.of(File.D)),
    KING(King::new, List.of(File.E));

    private final Constructor<Side, Role, Piece> createPiece;
    private final List<File> initialFiles;

    Role(final Constructor<Side, Role, Piece> createPiece, List<File> initialFiles) {
        this.createPiece = createPiece;
        this.initialFiles = initialFiles;
    }

    public Piece create(final Side side) {
        return createPiece.construct(side, this);
    }

    @FunctionalInterface
    interface Constructor<T1, T2, R> {
        R construct(T1 t1, T2 t2);
    }
}
