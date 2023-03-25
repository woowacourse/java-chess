package chess.domain.piece;

import java.util.Collections;

import chess.domain.exception.IllegalMoveException;
import chess.domain.game.Team;
import chess.domain.move.Move;

public class EmptyPiece extends Piece {

    private static final EmptyPiece INSTANCE = new EmptyPiece();

    private EmptyPiece() {
        super(Team.NEUTRAL, Collections.emptySet());
    }

    public static EmptyPiece create() {
        return INSTANCE;
    }

    @Override
    public boolean hasMove(Move move) {
        throw new IllegalMoveException("움직일 기물이 없습니다");
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public double score() {
        return 0;
    }

    @Override
    public PieceType getType() {
        return PieceType.EMPTY;
    }
}
