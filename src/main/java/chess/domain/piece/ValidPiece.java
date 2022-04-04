package chess.domain.piece;

public abstract class ValidPiece extends Piece {

    protected ValidPiece(final String name, final Team team, final double point) {
        super(name, team, point);
        if (team == Team.NONE) {
            throw new IllegalArgumentException("[ERROR] 기물의 Team 은 BLACK 혹은 WHITE 만 가능합니다.");
        }
    }

    @Override
    public boolean isBlank() {
        return false;
    }
}
