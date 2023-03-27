package chess.domain.piece;

import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.side.Color;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public enum Role {
    VACANT_PIECE(0,
            VacantPiece::new,
            List.of(File.values()),
            Map.of(Color.NOTHING, List.of(Rank.THREE, Rank.FOUR, Rank.FIVE, Rank.SIX))),
    PAWN(1,
            Pawn::new,
            Collections.EMPTY_LIST,
            Collections.EMPTY_MAP),
    INITIAL_PAWN(1,
            InitialPawn::new,
            List.of(File.values()),
            Map.of(Color.WHITE, List.of(Rank.TWO), Color.BLACK, List.of(Rank.SEVEN))),
    ROOK(5,
            Rook::new,
            List.of(File.A, File.H),
            Map.of(Color.WHITE, List.of(Rank.ONE), Color.BLACK, List.of(Rank.EIGHT))),
    KNIGHT(2.5,
            Knight::new,
            List.of(File.B, File.G),
            Map.of(Color.WHITE, List.of(Rank.ONE), Color.BLACK, List.of(Rank.EIGHT))),
    BISHOP(3,
            Bishop::new,
            List.of(File.C, File.F),
            Map.of(Color.WHITE, List.of(Rank.ONE), Color.BLACK, List.of(Rank.EIGHT))),
    QUEEN(9,
            Queen::new,
            List.of(File.D),
            Map.of(Color.WHITE, List.of(Rank.ONE), Color.BLACK, List.of(Rank.EIGHT))),
    KING(0,
            King::new,
            List.of(File.E),
            Map.of(Color.WHITE, List.of(Rank.ONE), Color.BLACK, List.of(Rank.EIGHT)));

    private final double score;
    private final Constructor<Color, Role, Piece> createPiece;
    private final List<File> initialFiles;
    private final Map<Color, List<Rank>> initialRanks;

    Role(final double score, final Constructor<Color, Role, Piece> createPiece, final List<File> initialFiles, final Map<Color, List<Rank>> initialRanks) {
        this.score = score;
        this.createPiece = createPiece;
        this.initialFiles = initialFiles;
        this.initialRanks = initialRanks;
    }

    public Piece create(final Color color) {
        return createPiece.construct(color, this);
    }

    public List<File> getInitialFiles() {
        return initialFiles;
    }

    public List<Rank> getInitialRanksBySide(final Color color) {
        return initialRanks.getOrDefault(color, List.of());
    }

    public double getScore() {
        return score;
    }

    @FunctionalInterface
    interface Constructor<T1, T2, R> {
        R construct(T1 t1, T2 t2);
    }
}
