package chess.domain.piece.character;

public record Character(Team team, Kind kind) {
    public static final Character BLACK_PAWN = new Character(Team.BLACK, Kind.PAWN);
    public static final Character BLACK_KNIGHT = new Character(Team.BLACK, Kind.KNIGHT);
    public static final Character BLACK_BISHOP = new Character(Team.BLACK, Kind.BISHOP);
    public static final Character BLACK_ROOK = new Character(Team.BLACK, Kind.ROOK);
    public static final Character BLACK_QUEEN = new Character(Team.BLACK, Kind.QUEEN);
    public static final Character BLACK_KING = new Character(Team.BLACK, Kind.KING);
    public static final Character WHITE_PAWN = new Character(Team.WHITE, Kind.PAWN);
    public static final Character WHITE_KNIGHT = new Character(Team.WHITE, Kind.KNIGHT);
    public static final Character WHITE_BISHOP = new Character(Team.WHITE, Kind.BISHOP);
    public static final Character WHITE_ROOK = new Character(Team.WHITE, Kind.ROOK);
    public static final Character WHITE_QUEEN = new Character(Team.WHITE, Kind.QUEEN);
    public static final Character WHITE_KING = new Character(Team.WHITE, Kind.KING);
}
