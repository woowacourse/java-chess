package domain.state;

import domain.exception.AlreadyStartException;
import domain.exception.ImmovableSamePositionException;
import domain.piece.objects.Piece;
import domain.piece.position.Position;

import java.util.Map;

public class Running extends Started {

    public Running(Map<Position, Piece> pieces) {
        super(pieces);
    }

    private void chekMovablePiece(Position start, Position end, boolean turn) {
        checkSamePosition(start, end);
        board.checkMovable(start, turn);
    }

    private void checkSamePosition(Position start, Position end) {
        if (start.equals(end)) {
            throw new ImmovableSamePositionException();
        }
    }

    private State checkKingState(Piece endPiece) {
        if (endPiece.isKingDead()) {
            return new Finished(board.getBoard());
        }
        return this;
    }

    @Override
    public State move(Position start, Position end) {
        chekMovablePiece(start, end, turn);
        Piece endPiece = board.getPiece(end);
        board.move(start, end);
        turn = !turn;
        return checkKingState(endPiece);
    }

    @Override
    public State finish() {
        return new Finished(board.getBoard());
    }

    @Override
    public State run(Map<Position, Piece> pieces) {
        throw new AlreadyStartException();
    }
}
