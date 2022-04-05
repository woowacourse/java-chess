package chess.web;

import chess.domain.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.dto.BoardDto;

import java.util.Map;

public class Response {

    private final BoardDto boardDto;
    private final String exceptionMessage;

    private Response(final Map<Position, Piece> board, final String exceptionMessage) {
        this.boardDto = BoardDto.from(board);
        this.exceptionMessage = exceptionMessage;
    }

    public static Response success(final Map<Position, Piece> board) {
        return new Response(board, "");
    }

    public static Response exception(final Map<Position, Piece> board, final String exceptionMessage) {
        return new Response(board, exceptionMessage);
    }

    public Map<String, String> getBoard() {
        return boardDto.getBoard();
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }
}
