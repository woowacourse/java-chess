package chess.model;

import java.util.List;
import java.util.Objects;

public class BoardDTO {
    private List<String> pieces;

    public BoardDTO(List<String> pieces) {
        this.pieces = pieces;
    }

    public List<String> getPieces() {
        return pieces;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardDTO boardDTO = (BoardDTO) o;
        return Objects.equals(pieces, boardDTO.pieces);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieces);
    }
}
