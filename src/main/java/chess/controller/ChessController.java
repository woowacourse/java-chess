package chess.controller;

import chess.domain.ChessGame;
import chess.dto.ChessBoardDto;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessController {

    private static final int SOURCE_POSITION_INDEX = 1;
    private static final int TARGET_POSITION_INDEX = 2;
    private static final int COMMAND_INDEX = 0;
    private static final String STATUS_COMMAND = "status";
    private static final String MOVE_COMMAND = "move";

    public void run() {
        OutputView.printChessGameStart();

        while (InputView.requestPlay()) {
            playChessGame();
        }
    }

    private void playChessGame() {
        ChessGame chessGame = ChessGame.create();
        sendDataForPrintCurrentChessBoard(chessGame);

        while (!chessGame.isGameEnd()) {
            doTurn(chessGame);
        }
    }

    private void sendDataForPrintCurrentChessBoard(ChessGame chessGame) {
        OutputView.printCurrentChessBoard(ChessBoardDto.of(chessGame.getChessBoardInformation()));
    }

    private void doTurn(ChessGame chessGame) {
        List<String> command = InputView.requestMoveOrStatus();
        inGameCommand(chessGame, command);
    }

    private void inGameCommand(ChessGame chessGame, List<String> command) {
        /*if (command.get(COMMAND_INDEX).equals(STATUS_COMMAND)) {
            statusCommand(chessGame);
        }*/
        if (command.get(COMMAND_INDEX).equals(MOVE_COMMAND)) {
            moveCommand(chessGame, command);
        }
    }

    /*private void statusCommand(ChessGame chessGame) {
        OutputView.printStatus(ChessStatusDto.of(chessGame));
    }*/

    private void moveCommand(ChessGame chessGame, List<String> command) {
        /*ChessBoardPosition sourcePosition = ChessBoardPosition.of(command.get(SOURCE_POSITION_INDEX));
        ChessBoardPosition targetPosition = ChessBoardPosition.of(command.get(TARGET_POSITION_INDEX));
        chessGame.move(sourcePosition, targetPosition);
        sendDataForPrintCurrentChessBoard(chessGame);*/
    }
}
