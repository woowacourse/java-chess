package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Position;
import chess.domain.position.type.File;
import chess.domain.position.type.Rank;
import java.sql.SQLException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionDAOTest {
    private static final String FILE_VALUE = "a";
    private static final String RANK_VALUE = "3";

    private final PositionDAO positionDAO = new PositionDAO();

    @BeforeEach
    void tearDown() throws SQLException {
        positionDAO.deleteAll();
    }

    @DisplayName("add 와 find")
    @Test
    void addAndFind() throws SQLException {
        Position position = new Position(FILE_VALUE, RANK_VALUE);
        Position addedPosition = positionDAO.add(position);

        Position positionFoundByFileRank
            = this.positionDAO.findByFileRank(File.of(FILE_VALUE), Rank.of(RANK_VALUE));
        Position positionFoundById = this.positionDAO.findById(addedPosition.getId());

        assertThat(positionFoundByFileRank).isEqualTo(position);
        assertThat(positionFoundById).isEqualTo(position);
    }

    @DisplayName("update")
    @Test
    void update() throws SQLException {
        Position initialPosition = new Position(FILE_VALUE, RANK_VALUE);
        Position addedPosition = positionDAO.add(initialPosition);

        File newFile = File.of("h");
        Rank newRank = Rank.of("8");
        addedPosition.setFile(newFile);
        addedPosition.setRank(newRank);

        Position updatedPosition = positionDAO.update(addedPosition);

        assertThat(updatedPosition.getId()).isEqualTo(addedPosition.getId());
        assertThat(updatedPosition.file()).isSameAs(newFile);
        assertThat(updatedPosition.rank()).isSameAs(newRank);
    }

    @DisplayName("delete")
    @Test
    void delete() throws SQLException {
        Position position = new Position(FILE_VALUE, RANK_VALUE);
        Position addedPosition = positionDAO.add(position);

        positionDAO.delete(addedPosition);

        Position foundPosition = positionDAO
            .findByFileRank(File.of(FILE_VALUE), Rank.of(RANK_VALUE));

        assertThat(foundPosition).isNull();
    }

    @DisplayName("deleteAll")
    @Test
    void deleteAll() throws SQLException {
        Position position1 = new Position(FILE_VALUE, RANK_VALUE);
        Position position2 = new Position("h", "8");
        positionDAO.add(position1);
        positionDAO.add(position2);

        positionDAO.deleteAll();

        Position foundPosition1 = positionDAO
            .findByFileRank(File.of(FILE_VALUE), Rank.of(RANK_VALUE));

        Position foundPosition2 = positionDAO
            .findByFileRank(File.of("h"), Rank.of("8"));

        assertThat(foundPosition1).isNull();
        assertThat(foundPosition2).isNull();
    }
}