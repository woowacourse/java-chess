package chess.domain.piece;

public abstract class ValidPiece extends Piece {

    protected ValidPiece(final Team team, final double point) {
        super(team, point);
        if (team == Team.NONE) {
            throw new IllegalArgumentException("[ERROR] 팀을 입력해주세요.");
        }
    }

    @Override
    public boolean isBlank() {
        return false;
    }
}
