package chess.domain;

import java.util.*;

public class Board {
    private static final Rank BLACK_KING_RANK = Rank.EIGHT;
    private static final Rank BLACK_PAWN_RANK = Rank.SEVEN;
    private static final Rank WHITE_PAWN_RANK = Rank.TWO;
    private static final Rank WHITE_KING_RANK = Rank.ONE;
    private static final Map<Role, List<File>> INITIAL_FILES;

    static {
        INITIAL_FILES = new HashMap<>();
        INITIAL_FILES.put(Role.PAWN, List.of(File.values()));
        INITIAL_FILES.put(Role.ROOK, List.of(File.A, File.H));
        INITIAL_FILES.put(Role.KNIGHT, List.of(File.B, File.G));
        INITIAL_FILES.put(Role.BISHOP, List.of(File.C, File.F));
        INITIAL_FILES.put(Role.QUEEN, List.of(File.D));
        INITIAL_FILES.put(Role.KING, List.of(File.E));
    }

    private final Map<Square, Piece> board;

    private Board(Map<Square, Piece> board) {
        this.board = board;
    }

    public static Board create() {
        Map<Square, Piece> board = new HashMap<>();
        List<Square> squares = Square.getAllSquares();
        for (Square square : squares) {
            board.put(square, null);
        }
        setupWhite(board);
        setupBlack(board);

        return new Board(board);
    }

    private static void setupBlack(final Map<Square, Piece> board) {
        Role[] roles = Role.values();
        for (Role role : roles) {
            for (File file : INITIAL_FILES.get(role)) {
                Piece piece = new Piece(role, new Side(Color.BLACK));
                if (role.equals(Role.PAWN)) {
                    board.put(Square.of(file, BLACK_PAWN_RANK), piece);
                    continue;
                }
                board.put(Square.of(file, BLACK_KING_RANK), piece);
            }
        }
    }

    private static void setupWhite(final Map<Square, Piece> board) {
        Role[] roles = Role.values();
        for (Role role : roles) {
            for (File file : INITIAL_FILES.get(role)) {
                Piece piece = new Piece(role, new Side(Color.WHITE));
                if (role.equals(Role.PAWN)) {
                    board.put(Square.of(file, WHITE_PAWN_RANK), piece);
                    continue;
                }
                board.put(Square.of(file, WHITE_KING_RANK), piece);
            }
        }
    }

    public static List<Piece> createInitialPieces(Side side) {
        List<Piece> pieces = new ArrayList<>();
        Role[] roles = Role.values();
        Arrays.stream(roles).forEach(role -> {
            for (int i = 0; i < role.getCount(); i++) {
                pieces.add(new Piece(role, side));
            }
        });
        return pieces;
    }

    public Piece findPiece(final File file, final Rank rank) {
        return board.get(Square.of(file, rank));
    }
}
