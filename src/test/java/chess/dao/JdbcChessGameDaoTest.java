package chess.dao;

import static chess.fixture.PiecesFixtures.PAWN_WHITE_B3;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import chess.domain.piece.Piece;
import chess.domain.piece.dto.FindPieceDto;
import chess.domain.piece.dto.GeneratePieceDto;
import chess.domain.piece.dto.SavePieceDto;
import chess.domain.piece.dto.UpdatePiecePositionDto;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.service.ChessGame;
import chess.domain.service.dto.ChessGameDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JdbcChessGameDaoTest {

    JdbcChessGameDao jdbcChessGameDao = JdbcChessGameDao.getInstance();

    @Test
    @DisplayName("DB 커넥션을 정상적으로 가져온다.")
    void getConnection() {
        // when, then
        try (final Connection connection = jdbcChessGameDao.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("이전의 체스 게임이 있는지 여부를 반환한다.")
    void isExistPreviousChessGame() {
        // given
        Long gameId = 1L;

        // when
        jdbcChessGameDao.saveNewChessGame();

        // then
        assertThat(jdbcChessGameDao.isExistPreviousChessGame(gameId)).isTrue();
    }

    @Test
    @DisplayName("기물을 저장한다.")
    void savePiece() {
        // given
        Piece pawn = PAWN_WHITE_B3;
        Long gameId = 10L;

        // when, then
        assertDoesNotThrow(() -> jdbcChessGameDao.savePiece(new SavePieceDto(pawn, gameId)));
    }

    @Test
    @DisplayName("게임 ID로 해당 게임에 사용된 기물들을 모두 조회한다.")
    void findAllPieceByGameId() {
        // given
        Long gameId = 11L;

        // when
        List<GeneratePieceDto> allPieceByGameId = jdbcChessGameDao.findAllPieceByGameId(gameId);

        // then
        assertThat(allPieceByGameId.size()).isEqualTo(32);
    }

    @Test
    @DisplayName("게임 ID로 해당 게임 정보를 반환한다.")
    void findChessGameByGameId() {
        // given
        Long gameId = 1L;

        // when
        ChessGameDto chessGameDto = jdbcChessGameDao.findChessGameByGameId(gameId);
        ChessGame generatedChessGame = chessGameDto.generateChessGame();

        // then
        assertThat(generatedChessGame.getId()).isEqualTo(gameId);
    }

    @Test
    @DisplayName("기물을 찾아서 포지션을 업데이트한다.")
    void updatePiecePosition() {
        // given
        Position positionToUpdate = new Position(File.B, Rank.FOUR);
        Long gameId = 20L;
        int rankToFind = 2;
        int fileToFind = 2;
        UpdatePiecePositionDto updatePiecePositionDto = new UpdatePiecePositionDto(positionToUpdate);
        FindPieceDto findPieceDto = new FindPieceDto(gameId, rankToFind, fileToFind);

        // when
        assertDoesNotThrow(() -> jdbcChessGameDao.updatePiecePosition(updatePiecePositionDto, findPieceDto));
    }
}
