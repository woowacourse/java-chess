package chess.domain.piece.directionmove;

import static chess.domain.piece.Team.BLACK;
import static chess.domain.piece.Team.WHITE;

import chess.domain.board.Direction;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class Queen extends DirectionMovePiece {
    public static final Piece WHITE_QUEEN = new Queen(WHITE);
    public static final Piece BLACK_QUEEN = new Queen(BLACK);

    private Queen(Team team) {
        super(PieceType.QUEEN, team);
    }

    @Override
    Set<Direction> legalDirections() {
        return Arrays.stream(Direction.values())
                .collect(Collectors.toSet());
    }
}
