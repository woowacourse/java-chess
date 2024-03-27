package controller;

import model.chessboard.ChessBoard;
import model.position.File;
import model.position.Position;
import model.position.Rank;
import view.GameCommand;
import view.InputView;
import view.OutputView;
import view.dto.GameProceedRequest;
import view.dto.InfoMapper;
import view.dto.PieceInfo;

import java.util.List;

import static util.InputRetryHelper.inputRetryHelper;

public class Controller {
    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;
    private final OutputView outputView;

    public Controller() {
        outputView = new OutputView();
    }

    public void execute() {
        GameCommand gameCommand = executeInitial();
        ChessBoard chessBoard = new ChessBoard();
        while (gameCommand != GameCommand.END) {
            List<PieceInfo> pieceInfos = InfoMapper.toPieceInfo(chessBoard);
            outputView.printChessBoard(pieceInfos);
            gameCommand = inputRetryHelper(() -> runGame(chessBoard));
        }
    }

    private GameCommand executeInitial() {
        outputView.printInitialGamePrompt();
        return inputRetryHelper(InputView::inputInitialGameCommand);
    }

    private GameCommand runGame(final ChessBoard chessBoard) {
        GameProceedRequest gameProceedRequest = InputView.inputGameProceedCommand();
        if (gameProceedRequest.gameCommand() == GameCommand.START) {
            throw new IllegalArgumentException("이미 진행중인 게임이 존재합니다.");
        }
        if (gameProceedRequest.gameCommand() == GameCommand.MOVE) {
            controlChessBoard(chessBoard, gameProceedRequest);
        }
        return gameProceedRequest.gameCommand();
    }

    private void controlChessBoard(final ChessBoard chessBoard, final GameProceedRequest gameProceedRequest) {
        Position source = matchPosition(gameProceedRequest.sourcePosition()
                                                          .get());
        Position destination = matchPosition(gameProceedRequest.targetPosition()
                                                               .get());
        chessBoard.move(source, destination);
    }

    private Position matchPosition(final String position) {
        File sourceFile = File.from(position.charAt(FILE_INDEX));
        Rank sourceRank = Rank.from(position.charAt(RANK_INDEX));
        return Position.of(sourceFile, sourceRank);
    }
}
