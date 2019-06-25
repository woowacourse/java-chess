package chess.dao;

import chess.domain.Point;
import chess.domain.Team;
import chess.domain.pieces.Piece;
import chess.domain.pieces.Rook;
import chess.utils.DBUtil;
import chess.vo.PieceVo;
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
        pieceDao = new PieceDao(dataSource);
    }

    @Test
    void create() throws SQLException {
        int gameId = 1;
        Point point = new Point("a1");
        Piece rook = new Rook(Team.WHITE);
        PieceVo pieceVo = new PieceVo(point, rook);
        pieceDao.add(gameId, pieceVo);
    }

    @Test
    void read() throws SQLException {
        int gameId = 1;
        List<PieceVo> pieceVos = pieceDao.findPieceById(gameId);
        assertThat(pieceVos.get(0)).isEqualTo(new PieceVo(new Point(3, 3), new Rook(Team.WHITE)));
    }

    @Test
    void update() throws SQLException {
        int gameId = 1;
        Point start = new Point(0, 0);
        Point end = new Point(3, 3);
        pieceDao.updatePosition(gameId, start, end);
        PieceVo pieceVo = pieceDao.findPieceById(gameId).stream()
                .filter(vo -> vo.getPoint().equals(end))
                .collect(Collectors.toList())
                .get(0);
        assertThat(pieceVo.getPiece()).isEqualTo(new Rook(Team.WHITE));
        assertThat(pieceVo.getPoint()).isEqualTo(end);
    }

    @Test
    void delete() throws SQLException {
        int gameId = 1;
        Point target = new Point(3, 3);
        pieceDao.insertBlank(gameId, target);
    }
}
