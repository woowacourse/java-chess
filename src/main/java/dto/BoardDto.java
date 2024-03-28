package dto;

import domain.game.Piece;
import domain.game.PieceType;
import domain.position.Position;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public record BoardDto(Map<Position, PieceType> piecePositions) {
    public static BoardDto from(final Map<Position, Piece> positionsOfPieces) {
        Map<Position, PieceType> piecePositions = positionsOfPieces.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().getPieceType()));

        return new BoardDto(Collections.unmodifiableMap(piecePositions));
    }
}
