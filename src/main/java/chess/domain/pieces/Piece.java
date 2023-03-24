package chess.domain.pieces;

import chess.domain.Direction;
import chess.domain.board.Position;
import chess.domain.pieces.component.Name;
import chess.domain.pieces.component.Team;

import java.util.List;

public abstract class Piece {

    protected final Team team;
    protected Name name;
    protected List<Direction> directions;

    public Piece(final Team team) {
        this.team = team;
    }

    public Name getName() {
        return name;
    }

    public Team getTeam() {
        return this.team;
    }


    public void checkDirection(Direction direction) {
        if (!this.directions.contains(direction)) {
            throw new IllegalArgumentException("[ERROR] 갈 수 없는 방향입니다.");
        }
    }

    public void checkSameTeam(Piece currentPiece, Piece targetPiece) {
        if (currentPiece.getTeam() == targetPiece.getTeam()) {
            throw new IllegalArgumentException("[ERROR] 도착지에 같은 팀이 존재합니다.");
        }
    }

    public boolean isBlackTeam(){
        return this.team == Team.BLACK;
    }

    public boolean isWhiteTeam(){
        return this.team == Team.WHITE;
    }

    abstract public void validateTeam(final Team team);

    abstract public void checkStep(Position currentPosition, Direction direction, List<Piece> pieces);

    abstract public void checkExistPiece(List<Piece> pieces);
}
