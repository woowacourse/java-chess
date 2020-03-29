package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Position;

import java.util.List;
import java.util.Objects;

public abstract class Piece {
    private static final String PIECE_IMPOSSIBLE_MOVE_EXCEPTION_MESSAGE = "해당 포지션으로 이동할 수 없습니다.";
    private static final int END_INDEX = 8;
    private static final int START_INDEX = 1;
    private static final char WHITE_KING_REPRESENTATION = 'k';
    private static final char BLACK_KING_REPRESENTATION = 'K';

    protected final char representation;
    protected final Team team;
    protected final Position position;

    public Piece(char representation, Team team, Position position) {
        this.representation = representation;
        this.team = team;
        this.position = position;
    }

    public char getRepresentation() {
        return representation;
    }

    public Position getPosition() {
        return position;
    }

    public boolean isMovable(final Board board, final Position to) throws IllegalAccessException {
        if (getPossiblePositions(board).contains(to)) {
            return true;
        }
        throw new IllegalAccessException(PIECE_IMPOSSIBLE_MOVE_EXCEPTION_MESSAGE);
    }

    protected boolean isInBoardRange(Position nextPosition) {
        return nextPosition.getX() <= END_INDEX && nextPosition.getX() >= START_INDEX &&
                nextPosition.getY() <= END_INDEX && nextPosition.getY() >= START_INDEX;
    }

    public boolean isKing() {
        return representation == WHITE_KING_REPRESENTATION | representation == BLACK_KING_REPRESENTATION;
    }

    public boolean isOtherTeam(Piece nextPiece) {
        return this.team.isNotSame(nextPiece.team);
    }

    public boolean isSameTeam(Team team) {
        return this.team == team;
    }

    public boolean isPawn() {
        return this.getClass().equals(WhitePawn.class) | this.getClass().equals(BlackPawn.class);
    }

    @Override
    public String toString() {
        return String.valueOf(representation);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Piece piece = (Piece) o;
        return representation == piece.representation &&
                Objects.equals(position, piece.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(representation, position);
    }

    public abstract List<Position> getPossiblePositions(Board board);

    public abstract Piece movePiecePosition(Position toPosition);
}
