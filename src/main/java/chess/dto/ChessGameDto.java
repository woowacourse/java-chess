package chess.dto;

import chess.domain.game.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;

public class ChessGameDto {

    private final String code;
    private final String message;
    private final Map<String, String> board;

    public ChessGameDto(final ChessGame chessGame) {
        this("success", "", chessGame);
    }

    public ChessGameDto(final String code, final String message, final ChessGame chessGame) {
        this.code = code;
        this.message = message;
        this.board = createBoard(chessGame.getBoard());
    }

    private Map<String, String> createBoard(final Map<Position, Piece> board) {
        final Map<String, String> strings = new HashMap<>();
        board.forEach((key, value) -> {
            strings.put(key.getName(), value.getType() + "_" + value.getColor());
        });
        return strings;
    }

    public Map<String, String> getBoard() {
        return board;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
