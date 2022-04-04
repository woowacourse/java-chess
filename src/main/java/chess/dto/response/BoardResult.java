package chess.dto.response;

import chess.game.Position;
import chess.piece.Piece;
import java.util.Map;
import java.util.stream.Collectors;

public class BoardResult {

    private final Long boardId;
    private final Map<String, PieceResult> value;

    public BoardResult(final Long boardId, final Map<Position, Piece> board) {
        this.boardId = boardId;
        this.value = board.entrySet()
                .stream()
                .collect(Collectors.toMap(m -> position(m.getKey()), m -> piece(m.getValue())));
    }

    private String position(final Position position) {
        final String column = position.getColumn().name().toLowerCase();
        final int row = position.getRow().getValue();
        return column + row;
    }

    private PieceResult piece(final Piece piece) {
        return new PieceResult(piece.getName().name(), piece.getColor().name());
    }

    public Long getBoardId() {
        return boardId;
    }

    public Map<String, PieceResult> getValue() {
        return value;
    }
}
