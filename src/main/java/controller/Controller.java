package controller;

import model.chessboard.ChessBoard;
import model.position.File;
import model.position.Position;
import model.position.Rank;
import view.GameCommand;
import view.InputView;
import view.dto.GameProceedRequest;
import view.dto.InfoMapper;
import view.dto.PieceInfo;

import java.util.List;

import static util.InputRetryHelper.inputRetryHelper;
import static view.OutputView.printChessBoard;
import static view.OutputView.printInitialGamePrompt;

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

    private GameCommand executeInitial() {
        printInitialGamePrompt();
        return inputRetryHelper(InputView::inputInitialGameCommand);
    }

    private GameCommand runGame(ChessBoard chessBoard) {
        GameProceedRequest gameProceedRequest = InputView.inputGameProceedCommand();
        if (gameProceedRequest.gameCommand() == GameCommand.START) {
            throw new IllegalArgumentException("이미 진행중인 게임이 존재합니다.");
        }
        if (gameProceedRequest.gameCommand() == GameCommand.MOVE) {
            controlChessBoard(chessBoard, gameProceedRequest);
        }
        return gameProceedRequest.gameCommand();
    }

    private void controlChessBoard(ChessBoard chessBoard, GameProceedRequest gameProceedRequest) {
        File sourceFile = File.from(gameProceedRequest.sourcePosition()
                                                      .charAt(0));
        Rank sourceRank = Rank.from(gameProceedRequest.sourcePosition()
                                                      .charAt(1));
        Position source = Position.of(sourceFile, sourceRank);
        File targetFile = File.from(gameProceedRequest.targetPosition()
                                                      .charAt(0));
        Rank targetRank = Rank.from(gameProceedRequest.targetPosition()
                                                      .charAt(1));
        Position destination = Position.of(targetFile, targetRank);
        chessBoard.move(source, destination);
    }
}
