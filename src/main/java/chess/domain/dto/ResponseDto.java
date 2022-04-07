package chess.domain.dto;

import chess.domain.piece.Piece;
import chess.domain.board.Position;
import chess.domain.game.ChessGame;
import chess.domain.state.Ready;
import chess.domain.state.Running;
import chess.domain.state.State;
import java.util.LinkedHashMap;
import java.util.Map;

public class ResponseDto {

    private final String code;
    private final String message;
    private final String state;
    private final String turn;
    private final Map<String, String> board;

    public ResponseDto(String turn, ChessGame chessGame) {
        this("success", "", turn, chessGame);
    }

    private ResponseDto(String code, String message, String turn, ChessGame chessGame) {
        this.code = code;
        this.message = message;
        this.state = createState(chessGame.getState());
        this.turn = turn;
        this.board = createBoard(chessGame.board().getBoard());
    }

    public static ResponseDto createResponseDto(String turn, ChessGame chessGame) {
        return new ResponseDto(turn, chessGame);
    }

    public static ResponseDto createErrorResponseDto(String message, ChessGame chessGame) {
        return new ResponseDto("error", message, "", chessGame);
    }

    private String createState(State state) {
        if (state instanceof Ready) {
            return "ready";
        }
        if (state instanceof Running) {
            return "running";
        }
        return "finished";
    }

    private Map<String, String> createBoard(final Map<Position, Piece> board) {
        final Map<String, String> strings = new LinkedHashMap<>();
        for (Position position : Position.getPositions()) {
            final Piece piece = board.get(position);
            strings.put(position.getName(), piece.getPieceType().getName() + "_" + piece.getPieceColor().getName());
        }
        return strings;
    }

    public String getCode() {
        return code;
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

    @Override
    public String toString() {
        return "ResponseDto{" +
                "code='" + code + '\'' +
                ", state='" + state + '\'' +
                ", turn='" + turn + '\'' +
                ", board=" + board +
                '}';
    }
}
