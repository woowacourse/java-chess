package chess.domain.board.service.dto;

import chess.domain.board.position.Position;
import chess.domain.piece.Piece;

import java.util.Map;

public class BoardSearchResponse {

    private final Map<Position, Piece> chessBoard;
    private final String turn;

    public BoardSearchResponse(final Map<Position, Piece> chessBoard, final String turn) {
        this.chessBoard = chessBoard;
        this.turn = turn;
    }

    public Map<Position, Piece> chessBoard() {
        return chessBoard;
    }

    public String turn() {
        return turn;
    }
}
