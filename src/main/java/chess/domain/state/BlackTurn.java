package chess.domain.state;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Direction;
import chess.domain.position.Square;
import chess.domain.position.SquareDifferent;

import java.util.Map;

public class BlackTurn implements Turn {
    @Override
    public Turn checkMovable(Map<Square, Piece> board, Square source, Square destination, Piece sourcePiece, Piece destinationPiece) {
        if (sourcePiece.isWhite()) {
            throw new IllegalArgumentException("움직이려고 하는 말이 본인 진영의 말이 아닙니다.");
        }

        if (!sourcePiece.canMove(source, destination)) {
            throw new IllegalArgumentException("해당 말의 규칙으로는 도착지로 갈 수 없습니다.");
        }

        if (sourcePiece.isSameColor(destinationPiece)) {
            throw new IllegalArgumentException("목적지에 같은 편 말이 있어 이동할 수 없습니다.");
        }

        if (sourcePiece.matches(PieceType.PAWN)) {
            checkPawnCanCatch(source, destination, destinationPiece);
        }

        checkPathBlocked(board, source, destination, sourcePiece);

        return new WhiteTurn();
    }

    private void checkPawnCanCatch(Square source, Square destination, Piece destinationPiece) {
        SquareDifferent squareDifferent = source.calculateDiff(destination);
        Direction direction = Direction.findDirectionByDiff(squareDifferent);

        if (!direction.isDiagonal() && destinationPiece.isNotEmpty()) {
            throw new IllegalArgumentException("폰은 직선 경로로 상대 말을 잡을 수 없습니다.");
        }
    }

    private void checkPathBlocked(Map<Square, Piece> board, Square source, Square destination, Piece sourcePiece) {
        SquareDifferent diff = source.calculateDiff(destination);

        if (needFindPathCondition(source, sourcePiece)) {
            findPath(board, source, destination, diff);
        }
    }

    private boolean needFindPathCondition(Square source, Piece sourcePiece) {
        return !sourcePiece.matches(PieceType.KNIGHT)
                && !(sourcePiece.matches(PieceType.PAWN) && source.isPawnStartSquare())
                && !sourcePiece.matches(PieceType.KING);
    }

    private void findPath(Map<Square, Piece> board, Square source, Square destination, SquareDifferent diff) {
        Square candidate = source;
        Direction direction = Direction.findDirectionByDiff(diff);

        while (!candidate.equals(destination)) {
            checkBlocked(board, source, candidate);
            candidate = direction.nextSquare(candidate);
        }
    }

    private void checkBlocked(Map<Square, Piece> board, Square source, Square candidate) {
        if (!source.equals(candidate) && board.get(candidate).isNotEmpty()) {
            throw new IllegalArgumentException("막힌 경로입니다.");
        }
    }
}
