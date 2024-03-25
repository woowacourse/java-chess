package chess.dto;

import chess.domain.Position;
import chess.domain.Status;
import chess.domain.piece.Piece;
import chess.view.CharacterViewer;
import java.util.Map;

public class BoardStatusDto {
    private final Map<Position, Piece> board;
    private final Status status;

    public BoardStatusDto(Map<Position, Piece> board, Status status) {
        this.board = board;
        this.status = status;
    }

    public String getPieceValue(int row, int column) {
        if (board.containsKey(Position.of(row, column))) {
            return CharacterViewer.convertToString(board.get(Position.of(row, column)).character());
        }
        return ".";
    }

    public Status status() {
        return status;
    }
}
