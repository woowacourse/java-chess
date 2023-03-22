package chess.dto;

import chess.domain.board.Position;
import chess.domain.game.ChessGame;
import chess.domain.pieces.Piece;
import java.util.Collections;
import java.util.Map;

public class BoardResultDto {

    private final Map<Position, Piece> board;

    private BoardResultDto(final Map<Position, Piece> board) {
        this.board = board;
    }

    public static BoardResultDto toDto(final ChessGame chessGame) {
        return new BoardResultDto(chessGame.getBoard());
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }
}
