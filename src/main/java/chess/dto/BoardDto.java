package chess.dto;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class BoardDto {

    private final Map<String, String> board;

    private BoardDto(final Map<String, String> board) {
        this.board = board;
    }

    public static BoardDto from(final Map<Position, Piece> board) {
        Map<String, String> boardMap = new HashMap<>();
        for (Entry<Position, Piece> entry : board.entrySet()) {
            boardMap.put(entry.getKey().getPosition(), PieceSymbol.getSymbol(entry.getValue()));
        }
        return new BoardDto(boardMap);
    }

    public String getSymbol(final String position) {
        return board.getOrDefault(position, PieceSymbol.NONE_PIECE_SYMBOL);
    }

    public Map<String, String> getBoard() {
        return Collections.unmodifiableMap(board);
    }
}
