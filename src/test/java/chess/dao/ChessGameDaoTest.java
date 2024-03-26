package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.dto.ChessGameComponentDto;
import java.sql.Connection;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameDaoTest implements DaoTest {
    private ChessGameDao chessGameDao;

    @BeforeEach
    void initializeChessGameDao() {
        chessGameDao = new ChessGameDao();
    }

    @DisplayName("데이터베이스 연결이 되었는지 확인한다.")
    @Test
    void getConnection() {
        // when
        Connection connection = chessGameDao.getConnection();

        // then
        assertThat(connection).isNotNull();
    }

    @DisplayName("데이터베이스에서 전체 데이터를 조회한다.")
    @Test
    void findAll() {
        // when
        List<ChessGameComponentDto> dtos = chessGameDao.findAll();

        // then
        assertThat(dtos.size()).isEqualTo(32);
    }

    @DisplayName("데이터베이스에 데이터를 저장한다.")
    @Test
    void save() {
        // given
        ChessGameComponentDto chessGameComponentDto = new ChessGameComponentDto(
                Position.of(File.A, Rank.ONE), new Rook(Color.WHITE), 1);

        // when
        chessGameDao.save(chessGameComponentDto);

        // then
        assertThat(chessGameDao.findAll().size()).isEqualTo(33);
    }

    @DisplayName("데이터베이스에서 position에 해당되는 piece를 찾아온다.")
    @Test
    void findPieceByPosition() {
        // given
        Position position = Position.of(File.A, Rank.ONE);

        // when
        Piece piece = chessGameDao.findPieceByPosition(position);

        // then
        assertAll(
                () -> assertThat(piece).isInstanceOf(Rook.class),
                () -> assertThat(piece.getColor()).isEqualTo(Color.WHITE)
        );
    }

    @DisplayName("piece가 이동하면 데이터베이스에서 해당 정보를 수정한다.")
    @Test
    void update() {
        // given
        Position source = Position.of(File.A, Rank.ONE);
        Position target = Position.of(File.B, Rank.FIVE);

        // when
        chessGameDao.update(source, target);
        Piece targetPiece = chessGameDao.findPieceByPosition(target);
        Piece sourcePiece = chessGameDao.findPieceByPosition(source);

        // then
        assertAll(
                () -> assertThat(targetPiece).isInstanceOf(Rook.class),
                () -> assertThat(sourcePiece).isNull()
        );
    }

    @DisplayName("piece가 제거되면 데이터베이스에서 해당 정보를 삭제한다.")
    @Test
    void remove() {
        // given
        Position target = Position.of(File.A, Rank.ONE);

        // when
        chessGameDao.remove(target);
        Piece sourcePiece = chessGameDao.findPieceByPosition(target);

        // then
        assertThat(sourcePiece).isNull();
    }
}
