package chess.repository;

import chess.entity.ChessGame;
import chess.piece.Team;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;

class MariaChessRepositoryTest {

    private ChessRepository chessRepository;

    @DisplayName("실제 디비 없는 경우 메모리로 테스트")
    @BeforeEach
    void setUp() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            chessRepository = new MariaChessRepository();
        } catch (ClassNotFoundException e) {
            chessRepository = new InMemoryChessRepository();
        }
    }

    @AfterEach
    void tearDown() throws SQLException {
        chessRepository.deleteAll();
    }

    @DisplayName("정상 저장 테스트")
    @Test
    void save() throws SQLException {
        //given
        ChessGame chessGame = new ChessGame(true);

        //when
        chessGame = chessRepository.save(chessGame);

        //then
        assertThat(chessGame.getId()).isNotNull();
    }

    @DisplayName("아이디로 하나의 엔티티 찾아오기")
    @Test
    void findById() throws SQLException {
        //given
        ChessGame chessGame = chessRepository.save(new ChessGame(true));

        //when
        ChessGame actual = chessRepository.findById(chessGame.getId())
                .orElseThrow(NoSuchElementException::new);

        //then
        assertThat(chessGame.getId()).isEqualTo(actual.getId());
    }

    @Test
    void update() throws SQLException {
        //given
        ChessGame chessGame = chessRepository.save(new ChessGame(true));

        //when
        chessGame.endGame(Team.WHITE);
        chessRepository.update(chessGame);

        //then
        ChessGame updated = chessRepository.findById(chessGame.getId()).orElseThrow(NoSuchElementException::new);
        assertThat(updated.getWinner()).isEqualTo(Team.WHITE);
    }

    @Test
    void findAll() throws SQLException {
        //given
        chessRepository.save(new ChessGame(true));
        chessRepository.save(new ChessGame(true));

        //when
        List<ChessGame> all = chessRepository.findAll();

        //then
        assertThat(all).hasSize(2);
    }

    @Test
    void findAllByActive() throws SQLException {
        //given
        chessRepository.save(new ChessGame(true));
        chessRepository.save(new ChessGame(false));

        //when
        List<ChessGame> actual = chessRepository.findAllByActive();

        //then
        assertThat(actual).hasSize(1);
    }

}