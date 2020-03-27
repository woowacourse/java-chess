package chess.domain.chesspiece;

import chess.domain.Direction;
import chess.domain.MoveManager;
import chess.domain.NameUtils;
import chess.domain.Position;
import chess.domain.Team;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static chess.domain.Direction.*;

public class Pawn extends WorthlessPiece {
    private static final List<Direction> DIRECTIONS;
    private static final String NAME = "p";

    static {
        DIRECTIONS = Arrays.asList(UP, RIGHT_UP, LEFT_UP);
    }
    public Pawn(Position position, Team team) {
        super(position, team);
    }


    @Override
    public String getName() {
        return NameUtils.parseName(NAME, team);
    }

    @Override
    public boolean isNeedCheckPath() {
        return false;
    }


    @Override
    public void validateMove(ChessPiece chessPiece) {
        Direction direction = moveManager.calculateDirection(chessPiece.position, DIRECTIONS);
        if (direction == UP && chessPiece.isMatchTeam(Team.BLANK)) {
            return;
        }
        if (isLeftUpOrRightUp(direction) && isNotBlank(chessPiece)) {
            return;
        }
        throw new IllegalArgumentException(MoveManager.CANNOT_MOVE_POSITION);
    }

    private boolean isNotBlank(ChessPiece chessPiece) {
        return chessPiece.isMatchTeam(Team.BLANK) == false;
    }

    private boolean isLeftUpOrRightUp(Direction direction) {
        return direction == RIGHT_UP || direction == LEFT_UP;
    }
}
