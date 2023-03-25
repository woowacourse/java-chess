package chess.dto.outputView;

import chess.domain.game.Position;
import chess.domain.piece.Piece;

import java.util.Map;

public final class PrintBoardDto {

    private final Map<Position, Piece> board;

    public PrintBoardDto(final Map<Position, Piece> board) {
        this.board = board;
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }
}
