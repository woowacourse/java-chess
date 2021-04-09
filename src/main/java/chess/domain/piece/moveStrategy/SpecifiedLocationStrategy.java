package chess.domain.piece.moveStrategy;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;

import java.util.Objects;

public class SpecifiedLocationStrategy extends MoveStrategy {
    @Override
    public boolean canMove(Position target, Position destination, Board board) {
        Piece destinationPiece = board.findPieceFromPosition(destination);
        Piece targetPiece = board.findPieceFromPosition(target);

        if (Objects.isNull(destinationPiece)) {
            return true;
        }
        return targetPiece.isDifferentTeam(destinationPiece);
    }
}
