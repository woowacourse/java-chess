package chess.domain.pieces;

import chess.domain.board.Position;
import chess.domain.pieces.component.Team;
import chess.domain.Direction;
import chess.domain.pieces.component.Name;

import java.util.List;

import static chess.domain.Direction.KNIGHT;

public class Knight extends Piece {

    private static final String KNIGHT_NAME = "N";

    public Knight(final Team team) {
        super(team);
        this.directions = List.of(KNIGHT);
        validateTeam(team);
        initialName(team);
    }

    private void initialName(Team team) {
        if (team == Team.BLACK) {
            this.name = new Name(KNIGHT_NAME);
            return;
        }
        this.name = new Name(KNIGHT_NAME.toLowerCase());
    }

    @Override
    public void validateTeam(Team team) {
        if(team == Team.NEUTRALITY){
            throw new IllegalStateException("중립팀은 emptyPiece 만 가능합니다");
        }
    }

    @Override
    public void checkStep(Position currentPosition, Direction direction, List<Piece> pieces) {
    }

    @Override
    public void checkExistPiece(List<Piece> pieces) {
    }

}
