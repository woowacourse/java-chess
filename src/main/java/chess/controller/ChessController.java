package chess.controller;

import chess.dao.ChessGameDao;
import chess.dao.ChessGameData;
import chess.dao.ChessGameSaveRecord;
import chess.dao.RoomName;
import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.SquareCoordinate;
import chess.domain.chessgame.ChessGame;
import chess.domain.piece.Team;
import chess.domain.winningstatus.GameResult;
import chess.domain.winningstatus.Score;
import chess.domain.winningstatus.WinningStatus;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static chess.controller.ChessBoardFormatter.getChessBoardMark;

public final class ChessController {

    public void run() {
        OutputView.printWelcomeMessage();
        final ChessGame chessGame = new ChessGame();

        do {
            checkException(this::playGame, chessGame);
        } while (chessGame.isNotEnd());
    }

    private <T> void checkException(final Consumer<T> consumer, final T parameter) {
        try {
            consumer.accept(parameter);
        } catch (Exception e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }

    private void playGame(final ChessGame chessGame) {
        final Command command = InputView.readCommand();
        processCommand(chessGame, command);
    }

    private void processCommand(final ChessGame chessGame, final Command command) {
        if (command == Command.START) {
            startGame(chessGame);
        }
        if (command == Command.MOVE) {
            movePiece(chessGame);
        }
        if (command == Command.STATUS) {
            showStatus(chessGame.status());
        }
        if (command == Command.SAVE) {
            saveGame(chessGame);
        }
        if (command == Command.LOAD) {
            loadGame(chessGame);
        }
        if (command == Command.END) {
            chessGame.end();
        }
    }

    private void startGame(final ChessGame chessGame) {
        chessGame.start();
        showChessBoard(chessGame.getChessBoard());
    }

    private void showChessBoard(final ChessBoard chessBoard) {
        OutputView.printChessBoard(getChessBoardMark(chessBoard));
    }

    private void movePiece(final ChessGame chessGame) {
        final SquareCoordinate from = SquareCoordinate.of(InputView.getCoordinate());
        final SquareCoordinate to = SquareCoordinate.of(InputView.getCoordinate());

        chessGame.move(from, to);
        showChessBoard(chessGame.getChessBoard());
    }

    private void showStatus(final WinningStatus winningStatus) {
        if (winningStatus.isWinnerDetermined()) {
            showStatusAfterRunning(winningStatus);
            return;
        }
        showStatusWhenRunning(winningStatus);
    }

    private void showStatusAfterRunning(final WinningStatus winningStatus) {
        GameResult gameResult = winningStatus.getWinner();
        OutputView.printWinnerAfterRunning(gameResult);
    }

    private void showStatusWhenRunning(final WinningStatus winningStatus) {
        final Map<Team, Score> scores = winningStatus.getScores();
        for (Team team : scores.keySet()) {
            OutputView.printScore(team, scores.get(team).getScore());
        }

        GameResult gameResult = winningStatus.getWinner();
        OutputView.printWinnerWhenRunning(gameResult);
    }

    private void saveGame(final ChessGame chessGame) {
        if (!chessGame.isRunning()) {
            throw new IllegalStateException("게임이 진행중이 아닐 때는 저장할 수 없습니다.");
        }

        RoomName roomName = new RoomName(InputView.readSaveRoomName());
        chessGame.save(roomName);
        OutputView.printSaved(roomName.getRoomName());
    }

    private void loadGame(final ChessGame chessGame) {
        if (!chessGame.isReady()) {
            throw new IllegalStateException("게임을 진행중일때는 다른 게임을 불러올 수 없습니다.");
        }

        ChessGameDao chessGameDao = new ChessGameDao();
        List<ChessGameSaveRecord> chessGameSaveRecords = chessGameDao.findChessGameSaveRecords();
        OutputView.printSavedRoomNames(chessGameSaveRecords);
        int loadId = InputView.readLoadId();

        if (!isExistRoom(chessGameSaveRecords, loadId)) {
            throw new IllegalArgumentException("저장 목록에 존재하지 않는 방의 id 입니다.");
        }

        ChessGameData chessGameData = chessGameDao.findChessGame(loadId);
        chessGame.load(chessGameData);
        showChessBoard(chessGame.getChessBoard());
    }

    private boolean isExistRoom(final List<ChessGameSaveRecord> chessGameSaveRecords, final int loadId) {
        boolean isExist = false;
        for (ChessGameSaveRecord record : chessGameSaveRecords) {
            isExist = isExist || (record.id == loadId);
        }
        return isExist;
    }
}
