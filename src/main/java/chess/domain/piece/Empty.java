package chess.domain.piece;

import chess.domain.direction.Direction;

import java.util.Set;

public class Empty extends Piece {
    private static final Empty empty = new Empty();
    
    private Empty() {
        super(Team.EMPTY);
    }
    
    public static Empty getInstance() {
        return empty;
    }
    
    @Override
    public Set<Direction> directions() {
        throw emptyPieceException();
    }
    
    @Override
    public PieceMovingType movingType() {
        throw emptyPieceException();
    }
    
    private UnsupportedOperationException emptyPieceException() {
        return new UnsupportedOperationException("기물이 없는 좌표이기에, 해당 기능을 지원하지 않습니다.");
    }
    
    @Override
    public PieceType pieceType() {
        return PieceType.EMPTY;
    }
    
    @Override
    public String toString() {
        return "Empty{" +
                "team=" + team +
                '}';
    }
}
