package chess;

import chess.domain.Board;
import chess.domain.BoardFactory;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import chess.dto.BoardDto;
import chess.view.GameCommand;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGame {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessGame(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void tryStart() {
        try {
            outputView.printStartGame();
            start();
        } catch (IllegalArgumentException exception) {
            outputView.printExceptionMessage(exception);
            tryStart();
        }
    }

    private void start() {
        GameCommand command = inputView.readCommand();
        if (command == GameCommand.START) {
            Board board = BoardFactory.createInitialBoard();
            showBoard(board);
            play(board);
            return;
        }
        if (command == GameCommand.MOVE || command == GameCommand.STATUS) {
            throw new IllegalArgumentException("아직 게임을 시작하지 않았습니다.");
        }
    }

    private void play(Board board) {
        GameStatus gameEnd = GameStatus.PLAY;
        while (gameEnd == GameStatus.PLAY) {
            gameEnd = tryProcessTurn(board);
        }
    }

    private GameStatus tryProcessTurn(Board board) {
        try {
            GameCommand command = inputView.readCommand();
            return processTurn(command, board);
        } catch (IllegalArgumentException exception) {
            outputView.printExceptionMessage(exception);
            tryProcessTurn(board);
        }
        return GameStatus.PLAY;
    }

    private GameStatus processTurn(GameCommand command, Board board) {
        if (command == GameCommand.START) {
            throw new IllegalArgumentException("이미 게임을 시작했습니다.");
        }
        if (command == GameCommand.END) {
            return GameStatus.END;
        }
        if (command == GameCommand.STATUS) {
            return showStatus(board);
        }
        return executeMove(board);
    }

    private GameStatus showStatus(Board board) {
        double blackScore = board.calculateScoreOf(Team.BLACK);
        double whiteScore = board.calculateScoreOf(Team.WHITE);
        outputView.printStatus(blackScore, whiteScore);
        return GameStatus.PLAY;
    }

    private GameStatus executeMove(Board board) {
        Position start = inputView.readPosition();
        Position end = inputView.readPosition();
        GameStatus gameStatus = board.tryMove(start, end);
        showBoard(board);
        if (gameStatus != GameStatus.PLAY) {
            outputView.printWinner(gameStatus);
        }
        return gameStatus;
    }

    private void showBoard(Board board) {
        BoardDto boardDto = BoardDto.of(board);
        outputView.printBoard(boardDto);
    }
}
