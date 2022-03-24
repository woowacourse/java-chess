package chess.domain.state;

import chess.domain.Board;
import chess.domain.Location;
import chess.domain.LocationDiff;
import chess.domain.piece.Piece;
import java.util.List;

public class White extends Running {

    public White(Board board) {
        super(board);
    }

    @Override
    public State move(Location source, Location target) {
        Piece piece = getBoard().getPiece(source);
        checkSourceColor(piece);
        LocationDiff locationDiff = source.computeDiff(target);
        checkMovable(piece, locationDiff.getDirection());

// TODO
//        List<Location> list = state.getMovableLocation(sourceLocation);
//        if (!list.contains(targetLocation)) {
//            throw new IllegalArgumentException("없어");
//        }
//        state.isMovable(sourceLocation, targetLocation);
        return new Black(getBoard());
    }

    private void checkSourceColor(Piece piece) {
        if (!piece.isWhite()) {
            throw new IllegalArgumentException("[ERROR] 해당 말은 움직일 수 없습니다.");
        }
    }

    private void checkMovable(Piece piece, Direction direction) {
        if (!piece.isMovableDirection(direction)) {
            throw new IllegalArgumentException("[ERROR] 해당 위치로 이동할 수 없습니다.");
        }
    }
}
