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

    public static BoardDto of(final boolean isOn, final Board board) {
        Map<String, String> map = new HashMap<>();
        for (final Entry<Position, Piece> boardEntry : board.getBoard().entrySet()) {
            map.put(boardEntry.getKey().convertPositionToString(),
                    boardEntry.getValue().convertPieceToString());
        }

        return new BoardDto(extractGameOverStatus(isOn), map);
    }

    public static BoardDto of(final Board board) {
        return BoardDto.of(false, board);
    }

    private static String extractGameOverStatus(final boolean isOn) {
        if (isOn) {
            return "false";
        }
        return "true";
    }
}
