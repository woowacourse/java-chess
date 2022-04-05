package chess.web.dto;

import java.util.Objects;

public class PieceDto {

    private final String id;
    private final PieceType pieceType;

    public PieceDto(String id, PieceType pieceType) {
        this.id = id;
        this.pieceType = pieceType;
    }

    public String getId() {
        return id;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PieceDto pieceDto = (PieceDto) o;
        return Objects.equals(id, pieceDto.id) && pieceType == pieceDto.pieceType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pieceType);
    }
}
