package chess.domain.state;

import chess.domain.color.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.piece.blank.Blank;
import chess.domain.position.Positions;
import java.util.Map;
import java.util.Set;

public final class GeneralChessState extends ChessState {

    public GeneralChessState(Map<Position, Piece> board) {
        super(board);
    }

    @Override
    public void move(Color turnColor, Positions positions) {
        Piece currentPiece = board.get(positions.from());
        checkTurnOf(currentPiece, turnColor);
        Piece destinationPiece = board.get(positions.to());
        Set<Position> pathToDestination = currentPiece.findPath(positions);
        validateMovable(turnColor, pathToDestination, destinationPiece);
        updateBoard(positions, currentPiece);
    }

    private void validateMovable(Color turnColor, Set<Position> pathToDestination, Piece destinationPiece) {
        if (destinationPiece.isSameColor(turnColor)) {
            throw new IllegalArgumentException("이동할 수 없는 경로 입니다.");
        }
        if (isNotAllBlankPath(pathToDestination)) {
            throw new IllegalArgumentException("이동할 수 없는 경로 입니다.");
        }
    }

    private void updateBoard(Positions positions, Piece currentPiece) {
        board.replace(positions.to(), currentPiece);
        board.replace(positions.from(), new Blank());
    }
}
