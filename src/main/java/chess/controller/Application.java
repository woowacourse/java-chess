package chess.controller;

import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardGenerator;
import chess.domain.board.Coordinate;
import chess.domain.board.Result;
import chess.domain.piece.TeamType;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class Application {

    public static TeamType teamType = TeamType.WHITE;

    public static void main(String[] args) {
        Command command = getFirstCommand();
        if (command == Command.START) {
            run();
        }
    }

    private static Command getFirstCommand() {
        InputView.printGameStartMessage();
        List<String> playerCommand = InputView.inputPlayerCommand();
        return Command.findCommand(playerCommand.get(0));
    }

    private static void run() {
        ChessBoard chessBoard = new ChessBoard(ChessBoardGenerator.generateEmptyBoard());
        chessBoard.initializeDefaultPieces();
        OutputView.printBoard(chessBoard);
        startChessGame(chessBoard);
        if (chessBoard.isKingCheckmate()) {
            OutputView.printWinner(chessBoard.winner());
        }
    }

    private static void startChessGame(ChessBoard chessBoard) {
        Command command = Command.START;
        while (command != Command.END && !chessBoard.isKingCheckmate()) {
            List<String> playerCommand = InputView.inputPlayerCommand();
            command = Command.findCommand(playerCommand.get(0));
            startChessGame(command, chessBoard, playerCommand);
            OutputView.printBoard(chessBoard);
        }
    }

    private static void startChessGame(Command command, ChessBoard chessBoard, List<String> playerCommand) {
        if (command == Command.MOVE) {
            move(chessBoard, playerCommand);
            return;
        }
        if (command == Command.STATUS) {
            Result result = chessBoard.calculateScores();
            OutputView.printScoreResult(result);
        }
    }

    private static void move(ChessBoard chessBoard, List<String> playerCommand) {
        Coordinate currentCoordinate = Coordinate.from(playerCommand.get(1));
        Coordinate targetCoordinate = Coordinate.from(playerCommand.get(2));
        chessBoard.move(currentCoordinate, targetCoordinate, teamType);
        teamType = teamType.getOppositeTeam();
    }
}
