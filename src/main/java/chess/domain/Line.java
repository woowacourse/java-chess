package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Line {
    private List<Piece> pieces;

    public Line(List<Piece> pieces) {
        validateDuplicatedPieces(pieces);
        this.pieces = new ArrayList<>(pieces);
    }

    private void validateDuplicatedPieces(List<Piece> pieces) {
        Set<Position> expectedPositions = pieces
                .stream()
                .map(Piece::getPosition)
                .collect(Collectors.toSet());

        if (pieces.size() != expectedPositions.size()) {
            throw new IllegalArgumentException("같은 포지션을 가진 체스 말이 존재할 수 없습니다.");
        }
    }
}
