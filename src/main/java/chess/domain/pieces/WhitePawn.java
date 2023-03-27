package chess.domain.pieces;

import chess.domain.Direction;
import chess.domain.board.Position;
import chess.domain.pieces.component.Team;
import chess.domain.pieces.component.Type;

import java.util.List;

import static chess.domain.Direction.*;
import static chess.domain.pieces.component.Team.WHITE;

public class WhitePawn extends Piece {

    private static final int TWO_STEP = 2;
    private static final int ONE_STEP = 1;

    public WhitePawn(Type type) {
        super(WHITE, type);
        this.directions = List.of(UP, UP_LEFT, UP_RIGHT);
        validateTeam(team);
        this.type = Type.PAWN;
    }

    @Override
    public void validateTeam(Team team) {
        if (team != WHITE) {
            throw new IllegalStateException("[ERROR] 현재 기물은 화이트팀만 가능합니다.");
        }
    }

    @Override
    public void checkStep(Position currentPosition, Direction direction, List<Piece> pieces) {
        if (pieces.size() <= TWO_STEP) {
            if (pieces.size() == TWO_STEP) {
                checkFirstMove(currentPosition);
                return;
            }
            if (pieces.size() == ONE_STEP && direction != UP) {
                checkCanEat(pieces.get(0));
                return;
            }
            return;
        }
        throw new IllegalArgumentException("[ERROR] 움직일 수 없는 거리입니다.");
    }

    private void checkFirstMove(Position currentPosition) {
        if (currentPosition.getRank() != 2) {
            throw new IllegalArgumentException("[ERROR] 폰은 처음만 두칸움직일 수 있습니다.");
        }
    }

    private void checkCanEat(Piece targetPiece) {
        if (targetPiece.getTeam() != Team.BLACK) {
            throw new IllegalArgumentException("[ERROR] 도착지점이 상대편 기물이 아니므로 이동할 수 없습니다.");
        }
    }

    @Override
    public void checkExistPiece(List<Piece> pieces) {
    }
}
