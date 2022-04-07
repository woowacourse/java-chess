package chess.dao;

import chess.domain.Position;
import chess.domain.generator.BlackGenerator;
import chess.domain.generator.NoKingCustomGenerator;
import chess.domain.generator.WhiteGenerator;
import chess.domain.piece.Bishop;
import chess.domain.piece.Knight;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.player.Player;
import chess.domain.player.Team;
import chess.dto.MoveDto;
import chess.dto.PieceDto;
import chess.utils.DbConnector;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static chess.utils.DbConnector.getConnection;
import static org.assertj.core.api.Assertions.assertThat;

class PieceDaoTest {

    private final PieceDao pieceDao = new PieceDaoImpl();

    @AfterEach
    void tearDown() {
        final String sql = "truncate table piece";
        try (final Connection connection = getConnection()) {
            connection.prepareStatement(sql)
                    .executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("db에 초기 화이트 체스말들이 세팅되는지 확인한다.")
    void initializeWhitePieces() {
        pieceDao.initializePieces(new Player(new WhiteGenerator(), Team.WHITE));
        final String sql = "select count(*) from piece where team = 'WHITE'";
        final int expected = 16;

        final int actual = DbConnector.getCountResult(sql);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("db에 초기 블랙 체스말들이 세팅되는지 확인한다.")
    void initializeBlackPieces() {
        pieceDao.initializePieces(new Player(new BlackGenerator(), Team.BLACK));
        final String sql = "select count(*) from piece where team = 'BLACK'";
        final int expected = 16;

        final int actual = DbConnector.getCountResult(sql);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("db에서 팀의 체스말들을 모두 찾는다.")
    void findPiecesByTeam() {
        pieceDao.initializePieces(new Player(new NoKingCustomGenerator(), Team.WHITE));

        final List<PieceDto> actual = pieceDao.findPiecesByTeam(Team.WHITE);

        assertThat(actual).contains(
                PieceDto.from(new Rook(Position.of(1, 'a'))),
                PieceDto.from(new Rook(Position.of(1, 'h'))),
                PieceDto.from(new Knight(Position.of(1, 'b'))),
                PieceDto.from(new Knight(Position.of(1, 'g'))),
                PieceDto.from(new Bishop(Position.of(1, 'c'))),
                PieceDto.from(new Bishop(Position.of(1, 'f'))),
                PieceDto.from(new Queen(Position.of(1, 'd')))
        );
    }

    @Test
    @DisplayName("db에서 체스말의 위치를 업데이트해준다.")
    void updatePiece() {
        pieceDao.initializePieces(new Player(new WhiteGenerator(), Team.WHITE));
        final MoveDto moveDto = new MoveDto("a2", "a4");
        pieceDao.updatePiece(moveDto);
        final String sql = "select count(*) from piece where position = 'a4'";
        final int expected = 1;

        final int actual = DbConnector.getCountResult(sql);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("capture가 일어나는 경우 db에서 체스말을 삭제시켜준다.")
    void removePieceByCaptured() {
        pieceDao.initializePieces(new Player(new NoKingCustomGenerator(), Team.WHITE));
        pieceDao.initializePieces(new Player(new BlackGenerator(), Team.BLACK));
        final MoveDto moveDto = new MoveDto("a1", "a7");
        pieceDao.removePieceByCaptured(moveDto);
        final String sql = "select count(*) from piece where position = 'a7'";
        final int expected = 0;

        final int actual = DbConnector.getCountResult(sql);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("db에서 모든 체스말을 삭제시켜준다.")
    void endPieces() {
        pieceDao.initializePieces(new Player(new WhiteGenerator(), Team.WHITE));
        pieceDao.endPieces();
        final String sql = "select count(*) from piece";
        final int expected = 0;

        final int actual = DbConnector.getCountResult(sql);

        assertThat(actual).isEqualTo(expected);
    }
}
