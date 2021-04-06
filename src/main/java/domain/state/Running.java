package domain.state;

import domain.Board;
import domain.exception.AlreadyStartException;
import domain.piece.objects.Piece;
import domain.piece.position.Position;

public class Running extends Started {

    public Running(Board board) {
        super(board);
    }

    public Running(Board board, boolean turn) {
        super(board, turn);
    }

    private State gameStateByKing(Piece endPiece) {
        if (board.isKingDead(endPiece)) {
            return new Finished(board);
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
        return new Finished(board);
    }

    @Override
    public State run() {
        throw new AlreadyStartException();
    }
}
