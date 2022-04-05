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
import chess.domain.position.Position;
import chess.domain.result.Score;
import chess.dto.BoardDto;
import chess.dto.ChessResponseDto;
import chess.dto.GameDto;
import chess.dto.PieceDto;
import chess.dto.StatusResponseDto;
import java.util.Objects;

public class ChessService {

    private final GameDao gameDao;
    private final BoardDao boardDao;
    private final ChessGame chessGame;

    public ChessService() {
        this.gameDao = new GameDao();
        this.boardDao = new BoardDao();
        this.chessGame = new ChessGame();
    }

    public void load() {
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

    public void start() {
        initializeChessGame();
        chessGame.start();

        gameDao.save(GameDto.of(chessGame));
        final Integer id = gameDao.findMaxId();
        boardDao.save(BoardDto.of(id, chessGame.getBoard()));
    }

    private void initializeChessGame() {
        if (!chessGame.isNotEnded()) {
            chessGame.initialize();
        }
    }

    public void end() {
        chessGame.end();

        final Integer id = gameDao.findMaxId();
        gameDao.updateById(GameDto.of(id, chessGame.getState(), chessGame.getState().getTurn()));
    }

    public void move(final String source, final String target) {
        final Position from = Position.from(source);
        final Position to = Position.from(target);
        chessGame.move(from, to);

        final Integer id = gameDao.findMaxId();
        boardDao.updateOnePosition(id, from.getName(), new PieceDto(chessGame.getBoard().getBoard().get(from)));
        boardDao.updateOnePosition(id, to.getName(), new PieceDto(chessGame.getBoard().getBoard().get(to)));
        gameDao.updateById(GameDto.of(id, chessGame.getState(), chessGame.getState().getTurn()));
    }

    public void status() {
        chessGame.status();
    }

    public ChessResponseDto createChessResponseDto() {
        return new ChessResponseDto(chessGame);
    }

    public ChessResponseDto createErrorChessResponseDto(final String message) {
        return new ChessResponseDto("error", message, chessGame);
    }

    public StatusResponseDto createStatusResponseDto() {
        final Score myScore = chessGame.calculateMyScore();
        final Score opponentScore = chessGame.calculateOpponentScore();
        return new StatusResponseDto(chessGame, myScore.getValue(), opponentScore.getValue(),
                myScore.decideResult(opponentScore).getName());
    }

    public StatusResponseDto creatErrorStatusResponseDto(final String message) {
        return new StatusResponseDto("error", message, chessGame);
    }
}
