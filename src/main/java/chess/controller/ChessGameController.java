package chess.controller;

import chess.domain.chessgame.ChessGame;
import chess.domain.chessgame.Command;
import chess.domain.chessgame.ScoreCalculator;
import chess.domain.dto.BoardDto;
import chess.domain.pieceInfo.Position;
import chess.domain.pieceInfo.Team;
import chess.view.InputView;
import chess.view.OutputView;

import static chess.domain.chessgame.CommandType.*;

public class ChessGameController {

    public void run() {
        ChessGame chessGame = new ChessGame();

        OutputView.printChessGameStartMessage();
        OutputView.printCommandGuideMessage();

        play(chessGame);
    }

    private void play(final ChessGame chessGame) {
        try {
            playChess(chessGame);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            play(chessGame);
        }
    }

    private void playChess(final ChessGame chessGame) {
        Command command = Command.of(InputView.inputCommand());
        boolean isEnd = playOneTurn(chessGame, command);
        while (!isEnd) {
            command = Command.of(InputView.inputCommand());
            isEnd = playOneTurn(chessGame, command);
        }
    }

    private boolean playOneTurn(final ChessGame chessGame, final Command command) {
        if (command.isCommand(END)) {
            return true;
        }
        if (command.isCommand(MOVE)) {
            return isFinishedAfterMove(chessGame, command);
        }
        return continueCommand(chessGame, command);
    }

    private boolean continueCommand(final ChessGame chessGame, final Command command) {
        if (command.isCommand(START)) {
            start(chessGame);
        }
        if (command.isCommand(STATUS)) {
            status(chessGame);
        }
        return false;
    }

    private void start(final ChessGame chessGame) {
        chessGame.start();
        OutputView.printBoard(BoardDto.of(chessGame.getBoard()));
    }

    private boolean isFinishedAfterMove(final ChessGame chessGame, final Command command) {
        Position source = Position.of(command.getSource());
        Position target = Position.of(command.getTarget());
        chessGame.move(source, target);

        OutputView.printBoard(BoardDto.of(chessGame.getBoard()));
        if (chessGame.isWin()) {
            OutputView.printWinner(chessGame.getTurn());
            return true;
        }
        return false;
    }

    private void status(final ChessGame chessGame) {
        ScoreCalculator scoreCalculator = chessGame.status();
        Team winner = chessGame.findWinner(scoreCalculator);
        result(chessGame, winner, scoreCalculator);
    }

    private void result(final ChessGame chessGame, final Team winner, final ScoreCalculator scoreCalculator) {
        double whiteScore = scoreCalculator.getWhiteScore();
        double blackScore = scoreCalculator.getBlackScore();
        OutputView.printBoard(BoardDto.of(chessGame.getBoard()));
        if (winner.equals(Team.NONE)) {
            OutputView.printScoreWithDraw(whiteScore, blackScore);
            return;
        }
        OutputView.printScoreWithWinner(whiteScore, blackScore, winner);
    }
}
