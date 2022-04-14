package chess.web.dto;

import chess.domain.board.Position;
import chess.domain.piece.Piece;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class BoardDto {
    private final Map<String, String> board;

    public BoardDto(final Map<String, String> board) {
        this.board = board;
    }

    public static BoardDto from(final Map<Position, Piece> board) {
        final Map<String, String> newBoard = convertDtoFrom(board);
        return new BoardDto(newBoard);
    }

    private static Map<String, String> convertDtoFrom(final Map<Position, Piece> board) {
        final Map<String, String> newBoard = new LinkedHashMap<>();
        for (final Entry<Position, Piece> entry : board.entrySet()) {
            final PositionDto position = PositionDto.from(entry.getKey());
            final PieceDto piece = PieceDto.from(entry.getValue());
            newBoard.put(position.getPosition(), piece.getName());
        }
        return newBoard;
    }

    public Map<String, String> getBoard() {
        return board;
    }
}
