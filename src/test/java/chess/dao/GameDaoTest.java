package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.game.Game;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

class GameDaoTest {

    private static final String SERVER = "jdbc:mysql://localhost:13306/chess?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static GameDao gameDao;
    private Game game;

    @BeforeAll
    static void beforeAll() {
        gameDao = new DBGameDao(new JDBCConnection(SERVER, USERNAME, PASSWORD));
    }

    @BeforeEach
    void setUp() {
        this.game = new Game(gameDao);
    }

    @AfterEach
    void tearDown() {
        game.finish();
    }

    @DisplayName("게임 저장하기")
    @Test
    void saveGame() {
        assertThat(isSame(gameDao.findPiecesBy(game.getId()), game.getPieces())).isTrue();
        assertThat(gameDao.findTurnBy(game.getId())).isEqualTo(game.getTurn());
    }

    @DisplayName("게임을 업데이트한다")
    @Test
    void updateGame() {
        game.movePiece(new Position(File.A, Rank.TWO), new Position(File.A, Rank.THREE));

        assertThat(isSame(gameDao.findPiecesBy(game.getId()), game.getPieces())).isTrue();
        assertThat(gameDao.findTurnBy(game.getId())).isEqualTo(game.getTurn());
    }

    @DisplayName("게임을 불러온다")
    @Test
    void findGame() {
        game.movePiece(new Position(File.A, Rank.TWO), new Position(File.A, Rank.THREE));

        assertThat(isSame(new Game(gameDao).getPieces(), game.getPieces())).isTrue();
        assertThat(gameDao.findTurnBy(game.getId())).isEqualTo(game.getTurn());
    }

    @DisplayName("안끝난 게임이 있는지 알 수 있다")
    @Test
    void hasUnfinished() {
        assertThat(gameDao.hasUnfinished()).isTrue();
    }

    @DisplayName("게임을 끝낸다")
    @Test
    void endGame() {
        Game game = new Game(gameDao);

        game.finish();
        assertThat(gameDao.hasUnfinished()).isFalse();
    }

    @DisplayName("가장 최근에 안끝난 게임 id를 찾는다")
    @Test
    void findIdOfLastUnfinished() {
        Game game = new Game(gameDao);

        assertThat(gameDao.findIdOfLastUnfinished()).isEqualTo(game.getId());
    }

    private boolean isSame(Map<Position, Piece> pieces, Map<Position, Piece> other) {
        var iterator = pieces.entrySet().iterator();
        var otherIterator = other.entrySet().iterator();
        if (pieces.size() != other.size()) {
            return false;
        }
        while (iterator.hasNext()) {
            var entry = iterator.next();
            var otherEntry = otherIterator.next();
            if (!isSame(entry, otherEntry)) {
                return false;
            }
        }
        return true;
    }

    private boolean isSame(Map.Entry<Position, Piece> entry, Map.Entry<Position, Piece> other) {
        Position position = entry.getKey();
        Position otherPosition = other.getKey();
        if (!position.equals(otherPosition)) {
            return false;
        }
        Piece piece = entry.getValue();
        Piece otherPiece = other.getValue();
        if (!piece.getType().equals(otherPiece.getType())) {
            return false;
        }
        return piece.isSameTeamWith(otherPiece);
    }
}