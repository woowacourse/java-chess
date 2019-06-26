package chess.dao;

import chess.domain.Point;
import chess.domain.Team;
import chess.domain.pieces.Piece;
import chess.domain.pieces.Rook;
import chess.dto.PieceDto;
import chess.utils.DBUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class PieceDaoTest {
    private DataSource dataSource = DBUtil.getDataSource();
    private PieceDao pieceDao;

    @BeforeEach
    void setUp() {
        pieceDao = PieceDao.getInstance(dataSource);
    }

    @Test
    void create() throws SQLException {
        int gameId = 1;
        Point point = new Point("a1");
        Piece rook = new Rook(Team.WHITE);
        PieceDto pieceDto = new PieceDto(point, rook);
        pieceDao.add(gameId, pieceDto);
    }

    @Test
    void read() throws SQLException {
        int gameId = 1;
        List<PieceDto> pieceDtos = pieceDao.findPieceById(gameId);
        assertThat(pieceDtos.get(0)).isEqualTo(new PieceDto(new Point(3, 3), new Rook(Team.WHITE)));
    }

    @Test
    void update() throws SQLException {
        int gameId = 1;
        Point start = new Point(0, 0);
        Point end = new Point(3, 3);
        pieceDao.updatePosition(gameId, start, end);
        PieceDto pieceDto = pieceDao.findPieceById(gameId).stream()
                .filter(vo -> vo.getPoint().equals(end))
                .collect(Collectors.toList())
                .get(0);
        assertThat(pieceDto.getPiece()).isEqualTo(new Rook(Team.WHITE));
        assertThat(pieceDto.getPoint()).isEqualTo(end);
    }

    @Test
    void delete() throws SQLException {
        int gameId = 1;
        Point target = new Point(3, 3);
        pieceDao.insertBlank(gameId, target);
    }
}
