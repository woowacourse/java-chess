package chess.dto.game;

import chess.domain.game.Position;
import chess.domain.game.Turn;
import chess.domain.piece.Piece;

import java.util.Map;

public final class ChessGameLoadDto {

    private final Map<Position, Piece> board;
    private final Turn turn;

    public ChessGameLoadDto(final Map<Position, Piece> board, final Turn turn) {
        this.board = board;
        this.turn = turn;
    }

    public static ChessGameLoadDto from(final Map<Position, Piece> board, final Turn turn) {
        return new ChessGameLoadDto(board, turn);
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }

    public Turn getTurn() {
        return turn;
    }
}
