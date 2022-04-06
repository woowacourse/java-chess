package chess.dto;

import chess.domain.board.Point;
import chess.domain.piece.Piece;

public class PieceResponse {

    private final int horizontalIndex;
    private final int verticalIndex;
    private final String color;
    private final String type;

    public PieceResponse(int horizontalIndex, int verticalIndex, String color, String type) {
        this.horizontalIndex = horizontalIndex;
        this.verticalIndex = verticalIndex;
        this.color = color;
        this.type = type;
    }

    public static PieceResponse of(Point point, Piece piece) {
        return new PieceResponse(
            point.getHorizontal(),
            point.getVertical(),
            piece.getColor(),
            piece.getType()
        );
    }
}
