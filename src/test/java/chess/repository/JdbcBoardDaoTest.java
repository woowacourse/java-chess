package chess.repository;

import chess.domain.ChessGame;
import chess.domain.board.BoardFactory;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;
import org.junit.jupiter.api.*;

import java.sql.SQLException;

import static chess.PositionFixture.B_2;
import static chess.PositionFixture.B_3;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class JdbcBoardDaoTest {

    private final JdbcBoardDao jdbcBoardDao = new JdbcBoardDao(new TestConnector());

    @AfterEach
    void init() {
        jdbcBoardDao.deleteAll();
    }

    @Test
    void 체스_말_삽입_테스트() {
        ChessGame chessGame = new ChessGame(BoardFactory.createBoard(), Team.WHITE);
        Assertions.assertDoesNotThrow(() -> jdbcBoardDao.saveChessGame(chessGame));
    }

    @Test
    void 체스_게임_불러오기_테스트() {

        ChessGame chessGame = new ChessGame(BoardFactory.createBoard(), Team.WHITE);
        chessGame.movePiece(B_2, B_3);
        jdbcBoardDao.saveChessGame(chessGame);

        ChessGame selectChessGame = jdbcBoardDao.selectChessGame();
        Piece piece = selectChessGame.findPiece(B_3);
        assertThat(piece.getPieceType()).isEqualTo(PieceType.PAWN);
    }
}
