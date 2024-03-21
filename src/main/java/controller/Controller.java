package controller;

import static util.InputRetryHelper.inputRetryHelper;
import static view.OutputView.printChessBoard;
import static view.OutputView.printInitialGamePrompt;

import java.util.List;
import model.ChessBoard;
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
        GameCommand gameCommand;
        List<String> inputCommand = InputView.parseCommand();
        gameCommand = inputRetryHelper(() -> GameCommand.from(inputCommand));
        controllChessBoard(chessBoard, gameCommand, inputCommand);
        return gameCommand;
    }

    private void controllChessBoard(ChessBoard chessBoard, GameCommand gameCommand, List<String> inputCommand) {
        if (gameCommand == GameCommand.MOVE) {
            int file = GameCommand.toSourceFileValue(inputCommand);
            int rank = GameCommand.toSourceRankValue(inputCommand);
            Position source = Position.of(file, rank);
            int toFile = GameCommand.toDestinationFileValue(inputCommand);
            int toRank = GameCommand.toDestinationRankValue(inputCommand);
            Position destination = Position.of(toFile, toRank);
            chessBoard.move(source, destination);
        }
    }

    private GameCommand executeInitial() {
        printInitialGamePrompt();
        return inputRetryHelper(InputView::inputInitialGameCommand);
    }
}
