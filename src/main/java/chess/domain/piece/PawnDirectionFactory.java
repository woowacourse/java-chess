package chess.domain.piece;

import static chess.domain.board.Direction.D;
import static chess.domain.board.Direction.DL;
import static chess.domain.board.Direction.DR;
import static chess.domain.board.Direction.U;
import static chess.domain.board.Direction.UL;
import static chess.domain.board.Direction.UR;

import chess.domain.board.Direction;
import java.util.List;

public class PawnDirectionFactory {
    private static final List<Direction> BLACK_PAWN_DIRECTIONS = List.of(D, DR, DL);
    private static final List<Direction> WHITE_PAWN_DIRECTIONS = List.of(U, UR, UL);

    public static List<Direction> getDirections(Team team) {
        if (team.isBlack()) {
            return BLACK_PAWN_DIRECTIONS;
        }
        return WHITE_PAWN_DIRECTIONS;
    }
}
