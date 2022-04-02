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

    public static String DEV_URL = "jdbc:mysql://localhost:3306/chess_dev?autoReconnect=true";

    public static Map<String, String> getMovedTestBoard() {
        Map<Position, Piece> board = BoardFactory.initialize();
        board.put(Position.valueOf("a2"), new Blank());
        board.put(Position.valueOf("a4"), new Pawn(Team.WHITE));
        return ChessDto.of(new Board(board)).getBoard();
    }
}
