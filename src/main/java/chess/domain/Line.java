package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.Position;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Line {
    private List<Piece> pieces;

    public Line(List<Piece> pieces) {
        validateDuplicatedPieces(pieces);
        this.pieces = new ArrayList<>(pieces);
    }

    private void validateDuplicatedPieces(List<Piece> pieces) {
        Set<Position> expectedPositions = new HashSet<>();
        for (Piece piece : pieces) {
            expectedPositions.add(piece.getPosition());
        }
        if (pieces.size() != expectedPositions.size()) {
            throw new IllegalArgumentException("같은 포지션을 가진 체스 말이 존재할 수 없습니다.");
        }
    }
}
