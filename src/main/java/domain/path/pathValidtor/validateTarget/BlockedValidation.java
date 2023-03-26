package domain.path.pathValidtor.validateTarget;

import domain.board.piece.Piece;
import domain.path.Path;
import java.util.List;

public final class BlockedValidation implements ValidateTarget {

    @Override
    public void validate(final Path path) {
        final List<Piece> piecesInPath = path.getPiecesInPath();
        final List<Piece> piecesExceptStartAndEnd = piecesInPath.subList(1, piecesInPath.size() - 1);
        if (piecesExceptStartAndEnd.stream().allMatch(Piece::isEmpty)) {
            return;
        }
        throw new IllegalArgumentException("이동 경로에 기물이 존재합니다.");
    }
}
