package chess.domain.piece;

public class Rook implements Piece {

    public static final double POINT = 5;
    private static final int MOVE_RANGE = 8;

    private final Team team;

    public Rook(Team team) {
        this.team = team;
    }

    @Override
    public DirectionStrategy strategy() {
        return new DirectionStrategy(Direction.linearDirection(), MOVE_RANGE);
    }

    @Override
    public String getName() {
        if (team == Team.BLACK) {
            return "R";
        }
        return "r";
    }

    @Override
    public Team getTeam() {
        return team;
    }

    @Override
    public void validateCurrentTurn(Team team) {
        if (this.team != team) {
            throw new IllegalArgumentException("[ERROR] 상대 팀의 차례입니다.");
        }
    }

    @Override
    public boolean isSameTeam(Team team) {
        return this.team == team;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public double getPoint() {
        return POINT;
    }
}
