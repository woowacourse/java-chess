package chess;

import chess.domain.ChessBoard;
import chess.domain.ScoreCalculator;
import chess.domain.CommandType;
import chess.domain.GameCommand;
import chess.domain.piece.Color;
import chess.domain.piece.generator.NormalPiecesGenerator;
import chess.domain.piece.Piece;
import chess.domain.piece.generator.PiecesGenerator;
import chess.view.InputView;
import chess.view.ResultView;
import java.util.List;

public class ChessGame {

    private ChessBoard chessBoard;
    private Color turn;

    public ChessGame() {
        this.turn = Color.WHITE;
    }

    public void playGame() {
        ResultView.printStartMessage();
        GameCommand gameCommand;
        do {
            gameCommand = requestCommand();
        } while (!gameCommand.isSameCommandType(CommandType.END));
    }

    private GameCommand requestCommand() {
        try {
            GameCommand gameCommand = new GameCommand(InputView.inputCommand());
            playGameByCommand(gameCommand);
            return gameCommand;
        } catch (RuntimeException exception) {
            ResultView.printReplay(exception.getMessage());
            return requestCommand();
        }
    }

    private void playGameByCommand(GameCommand gameCommand) {
        if (gameCommand.isSameCommandType(CommandType.START)) {
            initChessBoard();
            return;
        }
        if (gameCommand.isSameCommandType(CommandType.END)) {
            return;
        }
        validateInitBoard();
        validateEndChessBoard();
        if (gameCommand.isSameCommandType(CommandType.STATUS)) {
            printStatus();
            return;
        }
        doMovementByTurn(gameCommand);
    }

    private void validateInitBoard() {
        if (chessBoard == null) {
            throw new IllegalStateException("체스판이 초기화되지 않았습니다.");
        }
    }

    private void validateEndChessBoard() {
        if (turn == Color.EMPTY) {
            throw new IllegalArgumentException("현재 판이 종료되서 더 이상 진행할 수 없습니다.");
        }
    }

    private void initChessBoard() {
        PiecesGenerator piecesGenerator = new NormalPiecesGenerator();
        chessBoard = new ChessBoard(piecesGenerator);
        turn = Color.WHITE;
        ResultView.printChessBoard(chessBoard.getPieces());
    }

    private void printStatus() {
        printStatusByColor(Color.WHITE);
        printStatusByColor(Color.BLACK);
    }

    private void printStatusByColor(Color color) {
        List<List<Piece>> piecesOnColumns = chessBoard.getPiecesOnColumns(color);
        ScoreCalculator calculator = ScoreCalculator.getInstance();
        double score = calculator.calculateColumns(piecesOnColumns);
        ResultView.printStatus(color, score);
    }

    private void doMovementByTurn(GameCommand gameCommand) {
        if (chessBoard.getColor(gameCommand.getFromPosition()) != turn) {
            throw new IllegalArgumentException("당신의 차례가 아닙니다.");
        }
        chessBoard.move(gameCommand);
        ResultView.printChessBoard(chessBoard.getPieces());
        if (chessBoard.isEnd()) {
            ResultView.printWinner(turn);
            turn = Color.EMPTY;
        }
        turn = turn.getReverseColor();
    }
}
