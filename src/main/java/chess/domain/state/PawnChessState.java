package chess.domain.state;

import chess.domain.color.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.piece.blank.Blank;
import chess.domain.piece.pawn.Pawn;
import chess.domain.position.Positions;
import java.util.Map;
import java.util.Set;

public final class PawnChessState extends ChessState {

    public PawnChessState(Map<Position, Piece> board) {
        super(board);
    }

    @Override
    public void move(Color turnColor, Positions positions) {
        Pawn currentPiece = (Pawn) board.get(positions.from());
        checkTurnOf(currentPiece, turnColor);
        Piece destinationPiece = board.get(positions.to());
        validateMovable(positions, currentPiece);
        validateWithCapture(positions, currentPiece, destinationPiece);
        updateBoard(positions, currentPiece);
    }

    private void validateWithCapture(Positions positions, Pawn currentPiece, Piece destinationPiece) {
        if (!currentPiece.isCaptureMove(positions) && !destinationPiece.isBlank()) {
            throw new IllegalArgumentException("이동 할 수 없는 위치입니다.");
        }
        if (currentPiece.isCaptureMove(positions) && !currentPiece.isOppositeColor(destinationPiece)) {
            throw new IllegalArgumentException("이동 할 수 없는 위치입니다.");
        }
    }

    private void validateMovable(Positions positions, Pawn currentPiece) {
        Set<Position> pathToDestination = currentPiece.findPath(positions);
        if (isNotAllBlankPath(pathToDestination)) {
            throw new IllegalArgumentException("이동 할 수 없는 위치입니다.");
        }
    }

    private void updateBoard(Positions positions, Pawn currentPiece) {
        board.replace(positions.to(), currentPiece.update());
        board.replace(positions.from(), new Blank());
    }
}
