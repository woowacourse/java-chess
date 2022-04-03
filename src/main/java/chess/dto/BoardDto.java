package chess.dto;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class BoardDto {

    private final Map<Position, String> board;

    private BoardDto(final Map<Position, String> board) {
        this.board = board;
    }

    public static BoardDto from(final Map<Position, Piece> board) {
        Map<Position, String> boardMap = new HashMap<>();
        for (Entry<Position, Piece> entry : board.entrySet()) {
            boardMap.put(entry.getKey(), PieceSymbol.getSymbol(entry.getValue()));
        }
        return new BoardDto(boardMap);
    }

    public String getSymbol(final Position position) {
        return board.getOrDefault(position, PieceSymbol.NONE_PIECE_SYMBOL);
    }
}
