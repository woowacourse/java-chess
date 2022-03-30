package chess;

import chess.domain.ChessBoard;
import chess.domain.CommandType;
import chess.domain.GameCommand;
import chess.domain.Pieces;
import chess.domain.ScoreCalculator;
import chess.domain.State.Finish;
import chess.domain.State.Ready;
import chess.domain.State.State;
import chess.domain.piece.Color;
import chess.domain.piece.generator.NormalPiecesGenerator;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessGame {

    private State state;
    private ChessBoard chessBoard;

    public ChessGame() {
        this.state = new Ready();
    }

    public void play() {
        OutputView.printStartMessage();
        while (!state.isFinished()) {
            requestCommand();
        }
    }

    private void requestCommand() {
        try {
            GameCommand gameCommand = new GameCommand(InputView.inputCommand());
            playGameByCommand(gameCommand);
        } catch (RuntimeException exception) {
            OutputView.printReplay(exception.getMessage());
            requestCommand();
        }
    }

    private void playGameByCommand(GameCommand gameCommand) {
        state = state.proceed(chessBoard, gameCommand);
        playStart(gameCommand);
        playMove(gameCommand);
        playStatus(gameCommand);
    }

    private void playStart(GameCommand gameCommand) {
        if (gameCommand.isSameCommandType(CommandType.START)) {
            chessBoard = new ChessBoard(new NormalPiecesGenerator());
            OutputView.printChessBoard(chessBoard.getPieces());
        }
    }

    private void playMove(GameCommand gameCommand) {
        if (gameCommand.isSameCommandType(CommandType.MOVE)) {
            doMovementByTurn(gameCommand);
        }
    }
    private void doMovementByTurn(GameCommand gameCommand) {
        chessBoard.move(gameCommand);
        OutputView.printChessBoard(chessBoard.getPieces());
        checkFinished();
    }

    private void checkFinished() {
        if (chessBoard.isEnd()) {
            OutputView.printWinner(state.getWinner());
            state = new Finish();
        }
    }

    private void playStatus(GameCommand gameCommand) {
        if (gameCommand.isSameCommandType(CommandType.STATUS)) {
            printStatus();
        }
    }

    private void printStatus() {
        printStatusByColor(Color.WHITE);
        printStatusByColor(Color.BLACK);
    }

    private void printStatusByColor(Color color) {
        List<Pieces> piecesOnColumns = chessBoard.getPiecesOnColumns(color);
        ScoreCalculator calculator = ScoreCalculator.getInstance();

        double score = calculator.calculateColumns(piecesOnColumns);
        OutputView.printStatus(color, score);
    }
}
