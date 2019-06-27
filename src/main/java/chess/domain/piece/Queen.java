package chess.domain.piece;

import chess.domain.board.DirectionType;
import chess.domain.board.PlayerType;
import chess.domain.board.Point;

import java.util.Optional;

public class Queen extends Piece {

    public Queen(PlayerType playerType) {
        super(playerType);
    }

    @Override
    public boolean isMovable(Point prev, Point next) {
        Optional<DirectionType> directionType;
        try {
            directionType = Optional.of(DirectionType.valueOf(prev, next));
        } catch (IllegalArgumentException e) {
            return false;
        }
        return directionType.isPresent();
    }

    @Override
    public String pieceToString() {
        return isWhite() ? "q" : "Q";
    }
}
