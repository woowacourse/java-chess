package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.Role;
import chess.domain.square.*;

import java.util.*;

public class BoardFactory {
    private static final Map<Side, Map<Role, Rank>> INITIAL_RANKS;
    private static final Map<Role, List<File>> INITIAL_FILES;

    private static final Map<Square, Piece> board = new LinkedHashMap<>();

    static {
        INITIAL_FILES = createInitialFiles();
        INITIAL_RANKS = createInitialRanks();
    }

    private static Map<Role, List<File>> createInitialFiles() {
        Map<Role, List<File>> initialFiles = new HashMap<>();
        initialFiles.put(Role.INITIAL_PAWN, List.of(File.values()));
        initialFiles.put(Role.ROOK, List.of(File.A, File.H));
        initialFiles.put(Role.KNIGHT, List.of(File.B, File.G));
        initialFiles.put(Role.BISHOP, List.of(File.C, File.F));
        initialFiles.put(Role.QUEEN, List.of(File.D));
        initialFiles.put(Role.KING, List.of(File.E));
        return initialFiles;
    }

    private static Map<Side, Map<Role, Rank>> createInitialRanks() {
        Map<Side, Map<Role, Rank>> initialRanks = new HashMap<>();
        Map<Role, Rank> blackRanks = new HashMap<>();
        Arrays.stream(Role.values()).forEach(role -> blackRanks.put(role, Rank.EIGHT));
        blackRanks.put(Role.INITIAL_PAWN, Rank.SEVEN);
        Map<Role, Rank> whiteRanks = new HashMap<>();
        Arrays.stream(Role.values()).forEach(role -> whiteRanks.put(role, Rank.ONE));
        whiteRanks.put(Role.INITIAL_PAWN, Rank.TWO);
        initialRanks.put(Side.from(Color.BLACK), blackRanks);
        initialRanks.put(Side.from(Color.WHITE), whiteRanks);
        return initialRanks;
    }

    public static Board create() {
        setup(Side.from(Color.BLACK));
        setup(Side.from(Color.WHITE));

        return new Board(board);
    }

    public static Board create(final Map<Square, Piece> board) {
        return new Board(board);
    }

    private static void setup(final Side side) {
        Map<Role, Rank> ranksBySide = INITIAL_RANKS.get(side);
        for (Role role : INITIAL_FILES.keySet()) {
            putPiece(ranksBySide.get(role), INITIAL_FILES.get(role), role.create(side));
        }
    }

    private static void putPiece(final Rank rank, final List<File> files, final Piece piece) {
        for (File file : files) {
            board.put(Square.of(file, rank), piece);
        }
    }
}
