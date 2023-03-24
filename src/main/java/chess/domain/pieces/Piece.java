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

    abstract public void validateTeam(final Team team);

    abstract public void checkEachPiece(Position currentPosition, Direction direction, List<Piece> pieces);

    public void checkDirection(Direction direction) {
        if (!this.directions.contains(direction)) {
            throw new IllegalArgumentException("[ERROR] 갈 수 없는 방향입니다.");
        }
    }

    public void checkCanMove(Piece currentPiece, List<Piece> pieces) {
        checkExistPiece(pieces);
        checkSameTeam(currentPiece, pieces);
    }

    private void checkExistPiece(List<Piece> pieces) {
        for(int i=0;i<pieces.size()-1;i++){
            if (pieces.get(i).getTeam() != Team.NEUTRALITY) {
                throw new IllegalArgumentException("[ERROR] 경로에 기물이 존재합니다.");
            }
        }
    }

    private void checkSameTeam(Piece currentPiece, List<Piece> pieces) {
        Team LastPieceTeam = pieces.get(pieces.size() - 1).team;

        if (currentPiece.team == LastPieceTeam) {
            throw new IllegalArgumentException("[ERROR] 도착지에 같은 팀이 존재합니다.");
        }
    }
}
