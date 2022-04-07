package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.generator.BlackGenerator;
import chess.domain.generator.WhiteGenerator;
import chess.domain.player.Player;
import chess.domain.player.Team;
import chess.dto.PieceDto;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameDaoTest {

    private ChessGameDao chessGameDao = new ChessGameDao();

    @BeforeEach
    void setUp() {
        chessGameDao.resetPieces();
    }

    @Test
    @DisplayName("화이트 플레이어의 체스말을 생성 및 db에 저장한다.")
    void initializeWhitePlayer() {
        final Player whitePlayer = new Player(new WhiteGenerator(), Team.WHITE);
        chessGameDao.savePieces(whitePlayer);
        final int expected = 16;

        final List<PieceDto> pieces = chessGameDao.findAllPiece();
        final int actual = pieces.size();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("블랙 플레이어의 체스말을 생성 및 db에 저장한다.")
    void initializeBlackPlayer() {
        final Player blackPlayer = new Player(new BlackGenerator(), Team.BLACK);
        chessGameDao.savePieces(blackPlayer);
        final int expected = 16;

        final List<PieceDto> pieces = chessGameDao.findAllPiece();
        final int actual = pieces.size();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("체스판에 존재하는 모든 체스말을 확인한다.")
    void initializeChess() {
        final Player whitePlayer = new Player(new WhiteGenerator(), Team.WHITE);
        final Player blackPlayer = new Player(new BlackGenerator(), Team.BLACK);
        chessGameDao.initializeChessGame(whitePlayer, blackPlayer);
        final int expected = 32;

        final List<PieceDto> pieces = chessGameDao.findAllPiece();
        final int actual = pieces.size();

        assertThat(actual).isEqualTo(expected);
    }
}
