package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.game.Game;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

class GameDaoTest {

    private final GameDao gameDao = new GameDao();
    private Integer gameId;

    @AfterEach
    void tearDown() {
        if (gameId != null) {
            gameDao.end(gameId);
        }
    }

    @DisplayName("연결")
    @Test
    void connect() {
        assertThatNoException().isThrownBy(gameDao::connect);
    }

    @DisplayName("게임 저장하기")
    @Test
    void saveGame() {
        Game game = new Game();

        gameId = gameDao.save(game);

        assertThat(isSame(gameDao.findBy(gameId), game)).isTrue();
    }

    @DisplayName("게임을 업데이트한다")
    @Test
    void updateGame() {
        Game game = new Game();
        gameId = gameDao.save(game);
        game.movePiece(new Position(File.A, Rank.TWO), new Position(File.A, Rank.THREE));
        gameDao.put(gameId, game);

        assertThat(isSame(gameDao.findBy(gameId), game)).isTrue();
    }

    @DisplayName("게임을 불러온다")
    @Test
    void findGame() {
        Game game = new Game();
        gameId = gameDao.save(game);
        game.movePiece(new Position(File.A, Rank.TWO), new Position(File.A, Rank.THREE));
        gameDao.put(gameId, game);

        assertThat(isSame(gameDao.findBy(gameId), game)).isTrue();
    }

    @DisplayName("안끝난 게임이 있는지 알 수 있다")
    @Test
    void test_() {
        Game game = new Game();
        gameId = gameDao.save(game);

        assertThat(gameDao.hasUnfinished()).isTrue();
    }

    @DisplayName("게임을 끝낸다")
    @Test
    void endGame() {
        Game game = new Game();
        gameId = gameDao.save(game);

        gameDao.end(gameId);
        assertThat(gameDao.hasUnfinished()).isFalse();
    }

    @DisplayName("가장 최근에 안끝난 게임 id를 찾는다")
    @Test
    void findIdOfLastUnfinished() {
        Game game = new Game();
        gameId = gameDao.save(game);

        assertThat(gameDao.findIdOfLastUnfinished()).isEqualTo(gameId);
    }

    private boolean isSame(Game game, Game other) {
        if (game.isFinished() != other.isFinished()) {
            return false;
        }
        return isSame(game.getPieces(), other.getPieces());
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