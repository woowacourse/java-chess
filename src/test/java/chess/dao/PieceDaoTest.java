package chess.dao;

import chess.domain.PieceFactory;
import chess.domain.Point;
import chess.domain.Team;
import chess.domain.pieces.Rook;
import chess.dto.PieceDto;
import chess.utils.DbUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class PieceDaoTest {
    private DataSource dataSource = DbUtil.getDataSource("chess_test");
    private PieceDao pieceDao;

    @BeforeEach
    void setUp() {
        pieceDao = PieceDao.getInstance(dataSource);
    }

    @Test
    void create() throws SQLException {
        int gameId = 1;
        PieceDto pieceDto = new PieceDto(0, 0, "ROOK", true);
        pieceDao.add(gameId, pieceDto);
    }

    @Test
    void read() throws SQLException {
        int gameId = 1;
        List<PieceDto> pieceDtos = pieceDao.findPieceById(gameId);
        assertThat(pieceDtos.get(0)).isEqualTo(new PieceDto(3, 3, "ROOK", true));
    }

    @Test
    void update() throws SQLException {
        int gameId = 1;
        Point start = new Point(0, 0);
        Point end = new Point(3, 3);
        pieceDao.updatePosition(gameId, start, end);
        PieceDto pieceDto = pieceDao.findPieceById(gameId).stream()
                .filter(dto -> new Point(dto.getX(), dto.getY()).equals(end))
                .collect(Collectors.toList())
                .get(0);
        assertThat(PieceFactory.of(pieceDto.getName(), pieceDto.isTeam() ? Team.WHITE : Team.BLACK)).isEqualTo(new Rook(Team.WHITE));
        assertThat(new Point(pieceDto.getX(), pieceDto.getY())).isEqualTo(end);
    }

    @Test
    void delete() throws SQLException {
        int gameId = 1;
        Point target = new Point(3, 3);
        pieceDao.insertBlank(gameId, target);
    }
}
