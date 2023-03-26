package chess.domain.dao;

import chess.TestPiecesFactory;
import chess.domain.Color;
import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;
import chess.domain.chessgame.ChessGame;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.List;

import static chess.domain.Color.WHITE;
import static chess.domain.File.A;
import static chess.domain.Rank.FOUR;
import static chess.domain.Rank.TWO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class MysqlChessGameDaoTest {
    private final MysqlChessGameDao mysqlChessGameDao = new MysqlChessGameDao();

    @BeforeEach
    void setUp() {
        mysqlChessGameDao.deleteAll();
    }

    @Test
    void 데이터베이스를_연결한다() {
        //given
        try (final var connection = mysqlChessGameDao.getConnection()) {

            //when
            //then
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void 체스게임을_저장한다() {
        //given
        final ChessGame chessGame = ChessGame.from(new TestPiecesFactory(List.of(
                new Pawn(File.A, Rank.TWO, Color.WHITE)
        )).generate());

        //when
        mysqlChessGameDao.save(chessGame);

        //then
        final ChessGame selectedChessGame = mysqlChessGameDao.select();
        final List<Piece> pieces = selectedChessGame.getPieces();
        assertSoftly(softly -> {
            softly.assertThat(pieces.size()).isEqualTo(1);
            softly.assertThat(pieces.get(0)).extracting(Piece::getPosition, Piece::getColor, Object::getClass)
                    .contains(new Position(A, TWO), WHITE, Pawn.class);
        });
    }

    @Test
    void 체스게임을_업데이트한다() {
        //given
        ChessGame chessGame = ChessGame.from(new TestPiecesFactory(List.of(
                new Pawn(File.A, Rank.TWO, Color.WHITE)
        )).generate());
        mysqlChessGameDao.save(chessGame);
        chessGame = chessGame.start();
        chessGame.move(new Position(A, TWO), new Position(A, FOUR));

        //when
        mysqlChessGameDao.update(chessGame);

        //then
        final ChessGame selectedChessGame = mysqlChessGameDao.select();
        final List<Piece> pieces = selectedChessGame.getPieces();
        assertSoftly(softly -> {
            softly.assertThat(pieces.size()).isEqualTo(1);
            softly.assertThat(pieces.get(0)).extracting(Piece::getPosition, Piece::getColor, Object::getClass)
                    .contains(new Position(A, FOUR), WHITE, Pawn.class);
        });
    }

    @Test
    void 체스게임을_지운다() {
        //given
        final ChessGame chessGame = ChessGame.from(new TestPiecesFactory(List.of(
                new Pawn(File.A, Rank.TWO, Color.WHITE)
        )).generate());
        mysqlChessGameDao.save(chessGame);

        //when
        mysqlChessGameDao.deleteAll();

        //then
        final ChessGame selectedChessGame = mysqlChessGameDao.select();
        final List<Piece> pieces = selectedChessGame.getPieces();
        assertThat(pieces).isEmpty();
    }

    @AfterEach
    void tearDown() {
        mysqlChessGameDao.deleteAll();
    }
}