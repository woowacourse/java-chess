package chess.dto;

import chess.domain.game.ChessGame;
import chess.domain.game.state.Ready;
import chess.domain.game.state.Started;
import chess.domain.game.state.State;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.LinkedHashMap;
import java.util.Map;

public class ChessResponseDto {

    private final String code;
    private final String message;
    private final String state;
    private final String turn;
    private final Map<String, String> board;

    public ChessResponseDto(final ChessGame chessGame) {
        this("success", "", chessGame);
    }

    public ChessResponseDto(final String code, final String message, final ChessGame chessGame) {
        this.code = code;
        this.message = message;
        this.state = createState(chessGame.getState());
        this.turn = chessGame.getTurnName();
        this.board = createBoard(chessGame.getBoard());
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
        final Map<String, String> strings = new LinkedHashMap<>();
        for (Position position : Position.toPositions()) {
            final Piece piece = board.get(position);
            strings.put(position.getName(), piece.getTypeName() + "_" + piece.getColorName());
        }
        return strings;
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

    public Map<String, String> getBoard() {
        return board;
    }
}
