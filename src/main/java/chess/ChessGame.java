package chess;

import chess.domain.ChessBoard;
import chess.domain.CommandType;
import chess.domain.GameCommand;
import chess.domain.ScoreCalculator;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.generator.NormalPiecesGenerator;
import chess.domain.piece.generator.PiecesGenerator;
import chess.domain.position.Position;
import chess.view.InputView;
import chess.view.ResultView;
import java.util.List;
import java.util.Map;

public class ChessGame {

    private ChessBoard chessBoard;
    private Color turn;

    public ChessGame() {
        this.turn = Color.WHITE;
    }

    public void playGame() {
        ResultView.printStartMessage();
        do {
            requestCommand();
        } while (!turn.isEmpty());
    }

    private void requestCommand() {
        try {
            GameCommand gameCommand = new GameCommand(InputView.inputCommand());
            playGameByCommand(gameCommand);
        } catch (RuntimeException exception) {
            ResultView.printReplay(exception.getMessage());
            requestCommand();
        }
    }

    public void playGameByCommand(GameCommand gameCommand) {
        if (isEndCommand(gameCommand)) {
            turn = Color.EMPTY;
            return;
        }
        startGame(gameCommand);
        validateForStatusAndMove();
        showStatus(gameCommand);
        move(gameCommand);
    }

    private boolean isEndCommand(GameCommand gameCommand) {
        return gameCommand.isSameCommandType(CommandType.END);
    }

    private void startGame(GameCommand gameCommand) {
        if (gameCommand.isSameCommandType(CommandType.START)) {
            initChessBoard();
        }
    }

    private void initChessBoard() {
        PiecesGenerator piecesGenerator = new NormalPiecesGenerator();
        chessBoard = new ChessBoard(piecesGenerator);
        turn = Color.WHITE;
        ResultView.printChessBoard(chessBoard.getPieces());
    }

    private void validateForStatusAndMove() {
        validateInitBoard();
        validateEndChessBoard();
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

    private void showStatus(GameCommand gameCommand) {
        if (gameCommand.isSameCommandType(CommandType.STATUS)) {
            printStatus();
        }
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

    private void move(GameCommand gameCommand) {
        if (gameCommand.isSameCommandType(CommandType.MOVE)) {
            doMovementByTurn(gameCommand);
        }
    }

    private void doMovementByTurn(GameCommand gameCommand) {
        validatePlayerTurn(gameCommand);
        chessBoard.move(gameCommand);
        ResultView.printChessBoard(chessBoard.getPieces());
        doEnd();
        changeTurn();
    }

    private void changeTurn() {
        turn = turn.getReverseColor();
    }

    private void doEnd() {
        if (chessBoard.isEnd()) {
            ResultView.printWinner(turn);
            turn = Color.EMPTY;
        }
    }

    private void validatePlayerTurn(GameCommand gameCommand) {
        if (chessBoard.getPositionColor(gameCommand.getFromPosition()) != turn) {
            throw new IllegalArgumentException("당신의 차례가 아닙니다.");
        }
    }

    public Map<Position, Piece> getChessBoard() {
        return chessBoard.getPieces();
    }
}
