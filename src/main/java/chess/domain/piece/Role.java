package chess.domain.piece;

import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.side.Color;
import chess.domain.side.Side;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public enum Role {
    VACANT_PIECE(VacantPiece::new,
            List.of(File.values()),
            Map.of(Side.from(Color.NOTHING), List.of(Rank.THREE, Rank.FOUR, Rank.FIVE, Rank.SIX))),
    PAWN(Pawn::new,
            Collections.EMPTY_LIST,
            Collections.EMPTY_MAP),
    INITIAL_PAWN(InitialPawn::new,
            List.of(File.values()),
            Map.of(Side.from(Color.WHITE), List.of(Rank.TWO), Side.from(Color.BLACK), List.of(Rank.SEVEN))),
    ROOK(Rook::new,
            List.of(File.A, File.H),
            Map.of(Side.from(Color.WHITE), List.of(Rank.ONE), Side.from(Color.BLACK), List.of(Rank.EIGHT))),
    KNIGHT(Knight::new,
            List.of(File.B, File.G),
            Map.of(Side.from(Color.WHITE), List.of(Rank.ONE), Side.from(Color.BLACK), List.of(Rank.EIGHT))),
    BISHOP(Bishop::new,
            List.of(File.C, File.F),
            Map.of(Side.from(Color.WHITE), List.of(Rank.ONE), Side.from(Color.BLACK), List.of(Rank.EIGHT))),
    QUEEN(Queen::new,
            List.of(File.D),
            Map.of(Side.from(Color.WHITE), List.of(Rank.ONE), Side.from(Color.BLACK), List.of(Rank.EIGHT))),
    KING(King::new,
            List.of(File.E),
            Map.of(Side.from(Color.WHITE), List.of(Rank.ONE), Side.from(Color.BLACK), List.of(Rank.EIGHT)));

    private final Constructor<Side, Role, Piece> createPiece;
    private final List<File> initialFiles;
    private final Map<Side, List<Rank>> initialRanks;

    Role(final Constructor<Side, Role, Piece> createPiece, List<File> initialFiles, Map<Side, List<Rank>> initialRanks) {
        this.createPiece = createPiece;
        this.initialFiles = initialFiles;
        this.initialRanks = initialRanks;
    }

    public Piece create(final Side side) {
        return createPiece.construct(side, this);
    }

    public List<File> getInitialFiles() {
        return initialFiles;
    }

    public List<Rank> getInitialRanksBySide(Side side) {
        return initialRanks.getOrDefault(side, List.of());
    }

    @FunctionalInterface
    interface Constructor<T1, T2, R> {
        R construct(T1 t1, T2 t2);
    }
}
