package chess.domain.piece;

public class Pawn implements Piece {

    public static final double POINT = 1;
    public static final int MOVE_FIRST_RANGE = 2;
    public static final int MOVE_DEFAULT_RANGE = 1;

    private final Team team;

    public Pawn(Team team) {
        this.team = team;
    }

    @Override
    public DirectionStrategy strategy() {
        if (team == Team.BLACK) {
            return new DirectionStrategy(Direction.blackPawnDirection(), MOVE_FIRST_RANGE);
        }
        return new DirectionStrategy(Direction.whitePawnDirection(), MOVE_FIRST_RANGE);
    }

    @Override
    public String getName() {
        if (team == Team.BLACK) {
            return "P";
        }
        return "p";
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
        return true;
    }

    @Override
    public double getPoint() {
        return POINT;
    }
}
