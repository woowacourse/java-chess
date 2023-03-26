package database.production;

import chess.controller.GameStatus;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.piece.Team;
import chess.game.ChessGame;
import chess.game.Turn;
import database.ChessGameDao;
import database.dto.ChessGameDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ChessGameDaoTest {

    private final ChessGameDao chessGameDao = new ChessGameDao();

    @Test
    @DisplayName("체스 게임이 데이터베이스에 저장된다.")
    void saveChessGame() {
        Board board = new Board(BoardFactory.create());
        ChessGame chessGame = new ChessGame(board, new Turn(Team.WHITE));
        ChessGameDto chessGameDto = new ChessGameDto(chessGame.getId(), GameStatus.CONTINUING.name(), chessGame.getCurrentTeam().name());

        int savedId = chessGameDao.save(chessGameDto);
        assertThat(savedId).isGreaterThan(0);
        clear(savedId);
    }

    @Test
    @DisplayName("체스 게임 상태가 데이터베이스에 업데이트 된다.")
    void updateChessGame() {
        Board board = new Board(BoardFactory.create());
        ChessGame chessGame = new ChessGame(board, new Turn(Team.WHITE));
        ChessGameDto chessGameDto = new ChessGameDto(chessGame.getId(), GameStatus.CONTINUING.name(), chessGame.getCurrentTeam().name());
        int savedId = chessGameDao.save(chessGameDto);

        ChessGameDto updateChessGameDto = new ChessGameDto(chessGame.getId(), GameStatus.END.name(), chessGame.getCurrentTeam().name());
        assertDoesNotThrow(() -> chessGameDao.update(updateChessGameDto));
        clear(savedId);
    }

    private void clear(int id) {
        chessGameDao.delete(id);
    }
}
