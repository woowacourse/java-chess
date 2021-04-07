package chess.dto;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.dto.responsedto.ResponseDto;

public class PieceDto implements ResponseDto {
    private static final int PIECE_NAME_INDEX = 0;
    private static final int Y_POSITION_INDEX = 1;
    private static final int X_POSITION_INDEX = 0;
    private final long pieceId;
    private final boolean isBlack;
    private final String position;
    private final long gridId;
    private final String name;

    public PieceDto(long pieceId, boolean isBlack, String position, long gridId, String name) {
        this.pieceId = pieceId;
        this.isBlack = isBlack;
        this.position = position;
        this.gridId = gridId;
        this.name = name;
    }

    public long getPieceId() {
        return pieceId;
    }

    public boolean isBlack() {
        return isBlack;
    }

    public String getPosition() {
        return position;
    }

    public long getGridId() {
        return gridId;
    }

    public String getName() {
        return name;
    }

    public Piece toEntity() {
        Color color = null;
        if (isBlack()) {
            color = Color.BLACK;
        }
        if (!isBlack()) {
            color = Color.WHITE;
        }
        return PieceFactory.from(
                name.charAt(PIECE_NAME_INDEX),
                color,
                position.charAt(X_POSITION_INDEX),
                position.charAt(Y_POSITION_INDEX)
        );
    }

    @Override
    public String toString() {
        return "PieceDto{" +
                "pieceId=" + pieceId +
                ", isBlack=" + isBlack +
                ", position='" + position + '\'' +
                ", gridId=" + gridId +
                ", name='" + name + '\'' +
                '}';
    }
}
