package chess.service;

import chess.dao.BoardDao;
import chess.dao.GameDao;
import chess.domain.board.Board;
import chess.domain.game.ChessGame;
import chess.domain.game.state.Ended;
import chess.domain.game.state.Ready;
import chess.domain.game.state.Started;
import chess.domain.game.state.State;
import chess.domain.piece.Color;
import chess.dto.BoardDto;
import chess.dto.ChessResponseDto;
import chess.dto.GameDto;
import java.util.Objects;

public class ChessService {

    private GameDao gameDao;
    private BoardDao boardDao;

    public ChessService() {
        this.gameDao = new GameDao();
        this.boardDao = new BoardDao();
    }

    public void load(final ChessGame chessGame) {
        final GameDto gameDto = gameDao.findByMaxId();
        final BoardDto boardDto = boardDao.findByGameId(gameDto.getId());

        final Board board = boardDto.toBoard();
        final State state = createState(gameDto.getState(), gameDto.getTurn(), board);

        chessGame.load(state, board);
    }

    private State createState(final String state, final String turn, final Board board) {
        if (Objects.equals(state, "Ready")) {
            return new Ready(board);
        }
        if (Objects.equals(state, "Started")) {
            return new Started(Color.from(turn), board);
        }
        return new Ended(board);
    }
}
