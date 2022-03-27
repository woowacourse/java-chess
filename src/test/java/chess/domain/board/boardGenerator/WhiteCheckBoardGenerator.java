package chess.domain.board.boardGenerator;

import chess.domain.board.BoardGenerator;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import java.util.HashMap;
import java.util.Map;

public class WhiteCheckBoardGenerator implements BoardGenerator {

    private final Map<Position, Piece> board = new HashMap<>();

    @Override
    public Map<Position, Piece> create() {
        board.put(new Position(Column.D, Row.EIGHT), new King(Team.WHITE));
        board.put(new Position(Column.B, Row.SIX), new Bishop(Team.BLACK));
        return Map.copyOf(board);
    }
}