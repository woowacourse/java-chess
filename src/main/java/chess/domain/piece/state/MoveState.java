package chess.domain.piece.state;

import chess.domain.piece.ColorCompareResult;
import java.util.List;

public interface MoveState {

    boolean canMove(int fileDifference, int rankDifference, ColorCompareResult colorCompareResult);

    default MoveState getNextState() {
        return this;
    }

    double getScore(List<MoveState> sameFileColorPiecesState);
}
