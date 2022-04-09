package chess.domain;

import chess.controller.dto.GameStatusDto;
import chess.controller.dto.ScoreDto;
import chess.dao.BoardDao;
import chess.dao.JdbcBoardDao;
import chess.dao.GameStatusDao;
import chess.dao.JdbcGameStatusDao;
import chess.dao.TurnDao;
import chess.dao.JdbcTurnDao;
import chess.domain.board.Board;
import chess.domain.board.Result;
import chess.domain.board.strategy.BoardGenerationStrategy;
import chess.domain.board.strategy.CustomBoardStrategy;
import chess.domain.board.strategy.WebBasicBoardStrategy;
import chess.domain.piece.Blank;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceConvertor;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ChessGameService {

    public static final String WIN_MESSAGE = "승리 팀은 : %s 입니다.";

    private final BoardDao boardDao;
    private final TurnDao turnDao;
    private final GameStatusDao gameStatusDao;

    public ChessGameService() {
        boardDao = new JdbcBoardDao();
        turnDao = new JdbcTurnDao();
        gameStatusDao = new JdbcGameStatusDao();
    }

    public GameStatusDto startChessGame(BoardGenerationStrategy strategy) {
        if (gameStatusDao.getStatus().equals(GameStatus.PLAYING.toString())) {
            return loadChessGame();
        }
        ChessGame chessGame = new ChessGame();
        chessGame.startGame(strategy);
        gameStatusDao.update(gameStatusDao.getStatus(), chessGame.getGameStatus().toString());
        return GameStatusDto.of(chessGame);
    }

    public GameStatusDto loadChessGame() {
        ChessGame chessGame = createCustomChessGame();
        return GameStatusDto.of(chessGame);
    }

    public ScoreDto createScore() {
        Board board = createCustomBoard(boardDao.getBoard());
        Result result = board.createResult();
        return ScoreDto.of(result);
    }

    public GameStatusDto move(String from, String to) {
        checkReady();

        ChessGame chessGame = createCustomChessGame();
        moveAndUpdateBoard(from, to, chessGame);
        chessGame.checkGameStatus();

        turnDao.update(turnDao.getTurn(), chessGame.getTurn().toString());
        gameStatusDao.update(gameStatusDao.getStatus(), chessGame.getGameStatus().toString());

        return GameStatusDto.of(chessGame);
    }

    private void checkReady() {
        GameStatus gameStatus = GameStatus.of(gameStatusDao.getStatus());
        if (gameStatus.isReady()) {
            throw new IllegalArgumentException("체스 게임을 시작해야 합니다.");
        }
    }

    private ChessGame createCustomChessGame() {
        return new ChessGame(Team.of(turnDao.getTurn()), GameStatus.of(gameStatusDao.getStatus()),
                createCustomBoard(boardDao.getBoard()));
    }

    private Board createCustomBoard(Map<String, String> data) {
        Board board = new Board();
        board.initBoard(createStrategy(data));
        return board;
    }

    private CustomBoardStrategy createStrategy(Map<String, String> data) {
        Map<Position, Piece> board = data.entrySet()
                .stream()
                .collect(Collectors.toMap(m -> new Position(m.getKey()), m -> PieceConvertor.of(m.getValue())));

        CustomBoardStrategy strategy = new CustomBoardStrategy();
        strategy.put(board);
        return strategy;
    }

    private void moveAndUpdateBoard(String fromData, String toData, ChessGame chessGame) {
        Position from = new Position(fromData);
        Position to = new Position(toData);
        chessGame.move(from, to);
        boardDao.update(from.toString(), new Blank().toString());
        boardDao.update(to.toString(), chessGame.takePieceByPosition(to).toString());
    }

    public ScoreDto end() {
        ChessGame chessGame = createCustomChessGame();
        checkReady();
        Result result = chessGame.stepGame();
        return createEndScore(result);
    }

    private ScoreDto createEndScore(Result result) {
        ScoreDto scoreDto = null;
        if (gameStatusDao.getStatus().equals(GameStatus.CHECK_MATE.toString())) {
            scoreDto = new ScoreDto(String.format(WIN_MESSAGE, Team.of(turnDao.getTurn()).change()));
        }
        resetBoard();
        turnDao.reset();
        gameStatusDao.reset();
        return selectScoreDto(result, scoreDto);
    }

    private void resetBoard() {
        Board board = new Board();
        board.initBoard(new WebBasicBoardStrategy());
        boardDao.reset(board.toMap());
    }

    private ScoreDto selectScoreDto(Result result, ScoreDto scoreDto) {
        if (Objects.isNull(scoreDto)) {
            return ScoreDto.of(result);
        }
        return scoreDto;
    }
}