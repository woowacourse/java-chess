package techcourse.fp.chess.dao;


import static techcourse.fp.chess.domain.PieceFixtures.BLACK_KING;
import static techcourse.fp.chess.domain.PieceFixtures.WHITE_KING;
import static techcourse.fp.chess.domain.PositionFixtures.A1;
import static techcourse.fp.chess.domain.PositionFixtures.A2;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import techcourse.fp.chess.domain.Board;
import techcourse.fp.chess.domain.BoardFactory;
import techcourse.fp.chess.domain.ChessGame;
import techcourse.fp.chess.domain.File;
import techcourse.fp.chess.domain.Position;
import techcourse.fp.chess.domain.Rank;
import techcourse.fp.chess.domain.piece.Color;
import techcourse.fp.chess.domain.piece.Empty;
import techcourse.fp.chess.domain.piece.Piece;
import techcourse.fp.chess.dto.request.ChessGameRequest;

class LocalMysqlChessGameDaoTest {

    private LocalMysqlChessGameDao dao = new LocalMysqlChessGameDao();

    @Test
    void test1() {
        final Map<Position, Piece> emptyBoard = createEmptyBoard();
        emptyBoard.put(A1, BLACK_KING);
        emptyBoard.put(A2, WHITE_KING);

        final ChessGameRequest request = new ChessGameRequest(emptyBoard, "임시 게임", Color.BLACK.name());

        dao.save(request);
    }


    @Test
    void test2() {
        final Board board = BoardFactory.generate();
        final ChessGameRequest request = new ChessGameRequest(board.getBoard(), "임시 게임", Color.WHITE.name());

        final ChessGame chessGame = dao.findById(4);

        chessGame.move(Position.createByName("A", "two"), Position.createByName("A", "three"));
    }

    private Map<Position, Piece> createEmptyBoard() {
        final Map<Position, Piece> board = new HashMap<>();
        for (File file : File.values()) {
            for (Rank rank : Rank.values()) {
                board.put(Position.of(file, rank), Empty.create());
            }
        }

        return board;
    }
}
