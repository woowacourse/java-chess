package techcourse.fp.chess.controller;

import java.util.ArrayList;
import java.util.List;
import techcourse.fp.chess.domain.Board;
import techcourse.fp.chess.domain.BoardFactory;
import techcourse.fp.chess.domain.Position;
import techcourse.fp.chess.domain.piece.Color;
import techcourse.fp.chess.dto.request.CommandRequest;
import techcourse.fp.chess.dto.response.BoardResponse;
import techcourse.fp.chess.dto.response.ScoreResponse;
import techcourse.fp.chess.service.ChessGameService;
import techcourse.fp.chess.view.InputView;
import techcourse.fp.chess.view.OutputView;

public final class ChessController {

    private final InputView inputView;
    private final OutputView outputView;
    private final ChessGameService chessGameService;

    public ChessController(final InputView inputView, final OutputView outputView,
                           final ChessGameService chessGameService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.chessGameService = chessGameService;
    }

    public void run() {
        outputView.printInitialMessage();
        final Command command = getInitCommand();

        if (command == Command.START) {
            final Board board = createNewBoard();
            play(board);
        }

        if (command == Command.LOAD) {
            final Board board = getSavedBoard();
            play(board);
        }

        outputView.printEndMessage();
    }

    private Command getInitCommand() {
        try {
            final String input = inputView.readInitCommand();
            return Command.createInitCommand(input);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return getInitCommand();
        }
    }

    private Board createNewBoard() {
        final Board board = BoardFactory.generate();
        outputView.printBoard(BoardResponse.create(board.getBoard()));
        return board;
    }

    private Board getSavedBoard() {
        outputView.printGameInfos(chessGameService.findInfos());
        final Board board = chessGameService.findById(inputView.readChessGameId());
        outputView.printBoard(BoardResponse.create(board.getBoard()));
        return board;
    }

    private void play(final Board board) {
        try {
            Command command = null;

            while (command != Command.END) {
                final CommandRequest commandRequest = inputView.readInPlayCommand();
                command = playByCommand(commandRequest, board);
            }

        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            play(board);
        }
    }

    private Command playByCommand(final CommandRequest commandRequest, final Board board) {
        Command command = Command.createInPlayCommand(commandRequest.getMessage());

        if (command == Command.STATUS) {
            printStatus(board);
        }

        if (command == command.SAVE) {
            save(board);
        }

        if (command == command.MOVE) {
            move(board, commandRequest);
        }

        return command;
    }

    private void printStatus(final Board board) {
        List<ScoreResponse> scores = new ArrayList<>();
        addScoreByColor(Color.WHITE, scores, board);
        addScoreByColor(Color.BLACK, scores, board);

        outputView.printStatus(scores);
    }

    private void addScoreByColor(final Color color, final List<ScoreResponse> result, Board board) {
        final double score = board.findScoreByColor(color);
        result.add(ScoreResponse.of(color, score));
    }

    private void save(final Board board) {
        final String gameName = inputView.readSaveGameName();
        chessGameService.save(board, gameName);
        outputView.printSaveSuccessMessage();
    }

    private void move(final Board board, final CommandRequest commandRequest) {
        board.move(Position.from(commandRequest.getSource()), Position.from(commandRequest.getTarget()));
        final BoardResponse boardResponse = BoardResponse.create(board.getBoard());
        outputView.printBoard(boardResponse);
    }
}
