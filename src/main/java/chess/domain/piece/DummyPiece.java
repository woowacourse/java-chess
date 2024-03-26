package chess.domain.piece;

import chess.domain.board.Coordinate;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class DummyPiece implements Piece {

    private static final Piece INSTANCE = new DummyPiece();

    private DummyPiece() {
    }

    public static Piece getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Coordinate> legalNextCoordinates(final Coordinate now, final Coordinate destination) {
        throw new NoSuchElementException("보드에 움직일 대상 기물이 없습니다.");
    }

    @Override
    public boolean canMove(final Coordinate now, final Coordinate destination,
                           final Map<Coordinate, Piece> boardInformation) {
        return false;
    }

    @Override
    public Piece updateAfterMove() {
        return this;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public boolean isNotEmpty() {
        return false;
    }

    @Override
    public PieceType getType() {
        return PieceType.EMPTY;
    }

    @Override
    public Team getTeam() {
        return Team.EMPTY;
    }

    @Override
    public boolean isSameTeam(final Piece piece) {
        return false;
    }

    @Override
    public boolean isNotSameTeam(final Piece piece) {
        return false;
    }
}
