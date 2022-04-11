package chess.service;

import static org.assertj.core.api.Assertions.assertThat;

import chess.dao.InMemoryBoardDao;
import chess.dao.InMemoryGameDao;
import chess.service.dto.BoardDto;
import chess.service.dto.ChessGameDto;
import chess.service.dto.GameResultDto;
import chess.service.dto.GamesDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ChessServiceTest {
    private ChessService service;
    private InMemoryBoardDao boardDao;
    private InMemoryGameDao gameDao;

    @BeforeEach
    void setUp() {
        boardDao = new InMemoryBoardDao();
        gameDao = new InMemoryGameDao();
        service = new ChessService(boardDao, gameDao);
    }

    @Test
    void createGame() {
        service.createGame("firstGame");
        assertThat(gameDao.getGameTable().size()).isEqualTo(1);
    }

    @Test
    void initGame() {
        service.createGame("firstGame");
        service.initGame(1);
        assertThat(gameDao.getGameTable().size()).isEqualTo(1);
        assertThat(boardDao.getBoardByGameId(1).getPieces().size()).isEqualTo(64);
    }

    @Test
    void move() {
        service.createGame("firstGame");
        service.initGame(1);
        service.move(1, "a2", "a4");
        ChessGameDto chessGameDto = gameDao.getGameTable().get(1);
        BoardDto boardDto = boardDao.getBoardTable().get(1);
        boolean fromSquareIsEmpty = boardDto.getPieces().stream()
                .anyMatch(piece -> piece.getSquare().equals("a2") && piece.getType().equals("EMPTY"));
        boolean toSquareIsPawn = boardDto.getPieces().stream()
                .anyMatch(piece -> piece.getSquare().equals("a4") && piece.getType().equals("PAWN"));
        assertThat(fromSquareIsEmpty && toSquareIsPawn).isTrue();
    }

    @Test
    void isRunning() {
        service.createGame("firstGame");
        service.initGame(1);
        assertThat(service.isRunning(1)).isTrue();
    }

    @Test
    void isGameEmpty() {
        service.createGame("firstGame");
        service.initGame(1);
        assertThat(service.isGameEmpty(1)).isFalse();
    }

    @Test
    void endGame() {
        service.createGame("firstGame");
        service.initGame(1);
        service.endGame(1);
        BoardDto boardDto = boardDao.getBoardTable().get(1);
        String status = gameDao.getGameTable().get(1).getStatus();
        assertThat(boardDto).isNull();
        assertThat(status).isEqualTo("EMPTY");
    }

    @Test
    void getResult() {
        service.createGame("firstGame");
        service.initGame(1);
        GameResultDto result = service.getResult(1);
        assertThat(result.getIsDraw()).isTrue();
        assertThat(result.getPlayerPoints().get("WHITE")).isEqualTo(38.0);
        assertThat(result.getPlayerPoints().get("BLACK")).isEqualTo(38.0);
    }

    @Test
    void getAllGames() {
        service.createGame("firstGame");
        service.initGame(1);
        GamesDto allGames = service.getAllGames();
        assertThat(allGames.getGames().size()).isEqualTo(1);
    }
}