package chess.util;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.Position;
import chess.domain.piece.Blank;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.dto.ChessDto;
import java.util.Map;

public class JdbcTestFixture {

    public static Map<String, String> getMovedTestBoard() {
        Map<Position, Piece> board = BoardFactory.initialize();
        board.put(Position.valueOf("a2"), new Blank());
        board.put(Position.valueOf("a4"), new Pawn(Team.WHITE));
        return ChessDto.of(new Board(board)).getBoard();
    }

    public static Map<String, String> getTestBoard() {
        Map<Position, Piece> board = BoardFactory.initialize();
        board.put(Position.valueOf("g2"), new Blank());
        board.put(Position.valueOf("h2"), new Blank());
        return ChessDto.of(new Board(board)).getBoard();
    }
}
