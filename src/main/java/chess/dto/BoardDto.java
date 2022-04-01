package chess.dto;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class BoardDto {

    private String gameOver;
    private Map<String, String> board;

    private BoardDto(String gameOver, Map<String, String> board) {
        this.gameOver = gameOver;
        this.board = board;
    }

    public static BoardDto of(final Board board) {
        Map<String, String> map = new HashMap<>();
        for (final Entry<Position, Piece> boardEntry : board.getBoard().entrySet()) {
            map.put(boardEntry.getKey().convertPositionToString(),
                    boardEntry.getValue().convertPieceToString());
        }
        return new BoardDto("false", map);
    }
}
