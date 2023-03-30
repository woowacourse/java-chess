package chess.dto.outputView;

import java.util.List;

public final class PrintBoardDto {

    private final List<String> pieces;

    public PrintBoardDto(final List<String> pieces) {
        this.pieces = pieces;
    }

    public List<String> getPieces() {
        return pieces;
    }
}
