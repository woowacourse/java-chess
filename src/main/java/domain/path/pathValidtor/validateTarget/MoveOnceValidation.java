package domain.path.pathValidtor.validateTarget;

import domain.board.piece.Piece;
import domain.path.Path;
import java.util.List;

public final class MoveOnceValidation implements ValidateTarget {

    @Override
    public void validate(final Path path) {
        final List<Piece> piecesInPath = path.getPiecesInPath();
        final int moveCount = piecesInPath.size() - 1;
        if (moveCount == 1) {
            return;
        }
        throw new IllegalArgumentException("한번만 이동할 수 있습니다.");
    }
}
