package chess.controller;

import chess.domain.board.ChessBoard;
import chess.domain.piece.coordinate.Coordinate;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessController {

    public void runChessGame(ChessBoard chessBoard) {
        OutputView.noticeGameStart();
        List<String> command = InputView.repeat(InputView::inputCommand);
        commandMoveCase(chessBoard, command);
        if (isCommandEnd(command)) {
            return;
        }
        commandStartCase(command);
    }

    private void commandMoveCase(ChessBoard chessBoard, List<String> splitedCommand) {
        if (isCommandMove(splitedCommand)){
            move(chessBoard, splitedCommand);
        }
    }

    private boolean isCommandMove(List<String> splitedCommand) {
        return "move".equals(InputView.extractCommand(splitedCommand));
    }

    private void move(ChessBoard chessBoard, List<String> splitedCommand) {
        List<String> source = InputView.extractSource(splitedCommand);
        List<String> destination = InputView.extractDestination(splitedCommand);
        Coordinate sourceCoordinate = Coordinate.createCoordinate(InputView.extractRow(source),InputView.extractColumn(source));
        Coordinate destinationCoordinate = Coordinate.createCoordinate(InputView.extractRow(destination),InputView.extractColumn(destination));

        chessBoard.move(sourceCoordinate,destinationCoordinate);
        OutputView.printChessBoard(chessBoard.chessBoard());
        runChessGame(chessBoard);
    }

    private boolean isCommandEnd(List<String> splitedCommand) {
        return "end".equals(InputView.extractCommand(splitedCommand));
    }

    private void commandStartCase(List<String> splitedCommand) {
        if (isCommandStart(splitedCommand)){
            runNewChessGame();
        }
    }

    private boolean isCommandStart(List<String> splitedCommand) {
        return "start".equals(InputView.extractCommand(splitedCommand));
    }

    private void runNewChessGame() {
        ChessBoard newChessBoard = ChessBoard.create();
        OutputView.printChessBoard(newChessBoard.chessBoard());
        runChessGame(newChessBoard);
    }

}
