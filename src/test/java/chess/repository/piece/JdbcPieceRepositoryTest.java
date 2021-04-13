package chess.repository.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.location.Location;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.team.Team;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JdbcPieceRepositoryTest {

    private JdbcPieceRepository repository;

    @BeforeEach
    void setUp() throws SQLException {
        repository = new JdbcPieceRepository();
        repository.deleteAll();
    }

    @Test
    void insert() throws SQLException {
        // given
        int roomId = 0;
        Queen piece = Queen.of(Location.of(1, 1), Team.WHITE);

        // when
        long uid = repository.insert(roomId, piece);
        Piece foundPiece = repository.findPieceById(uid);

        // then
        assertThat(foundPiece).isInstanceOf(Queen.class);
        assertAll(
            () -> assertThat(foundPiece.getId()).isEqualTo(uid),
            () -> assertThat(foundPiece.getRoomId()).isEqualTo(roomId),
            () -> assertThat(foundPiece.getTeam()).isEqualTo(piece.getTeam()),
            () -> assertThat(foundPiece.getSignature()).isEqualTo(piece.getSignature()),
            () -> assertThat(foundPiece.getLocation()).isEqualTo(Location.of(1, 1))
        );
    }

    @Test
    void update() throws SQLException {
        // given
        int roomId = 0;
        Queen original = Queen.of(Location.of(1, 1), Team.WHITE);
        long pieceId = repository.insert(roomId, original);

        // when
        Queen updated = Queen.of(pieceId, roomId, Team.WHITE, Location.of(1, 8));
        repository.update(updated);
        Piece foundPiece = repository.findPieceById(pieceId);

        // then
        assertThat(foundPiece).isInstanceOf(Queen.class);
        assertThat(foundPiece.getLocation()).isEqualTo(Location.of(1, 8));
    }

    @Test
    void findPiecesByRoomId() throws SQLException {
        // given
        int roomId = 0;
        Queen piece1 = Queen.of(Location.of(1, 1), Team.WHITE);
        King piece2 = King.of(Location.of(1, 2),Team.WHITE);

        // when
        long piece1Id = repository.insert(roomId, piece1);
        long piece2Id = repository.insert(roomId, piece2);
        List<Piece> foundPieces = repository.findPiecesByRoomId(roomId);

        // then
        assertThat(foundPieces.size()).isEqualTo(2);
        assertAll(
            () -> assertThat(foundPieces.get(0).getRoomId()).isEqualTo(roomId),
            () -> assertThat(foundPieces.get(0).getId()).isEqualTo(piece1Id),
            () -> assertThat(foundPieces.get(0).getSignature()).isEqualTo(piece1.getSignature()),
            () -> assertThat(foundPieces.get(0).getTeam()).isEqualTo(piece1.getTeam()),
            () -> assertThat(foundPieces.get(0).getLocation()).isEqualTo(Location.of(1, 1)),
            () -> assertThat(foundPieces.get(1).getRoomId()).isEqualTo(roomId),
            () -> assertThat(foundPieces.get(1).getId()).isEqualTo(piece2Id),
            () -> assertThat(foundPieces.get(1).getSignature()).isEqualTo(piece2.getSignature()),
            () -> assertThat(foundPieces.get(1).getTeam()).isEqualTo(piece2.getTeam()),
            () -> assertThat(foundPieces.get(1).getLocation()).isEqualTo(Location.of(1, 2))
        );
    }

    @Test
    void count() throws SQLException {
        // given, when
        int zero = repository.count();

        repository.insert(0, Queen.of(Location.of("a1"), Team.WHITE));
        int one = repository.count();

        // then
        assertThat(zero).isEqualTo(0);
        assertThat(one).isEqualTo(1);
    }

    @Test
    void deleteAll() throws SQLException {
        assertThat(repository.count()).isEqualTo(0);
    }

    @Test
    void deletePieceById() throws SQLException {
        // given
        Queen piece = Queen.of(Location.of(1, 1), Team.WHITE);

        // when
        long id = repository.insert(0, piece);
        repository.deletePieceById(id);

        // then
        assertThatThrownBy(() -> repository.findPieceById(id))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
