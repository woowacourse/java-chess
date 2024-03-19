package chess.domain;

class Piece {

    private final PieceType type;
    private final Team team;

    public Piece(PieceType type, Team team) {
        this.type = type;
        this.team = team;
    }
}
