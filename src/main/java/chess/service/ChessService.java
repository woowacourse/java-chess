package chess.service;

import chess.dao.BoardDao;
import chess.dao.GameDao;
import chess.domain.board.BasicBoardFactory;
import chess.domain.board.Board;
import chess.domain.board.CustomBoardFactory;
import chess.domain.game.ChessGame;
import chess.domain.game.state.Ended;
import chess.domain.game.state.Ready;
import chess.domain.game.state.Started;
import chess.domain.game.state.State;
import chess.domain.piece.Color;
import chess.domain.position.Position;
import chess.domain.result.Score;
import chess.dto.BoardDto;
import chess.dto.ChessResponseDto;
import chess.dto.GameDto;
import chess.dto.PieceDto;
import chess.dto.StatusResponseDto;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ChessService {

    private final GameDao gameDao;
    private final BoardDao boardDao;

    public ChessService() {
        this.gameDao = new GameDao();
        this.boardDao = new BoardDao();
    }

    public ChessGame load(final String id) {
        try {
            final GameDto gameDto = gameDao.findById(id);
            final BoardDto boardDto = boardDao.findByGameId(id);
            final Board board = boardDto.toBoard();
            final State state = createState(gameDto.getState(), gameDto.getTurn());
            return new ChessGame(state, board);
        } catch (RuntimeException e) {
            return createNewGame(id);
        }
    }

    private ChessGame createNewGame(final String id) {
        ChessGame chessGame = new ChessGame(new Ready(), new Board(new BasicBoardFactory()));
        gameDao.save(GameDto.of(id, chessGame));
        boardDao.save(BoardDto.of(id, chessGame.getBoard()));
        return chessGame;
    }

    private State createState(final String state, final String turn) {
        if (Objects.equals(state, "Ready")) {
            return new Ready();
        }
        if (Objects.equals(state, "Started")) {
            return new Started(Color.from(turn));
        }
        return new Ended();
    }

    public ChessGame start(final String id, ChessGame chessGame) {
        if (!chessGame.isNotEnded()) {
            chessGame = new ChessGame(new Ready(), new Board(new BasicBoardFactory()));
            boardDao.deleteByGameId(id);
            boardDao.save(BoardDto.of(id, chessGame.getBoard()));
        }

        chessGame.start();
        gameDao.updateById(GameDto.of(id, chessGame));
        return chessGame;
    }

    public ChessGame end(final String id, final ChessGame chessGame) {
        chessGame.end();

        gameDao.updateById(GameDto.of(id, chessGame.getState(), chessGame.getState().getTurn()));
        return chessGame;
    }

    public ChessGame move(final String id, final ChessGame chessGame, final String source, final String target) {
        final Position from = Position.from(source);
        final Position to = Position.from(target);
        chessGame.move(from, to);

        boardDao.updateOnePosition(id, from.getName(), new PieceDto(chessGame.getBoard().getBoard().get(from)));
        boardDao.updateOnePosition(id, to.getName(), new PieceDto(chessGame.getBoard().getBoard().get(to)));
        gameDao.updateById(GameDto.of(id, chessGame.getState(), chessGame.getState().getTurn()));
        return chessGame;
    }

    public ChessGame status(final ChessGame chessGame) {
        chessGame.status();
        return chessGame;
    }

    public ChessResponseDto createChessResponseDto(final ChessGame chessGame) {
        return new ChessResponseDto(chessGame);
    }

    public StatusResponseDto createStatusResponseDto(final ChessGame chessGame) {
        final Score myScore = chessGame.calculateMyScore();
        final Score opponentScore = chessGame.calculateOpponentScore();
        return new StatusResponseDto(chessGame, myScore.getValue(), opponentScore.getValue(),
                myScore.decideResult(opponentScore).getName());
    }

    public List<GameDto> loadGames() {
        return gameDao.findAll();
    }
}
