package chess.domain.state;

import chess.domain.Board;
import chess.domain.Location;
import chess.domain.piece.Piece;

public class White extends Running {

    public White(Board board) {
        super(board);
    }

    @Override
    public State move(Location source, Location target) {
        Piece piece = getBoard().getPiece(source);
        checkRightColor(piece);

//        List<Location> list = state.getMovableLocation(sourceLocation);
//        if (!list.contains(targetLocation)) {
//            throw new IllegalArgumentException("없어");
//        }
//        state.isMovable(sourceLocation, targetLocation);
        return new Black(getBoard());
    }

    private void checkRightColor(Piece piece) {
        if (!piece.isWhite()) {
            throw new IllegalArgumentException("[ERROR] 해당 말은 움직일 수 없습니다.");
        }
    }
}
