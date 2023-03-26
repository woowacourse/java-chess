package database.production;

import chess.controller.GameStatus;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.piece.Team;
import chess.game.ChessGame;
import chess.game.Turn;
import database.BoardDao;
import database.ChessGameDao;
import database.dto.ChessGameDto;
import database.dto.SquareDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class BoardDaoTest {

    private final ChessGameDao chessGameDao = new ChessGameDao();
    private final BoardDao boardDao = new BoardDao();

    @Test
    @DisplayName("체스 게임에 해당하는 보드가 정상적으로 DB에 저장된다.")
    void saveBoard() {
        Board board = new Board(BoardFactory.create());
        ChessGame chessGame = new ChessGame(board, new Turn(Team.WHITE));

        List<SquareDto> squareDtos = board.getBoard().entrySet()
                .stream()
                .map(entry -> SquareDto.of(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
        int gameId = chessGameDao.save(new ChessGameDto(chessGame.getId(), GameStatus.CONTINUING.name(), chessGame.getCurrentTeam().name()));

        assertDoesNotThrow(() -> boardDao.save(squareDtos, gameId));
        delete(gameId);
    }

    private void delete(int id) {
        boardDao.delete(id);
        chessGameDao.delete(id);
    }

}
