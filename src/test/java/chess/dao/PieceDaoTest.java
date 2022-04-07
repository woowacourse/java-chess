package chess.dao;

import static chess.domain.CachedPosition.a2;
import static chess.domain.CachedPosition.a3;
import static chess.domain.CachedPosition.b2;
import static org.assertj.core.api.Assertions.assertThat;

import chess.controller.dto.response.PieceResponse;
import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import com.mysql.cj.exceptions.AssertionFailedException;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PieceDaoTest {

    private static final long testGameId = 1;

    private final GameDao gameDao = new GameDaoImpl(new TestConnectionSetup());
    private final PieceDao pieceDao = new PieceDaoImpl(new TestConnectionSetup());

    @BeforeEach
    void createGame() {
        gameDao.save(testGameId);
    }

    @AfterEach
    void clear() {
        gameDao.delete(testGameId);
    }

    @DisplayName("기물 저장 테스트")
    @Test
    void save() {
        pieceDao.save(testGameId, a2, new Pawn(Color.WHITE));
    }

    @DisplayName("게임의 전체 기물 조회 테스트")
    @Test
    void findAll() {
        pieceDao.save(testGameId, a2, new Pawn(Color.WHITE));
        pieceDao.save(testGameId, b2, new Pawn(Color.WHITE));

        List<PieceResponse> pieces = pieceDao.findAll(testGameId);

        assertThat(pieces.size()).isEqualTo(2);
    }

    @DisplayName("위치에 맞는 기물 조회 테스트")
    @Test
    void find() {
        pieceDao.save(testGameId, a2, new Pawn(Color.WHITE));

        Optional<Piece> maybePiece = pieceDao.find(testGameId, a2);
        Piece actual = maybePiece.orElseThrow(() -> new AssertionFailedException("해당하는 기물이 없습니다."));

        assertThat(actual).isEqualTo(new Pawn(Color.WHITE));
    }

    @DisplayName("기물의 위치 변경 테스트")
    @Test
    void update() {
        pieceDao.save(testGameId, a2, new Pawn(Color.WHITE));

        pieceDao.updatePosition(testGameId, a2, a3);
        Optional<Piece> shouldEmpty = pieceDao.find(testGameId, a2);
        Piece actual = pieceDao.find(testGameId, a3).orElseThrow(() -> new AssertionFailedException("해당하는 기물이 없습니다."));

        Assertions.assertAll(
                () -> assertThat(shouldEmpty.isPresent()).isFalse(),
                () -> assertThat(actual).isEqualTo(new Pawn(Color.WHITE))
        );
    }

    @DisplayName("위치에 맞는 기물 삭제 테스트")
    @Test
    void delete() {
        pieceDao.save(testGameId, a2, new Pawn(Color.WHITE));

        pieceDao.delete(testGameId, a2);
        Optional<Piece> maybePiece = pieceDao.find(1, a2);

        assertThat(maybePiece.isPresent()).isFalse();
    }

    @DisplayName("게임의 전체 기물 삭제 테스트")
    @Test
    void deleteAll() {
        pieceDao.deleteAll(testGameId);

        List<PieceResponse> pieces = pieceDao.findAll(1);

        assertThat(pieces).isEmpty();
    }
}
