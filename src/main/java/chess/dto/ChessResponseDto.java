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

    private final String status;
    private final String state;
    private final String turn;
    private final Map<String, String> board;

    public ChessResponseDto(final ChessGame chessGame) {
        this.status = "success";
        this.state = createState(chessGame.getState());
        this.turn = chessGame.getTurn().getName();
        this.board = createBoard(chessGame.getBoard().getBoard());
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
            strings.put(position.getName(), piece.getType().getName() + "_" + piece.getColor().getName());
        }
        return strings;
    }
}
