package chess.dao;

import chess.db.DBConnector;
import chess.domain.piece.PieceColor;
import chess.dto.GameDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertAll;

public class GameDaoImplTest {

    private GameDao gameDao;

    @BeforeEach
    void setUp() {
        gameDao = new GameDaoImpl(new DBConnector());
        gameDao.removeAll();
    }

    @AfterEach
    void clearData() {
        gameDao.removeAll();
    }

    @Test
    @DisplayName("삭제")
    void removeAll() {
        assertAll(
                () -> assertThatCode(() -> gameDao.removeAll()).doesNotThrowAnyException(),
                () -> assertThat(gameDao.find().getTurn()).isNull(),
                () -> assertThat(gameDao.find().getStatus()).isEqualTo("ready")
        );
    }

    @Test
    @DisplayName("저장")
    void save() {
        GameDto gameDto = GameDto.from(PieceColor.WHITE, true);
        assertAll(
                () -> assertThatCode(() -> gameDao.save(gameDto)).doesNotThrowAnyException(),
                () -> assertThat(gameDao.find().getTurn()).isEqualTo("white"),
                () -> assertThat(gameDao.find().getStatus()).isEqualTo("playing")
        );
    }

    @Test
    @DisplayName("업데이트")
    void update() {
        GameDto gameDto = GameDto.from(PieceColor.WHITE, true);
        gameDao.save(gameDto);
        GameDto updatedGameDto = GameDto.from(PieceColor.BLACK, false);

        assertAll(
                () -> assertThatCode(() -> gameDao.update(updatedGameDto)).doesNotThrowAnyException(),
                () -> assertThat(gameDao.find().getTurn()).isEqualTo("black"),
                () -> assertThat(gameDao.find().getStatus()).isEqualTo("finished")
        );
    }

    @Test
    @DisplayName("검색")
    void find() {
        GameDto gameDto = GameDto.from(PieceColor.WHITE, true);
        gameDao.save(gameDto);

        assertAll(
                () -> assertThatCode(() -> gameDao.find()).doesNotThrowAnyException(),
                () -> assertThat(gameDao.find().getTurn()).isEqualTo("white"),
                () -> assertThat(gameDao.find().getStatus()).isEqualTo("playing")
        );
    }
}
