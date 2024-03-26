package controller;

import static util.InputRetryHelper.inputRetryHelper;
import static view.OutputView.printChessBoard;
import static view.OutputView.printInitialGamePrompt;

import java.util.List;
import model.chessboard.ChessBoard;
import model.position.Position;
import view.GameCommand;
import view.InputView;
import view.dto.InfoMapper;
import view.dto.PieceInfo;

public class Controller {

    public void execute() {
        GameCommand gameCommand = executeInitial();
        ChessBoard chessBoard = new ChessBoard();
        while (gameCommand != GameCommand.END) {
            List<PieceInfo> pieceInfos = InfoMapper.toPieceInfoMapper(chessBoard);
            printChessBoard(pieceInfos);
            gameCommand = inputRetryHelper(() -> runGame(chessBoard));
        }
    }

    private GameCommand runGame(ChessBoard chessBoard) {
        List<String> inputCommand = InputView.parseCommand();
        GameCommand gameCommand = inputRetryHelper(() -> GameCommand.from(inputCommand));
        if (gameCommand == GameCommand.MOVE) {
            controlChessBoard(chessBoard, inputCommand);
        }
        return gameCommand;
    }

    private void controlChessBoard(ChessBoard chessBoard, List<String> inputCommand) {
        Position source = generateSourcePosition(inputCommand);
        Position destination = generateDestinationPosition(inputCommand);
        chessBoard.move(source, destination);
    }

    private Position generateSourcePosition(List<String> inputCommand) {
        int file = GameCommand.toSourceFileValue(inputCommand);
        int rank = GameCommand.toSourceRankValue(inputCommand);
        return Position.of(file, rank);
    }

    private Position generateDestinationPosition(List<String> inputCommand) {
        int file = GameCommand.toDestinationFileValue(inputCommand);
        int rank = GameCommand.toDestinationRankValue(inputCommand);
        return Position.of(file, rank);
    }

    private GameCommand executeInitial() {
        printInitialGamePrompt();
        return inputRetryHelper(InputView::inputInitialGameCommand);
    }
}
