package chess.domain.chesspiece.slidingPiece;

import chess.domain.chesspiece.Piece;
import chess.domain.chesspiece.Role;
import chess.domain.chesspiece.Team;
import chess.domain.position.Direction;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static chess.domain.chesspiece.Role.*;

public class Rook extends SlidingPiece {

    public Rook(Team team) {
        super(team);
    }

    @Override
    protected void validateMovingRule(Position source, Position target) {
        if (!source.isSameRank(target) && !source.isSameFile(target)) {
            throw new IllegalArgumentException("이동할 수 없습니다.");
        }
    }

    @Override
    public Role getRole() {
        if (getTeam().isWhite()) {
            return WHITE_ROOK;
        }
        return BLACK_ROOK;
    }
}
