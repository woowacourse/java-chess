package chess.repository;

import chess.entity.ChessGame;
import chess.entity.Movement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MovementRepositoryTest {

    private MovementRepository movementRepository;
    private ChessRepository chessRepository;

    @DisplayName("실제 디비 없는 경우 메모리로 테스트")
    @BeforeEach
    void setUp() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            chessRepository = new MariaChessRepository();
            movementRepository = new MariaMovementRepository();
        } catch (ClassNotFoundException e) {
            chessRepository = new InMemoryChessRepository();
            movementRepository = new InMemoryMovementRepository();
        }
    }

    @AfterEach
    void tearDown() throws SQLException {
        movementRepository.deleteAll();
        chessRepository.deleteAll();
    }

    @DisplayName("정상 저장 테스트")
    @Test
    void save() throws SQLException {
        //given
        ChessGame chessGame = new ChessGame(true);
        chessGame = chessRepository.save(chessGame);
        Movement movement = new Movement(chessGame.getId(), "a1", "a2");

        //when
        Movement saved = movementRepository.save(movement);

        //then
        assertThat(saved.getId()).isNotNull();
    }

    @Test
    void findAllByChessId() throws SQLException {
        //given
        //given
        ChessGame chessGame = new ChessGame(true);
        chessGame = chessRepository.save(chessGame);
        Movement movement1 = new Movement(chessGame.getId(), "a1", "a2");
        Movement movement2 = new Movement(chessGame.getId(), "a2", "a3");
        movementRepository.save(movement1);
        movementRepository.save(movement2);

        //when
        List<Movement> movements = movementRepository.findAllByChessId(chessGame.getId());

        //then
        assertThat(movements).hasSize(2);
    }
}