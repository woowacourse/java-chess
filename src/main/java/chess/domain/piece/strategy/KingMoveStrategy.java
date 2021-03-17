package chess.domain.piece.strategy;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.exceptions.InvalidMoveException;
import chess.domain.piece.Piece;

public class KingMoveStrategy extends BasicMoveStrategy {

    private static final int KING_DISTANCE_LIMIT = 1;

    @Override
    public void move(Position source, Position target, Board board) {
        checkValidMove(source, target, board);

        Piece originalPiece = board.checkPieceAtPosition(source);
        board.putPieceAtPosition(target, originalPiece);

        board.putPieceAtPosition(source, VOID_PIECE);
    }

    private void checkValidMove(Position source, Position target, Board board) {
        checkBasicMoveRule(source, target, board);
        checkValidDistance(source, target);
        checkIsNotSameTeam(source, target, board);
    }

    private void checkValidDistance(Position source, Position target) {
        if (Math.abs(source.computeHorizontalDistance(target)) > KING_DISTANCE_LIMIT ||
            Math.abs(source.computeVerticalDistance(target)) > KING_DISTANCE_LIMIT) {
            throw new InvalidMoveException(Piece.OVER_DISTANCE_MESSAGE);
        }
    }
}
