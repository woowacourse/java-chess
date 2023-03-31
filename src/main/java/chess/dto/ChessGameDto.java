package chess.dto;

import chess.domain.ChessGame;
import chess.domain.Turn;
import chess.domain.piece.Piece;
import chess.domain.square.Square;

import java.util.HashMap;
import java.util.Map;

public class ChessGameDto {

    private final Map<Square, Piece> board;
    private final Turn turn;

    private ChessGameDto(final Map<Square, Piece> board, final Turn turn) {
        this.board = board;
        this.turn = turn;
    }

    public static ChessGameDto of(final ChessGame chessGame) {
        final Map<Square, Piece> board = chessGame.getBoard().getBoard();
        final Turn turn = chessGame.getTurn();
        return new ChessGameDto(board, turn);
    }

    public Map<Square, Piece> getBoard() {
        return new HashMap<Square, Piece>(board);
    }

    public String getTurn() {
        return turn.getValue();
    }
}
