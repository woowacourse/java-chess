package chess.dto;

import chess.domain.position.Position;

public record MoveOrder(
        Position piecePosition,
        Position newPosition,
        OrderOption orderOption
) {
}
