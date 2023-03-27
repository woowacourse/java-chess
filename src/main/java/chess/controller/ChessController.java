package chess.controller;

import static chess.controller.Command.END;
import static chess.controller.Command.MOVE;
import static chess.controller.Command.START;
import static chess.controller.Command.STATUS;

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
    private final Map<Command, Action> commandAction;

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.commandAction = initCommandAction();
    }

    private Map<Command, Action> initCommandAction() {
        Map<Command, Action> commandMapper = new HashMap<>();

        commandMapper.put(START, (chessGame, ignored) -> start(chessGame));
        commandMapper.put(END, (chessGame, ignored) -> end(chessGame));
        commandMapper.put(MOVE, this::move);
        commandMapper.put(STATUS, (chessGame, ignored) -> status(chessGame));

        return commandMapper;
    }

    public void run() {
        final ChessGameDao chessGameDao = new JdbcChessGameDao();
        final PieceDao pieceDao = new JdbcPieceDao();
        ChessGame chessGame = null;

        final List<String> command = inputView.readCommand();
        final String mainCommand = command.get(0);

        if (mainCommand.equals("new")) {
            final Board board = BoardFactory.generateBoard();
            chessGame = new ChessGame(board, Turn.WHITE, chessGameDao, pieceDao);
            chessGameDao.save(chessGame);
            
            final int chessGameId = chessGameDao.findLastInsertId();
            chessGame.setId(chessGameId);
            pieceDao.saveAll(chessGameId, PieceDto.from(board));
        }
        if (mainCommand.equals("enter")) {
            final int chessGameId = Integer.parseInt(command.get(1));
            final ChessGameDto chessGameDto = chessGameDao.findById(chessGameId);
            final List<PieceDto> pieceDtos = pieceDao.findAllByChessGameId(chessGameId);
            chessGame = ChessGameFactory.generateChessGame(chessGameDto, pieceDtos, chessGameDao, pieceDao);
        }

        outputView.printStartMessage();

        while (chessGame.isRunnable()) {
            play(chessGame);
        }
    }

    private void newGame() {

    }

    private void enterGame() {

    }

    private void play(final ChessGame chessGame) {
        try {
            final List<String> command = inputView.readCommand();
            final Command mainCommand = Command.from(command.get(MAIN_COMMAND_INDEX));
            final Action action = commandAction.get(mainCommand);
            action.execute(chessGame, command);
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
