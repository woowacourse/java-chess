package chess.domain.piece.fixedmove;

import static chess.domain.board.Weight.ONE_DOWN;
import static chess.domain.board.Weight.ONE_LEFT;
import static chess.domain.board.Weight.ONE_LEFT_ONE_DOWN;
import static chess.domain.board.Weight.ONE_LEFT_ONE_UP;
import static chess.domain.board.Weight.ONE_RIGHT;
import static chess.domain.board.Weight.ONE_RIGHT_ONE_DOWN;
import static chess.domain.board.Weight.ONE_RIGHT_ONE_UP;
import static chess.domain.board.Weight.ONE_UP;
import static chess.domain.piece.Team.BLACK;
import static chess.domain.piece.Team.WHITE;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;
import java.util.Map.Entry;
import java.util.Set;

public class King extends FixedMovePiece {

    public static final Piece WHITE_KING = new King(WHITE);
    public static final Piece BLACK_KING = new King(BLACK);

    private King(Team team) {
        super(PieceType.KING, team);
    }

    @Override
    Set<Entry<Integer, Integer>> weights() {
        return Set.of(
                ONE_LEFT.value(),
                ONE_RIGHT.value(),
                ONE_UP.value(),
                ONE_DOWN.value(),
                ONE_LEFT_ONE_DOWN.value(),
                ONE_LEFT_ONE_UP.value(),
                ONE_RIGHT_ONE_DOWN.value(),
                ONE_RIGHT_ONE_UP.value()
        );
    }
}

