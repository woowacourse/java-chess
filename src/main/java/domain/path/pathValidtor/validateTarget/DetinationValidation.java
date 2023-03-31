package domain.path.pathValidtor.validateTarget;

import domain.board.piece.Piece;
import domain.path.Path;
import java.util.List;

public final class DetinationValidation implements ValidateTarget {

    @Override
    public void validate(final Path path) {
        final List<Piece> piecesInPath = path.getPiecesInPath();
        final Piece startPiece = piecesInPath.get(0);
        final Piece destinationPiece = piecesInPath.get(piecesInPath.size() - 1);
        if (startPiece.getCamp() != destinationPiece.getCamp()) {
            return;
        }
        throw new IllegalArgumentException("도착지에 같은 진영의 기물이 존재합니다.");
    }
}
