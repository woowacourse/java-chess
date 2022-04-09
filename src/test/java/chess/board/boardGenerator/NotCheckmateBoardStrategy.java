package chess.board.boardGenerator;

import chess.domain.board.strategy.BoardGenerationStrategy;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import java.util.HashMap;
import java.util.Map;

public class NotCheckmateBoardStrategy implements BoardGenerationStrategy {

    private final Map<Position, Piece> board = new HashMap<>();

    @Override
    public Map<Position, Piece> create() {
        board.put(new Position(Column.A, Row.ONE), new King(Team.BLACK));
        board.put(new Position(Column.D, Row.EIGHT), new King(Team.WHITE));
        board.put(new Position(Column.D, Row.SIX), new Rook(Team.BLACK));
        board.put(new Position(Column.E, Row.SIX), new Queen(Team.BLACK));
        return Map.copyOf(board);
    }
}