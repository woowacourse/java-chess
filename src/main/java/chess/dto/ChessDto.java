package chess.dto;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ChessDto {

    private String gameOver;
    private Map<String, String> board;
    private String turn;

    private ChessDto(String gameOver, Map<String, String> board, String turn) {
        this.gameOver = gameOver;
        this.board = board;
        this.turn = turn;
    }

    public static ChessDto of(final boolean isOn, final Board board, final String turn) {
        Map<String, String> map = new HashMap<>();
        for (final Entry<Position, Piece> boardEntry : board.getBoard().entrySet()) {
            map.put(boardEntry.getKey().convertPositionToString(),
                    boardEntry.getValue().convertPieceToString());
        }

        return new ChessDto(extractGameOverStatus(isOn), map, turn);
    }

    public static ChessDto of(final Board board, final String turn) {
        return of(true, board, turn);
    }

    public static ChessDto of(final Board board) {
        return of(false, board, "white");
    }

    private static String extractGameOverStatus(final boolean isOn) {
        if (isOn) {
            return "false";
        }
        return "true";
    }

    public Map<String, String> getBoard() {
        return board;
    }
}
