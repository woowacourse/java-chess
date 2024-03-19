package dto;

import view.PieceShape;

public record PieceInfo(
        PieceShape shape,
        boolean isWhite,
        PositionInfo position
) {
}
