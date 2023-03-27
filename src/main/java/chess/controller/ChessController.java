package chess.controller;

import static chess.controller.command.GameCommand.END;
import static chess.controller.command.GameCommand.MOVE;
import static chess.controller.command.GameCommand.START;
import static chess.controller.command.GameCommand.STATUS;
import static chess.controller.command.RoomCommand.ENTER;
import static chess.controller.command.RoomCommand.NEW;

import chess.controller.action.GameAction;
import chess.controller.action.RoomAction;
import chess.controller.command.GameCommand;
import chess.controller.command.RoomCommand;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.game.ChessGame;
import chess.domain.game.ChessGameFactory;
import chess.domain.game.Status;
import chess.domain.game.Turn;
import chess.domain.position.Position;
import chess.dto.ChessGameDto;
import chess.dto.PieceDto;
import chess.repository.dao.ChessGameDao;
import chess.repository.dao.JdbcChessGameDao;
import chess.repository.dao.JdbcPieceDao;
import chess.repository.dao.PieceDao;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessController {

    private static final int MAIN_COMMAND_INDEX = 0;
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;

    private final InputView inputView;
    private final OutputView outputView;
    private final Map<RoomCommand, RoomAction> roomCommandAction;
    private final Map<GameCommand, GameAction> gameCommandAction;

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.roomCommandAction = initRoomCommandAction();
        this.gameCommandAction = initGameCommandAction();
    }

    private Map<RoomCommand, RoomAction> initRoomCommandAction() {
        Map<RoomCommand, RoomAction> commandMapper = new HashMap<>();

        commandMapper.put(NEW, (chessGameDao, pieceDao, ignored) -> newGame(chessGameDao, pieceDao));
        commandMapper.put(ENTER, this::enterGame);

        return commandMapper;
    }

    private Map<GameCommand, GameAction> initGameCommandAction() {
        Map<GameCommand, GameAction> commandMapper = new HashMap<>();

        commandMapper.put(START, (chessGame, ignored) -> start(chessGame));
        commandMapper.put(END, (chessGame, ignored) -> end(chessGame));
        commandMapper.put(MOVE, this::move);
        commandMapper.put(STATUS, (chessGame, ignored) -> status(chessGame));

        return commandMapper;
    }

    public void run() {
        final ChessGame chessGame = startChessGame();

        outputView.printStartMessage();

        while (chessGame.isRunnable()) {
            play(chessGame);
        }
    }

    private ChessGame startChessGame() {
        try {
            final List<String> roomCommand = inputView.readRoomCommand();
            final RoomCommand mainRoomCommand = RoomCommand.from(roomCommand.get(MAIN_COMMAND_INDEX));
            final RoomAction roomAction = roomCommandAction.get(mainRoomCommand);
            return roomAction.execute(new JdbcChessGameDao(), new JdbcPieceDao(), roomCommand);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e);
            return startChessGame();
        }
    }

    private ChessGame newGame(final ChessGameDao chessGameDao, final PieceDao pieceDao) {
        final Board board = BoardFactory.generateBoard();
        ChessGame chessGame = new ChessGame(board, Turn.WHITE, chessGameDao, pieceDao);
        chessGameDao.save(chessGame);

        final int chessGameId = chessGameDao.findLastInsertId();
        chessGame.setId(chessGameId);
        pieceDao.saveAll(chessGameId, PieceDto.from(board));

        return chessGame;
    }

    private ChessGame enterGame(final ChessGameDao chessGameDao, final PieceDao pieceDao,
                                final List<String> roomCommand) {
        if (roomCommand.size() != 2) {
            throw new IllegalArgumentException("잘못된 명령어입니다.");
        }
        final int chessGameId = Integer.parseInt(roomCommand.get(1));
        final ChessGameDto chessGameDto = chessGameDao.findById(chessGameId);
        final List<PieceDto> pieceDtos = pieceDao.findAllByChessGameId(chessGameId);

        return ChessGameFactory.generateChessGame(chessGameDto, pieceDtos, chessGameDao, pieceDao);
    }

    private void play(final ChessGame chessGame) {
        try {
            final List<String> gameCommand = inputView.readGameCommand();
            final GameCommand mainGameCommand = GameCommand.from(gameCommand.get(MAIN_COMMAND_INDEX));
            final GameAction gameAction = gameCommandAction.get(mainGameCommand);
            gameAction.execute(chessGame, gameCommand);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e);
            play(chessGame);
        }
    }

    private void start(final ChessGame chessGame) {
        chessGame.start();
        outputView.printBoard(chessGame.getBoard());
    }

    private void end(final ChessGame chessGame) {
        chessGame.end();
    }

    private void move(final ChessGame chessGame, final List<String> command) {
        if (command.size() != 3) {
            throw new IllegalArgumentException("잘못된 명령어입니다.");
        }
        final Position source = Position.from(command.get(SOURCE_INDEX));
        final Position target = Position.from(command.get(TARGET_INDEX));
        chessGame.movePiece(source, target);
        outputView.printBoard(chessGame.getBoard());
    }

    private void status(final ChessGame chessGame) {
        final Status status = chessGame.getStatus();
        outputView.printBoard(chessGame.getBoard());
        outputView.printStatus(status);
    }
}
