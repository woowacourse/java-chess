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
        int file = GameCommand.toSourceFileValue(inputCommand);
        int rank = GameCommand.toSourceRankValue(inputCommand);
        Position source = Position.of(file, rank);
        int toFile = GameCommand.toDestinationFileValue(inputCommand);
        int toRank = GameCommand.toDestinationRankValue(inputCommand);
        Position destination = Position.of(toFile, toRank);
        chessBoard.move(source, destination);
    }

    private GameCommand executeInitial() {
        printInitialGamePrompt();
        return inputRetryHelper(InputView::inputInitialGameCommand);
    }
}
