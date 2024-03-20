package chess.domain.strategy;

import chess.domain.Blank;
import chess.domain.Color;
import chess.domain.Piece;
import chess.domain.Position;
import java.util.Map;
import java.util.Set;

public class GeneralMoveStrategy extends MoveStrategy {

    public GeneralMoveStrategy(Map<Position, Piece> board) {
        super(board);
    }

    @Override
    public MoveStrategy changeStrategy(Position from) {
        return null;
    }

    @Override
    public void move(Color turnColor, Position from, Position to) {
        Piece currentPiece = board.get(from);
        checkTurnOf(currentPiece, turnColor);
        Set<Position> movablePositions = currentPiece.findMovablePositions(to);
        Piece destinationPiece = board.get(to);
        if (isAllBlankCourses(movablePositions) && !destinationPiece.isSameColor(turnColor)) {
            board.replace(to, currentPiece.update(to));
            board.replace(from, new Blank(from));
            return;
        }
        throw new IllegalArgumentException("이동할 수 없는 경로 입니다.");
    }
}
