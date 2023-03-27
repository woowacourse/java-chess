package chess.controller;

import chess.dao.ChessGameDao;
import chess.domain.ChessGame;
import chess.domain.exception.InvalidTurnException;
import chess.dto.ChessGameDto;
import chess.dto.SquareMoveDto;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

import static chess.view.Command.MOVE_DESTINATION_INDEX;
import static chess.view.Command.MOVE_SOURCE_INDEX;

// TODO: 커멘드 패턴 적용해보기
public class ChessController {

    private final ChessGameDao chessGameDao;
    private ChessGame chessGame;

    public ChessController(final ChessGameDao chessGameDao) {
        this.chessGameDao = chessGameDao;
        this.chessGame = new ChessGame();
    }

    public void run() {
        OutputView.printStartMessage();
        start();
        play();
        InputView.terminate();
    }

    private void start() {
        try {
            List<String> command = InputView.readCommand();
            chessGame.start(Command.from(command));
            OutputView.printGameStatus(chessGame.getGameStatus());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            start();
        }
    }

    private void play() {
        try {
            playUntilEnd();
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            play();
        }
    }

    // TODO: 함수형 인터페이스 고려
    private void playUntilEnd() {
        List<String> command = InputView.readCommand();
        while (Command.from(command).isMoveCommand()) {
            move(command.get(MOVE_SOURCE_INDEX), command.get(MOVE_DESTINATION_INDEX));
            OutputView.printGameStatus(chessGame.getGameStatus());
            command = InputView.readCommand();
        }
        checkStart(command);
        checkStatus(command);
        checkLoad(command);
        checkSave(command);
        checkEnd(command);
    }

    private void move(String current, String destination) {
        try {
            SquareMoveDto moveDto = SquareMoveDto.from(current, destination);
            chessGame.move(moveDto);
        } catch (InvalidTurnException e) {
            OutputView.printErrorMessage(e.getMessage());
            playUntilEnd();
        }
    }

    private void checkStart(final List<String> command) {
        if (Command.from(command).isStartCommand()) {
            chessGame.restart();
            OutputView.printGameStatus(chessGame.getGameStatus());
            playUntilEnd();
        }
    }

    private void checkStatus(final List<String> command) {
        if (Command.from(command).isStatusCommand()) {
            OutputView.printScore(chessGame.getScore());
            OutputView.printGameStatus(chessGame.getGameStatus());
            playUntilEnd();
        }
    }

    private void checkLoad(final List<String> command) {
        if (Command.from(command).isLoadCommand()) {
            String chessGameIdInput = command.get(1);
            load(Integer.parseInt(chessGameIdInput));
            OutputView.printGameStatus(chessGame.getGameStatus());
            playUntilEnd();
        }
    }

    private void load(final int id) {
        chessGame = chessGameDao.findById(id);
    }

    private void checkSave(final List<String> command) {
        if (Command.from(command).isSaveCommand()) {
            chessGameDao.save(ChessGameDto.of(chessGame));
            OutputView.printGameStatus(chessGame.getGameStatus());
            playUntilEnd();
        }
    }

    private void checkEnd(final List<String> command) {
        if (canNotPlay(Command.from(command))) {
            return;
        }
        playUntilEnd();
    }

    private boolean canNotPlay(final Command command) {
        return command.isEndCommand() || chessGame.isEnd();
    }
}
