package chess.web.dto;

import java.util.Map;

import chess.domain.game.state.position.Position;
import chess.domain.piece.Piece;

public class BoardDto {
    private Map<Position, Piece> board;

    private BoardDto(Map<Position, Piece> board) {
        this.board = board;
    }

    public static BoardDto of(Map<Position, Piece> board) {
        return new BoardDto(board);
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }

    public void setBoard(Map<Position, Piece> board) {
        this.board = board;
    }

    public Piece get(Position position) {
        return board.get(position);
    }
}
