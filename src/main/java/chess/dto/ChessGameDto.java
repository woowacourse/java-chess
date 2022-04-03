package chess.dto;

import chess.domain.game.ChessGame;
import chess.domain.game.state.Ready;
import chess.domain.game.state.Started;
import chess.domain.game.state.State;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;

public class ChessGameDto {

    private final String code;
    private final String message;
    private final Map<String, String> board;
    private final String state;
    private final String turn;

    public ChessGameDto(final ChessGame chessGame) {
        this("success", "", chessGame);
    }

    public ChessGameDto(final String code, final String message, final ChessGame chessGame) {
        this.code = code;
        this.message = message;
        this.board = createBoard(chessGame.getBoard());
        this.state = createState(chessGame.getState());
        this.turn = chessGame.getTurnName();
    }

    private String createState(final State state) {
        if (state instanceof Ready) {
            return "Ready";
        }
        if (state instanceof Started) {
            return "Started";
        }
        return "Ended";
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

    public String getState() {
        return state;
    }

    public String getTurn() {
        return turn;
    }
}
