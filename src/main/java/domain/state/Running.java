package domain.state;

import domain.exception.AlreadyStartException;
import domain.piece.objects.Piece;
import domain.piece.position.Position;

import java.util.Map;

public class Running extends Started {

    public Running(Map<Position, Piece> pieces) {
        super(pieces);
    }

    public Running(Map<Position, Piece> pieces, boolean turn) {
        super(pieces, turn);
    }

    private State gameStateByKing(Piece endPiece) {
        if (board.isKingDead(endPiece)) {
            return new Finished(board.getPieceMap());
        }
        return this;
    }

    @Override
    public State move(Position start, Position end) {
        board.checkMovable(start, end, turn);
        Piece endPiece = board.getPiece(end);
        board.move(start, end);
        turn = !turn;
        return gameStateByKing(endPiece);
    }

    @Override
    public State finish() {
        return new Finished(board.getPieceMap());
    }

    @Override
    public State run(Map<Position, Piece> pieces) {
        throw new AlreadyStartException();
    }
}
