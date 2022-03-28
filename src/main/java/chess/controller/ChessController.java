package chess.controller;

import static chess.view.InputView.*;
import static chess.view.OutputView.*;

import chess.domain.ChessBoardPosition;
import chess.domain.ChessGame;
import chess.dto.ChessMenDto;
import chess.dto.ChessStatusDto;
import java.util.List;

public class ChessController {
    private static final int SOURCE_POSITION_INDEX = 1;
    private static final int TARGET_POSITION_INDEX = 2;
    private static final int COMMAND_INDEX = 0;
    private static final String STATUS_COMMAND = "status";
    private static final String MOVE_COMMAND = "move";

    public void run() {
        printChessGameStart();

        while (requestPlay()) {
            playChessGame();
        }
    }

    private void playChessGame() {
        ChessGame chessGame = ChessGame.create();
        ChessMenDto blackChessMenDto = ChessMenDto.of(chessGame.getBlackChessMen());
        ChessMenDto whiteChessMenDto = ChessMenDto.of(chessGame.getWhiteChessMen());
        printCurrentChessBoard(blackChessMenDto, whiteChessMenDto);

        while (!chessGame.end()) {
            executeCommand(chessGame);
        }
        printStatus(ChessStatusDto.of(chessGame));
    }

    private void executeCommand(ChessGame chessGame) {
        List<String> command = requestMoveOrStatus();
        if (command.get(COMMAND_INDEX).equals(STATUS_COMMAND)) {
            printStatus(ChessStatusDto.of(chessGame));
            return;
        }
        if (command.get(COMMAND_INDEX).equals(MOVE_COMMAND)) {
            executeMoveCommand(chessGame, command);
            return;
        }
        printInGameCommandExceptionMessage();
        executeCommand(chessGame);
    }

    private void executeMoveCommand(ChessGame chessGame, List<String> command) {
        try {
            ChessBoardPosition sourcePosition = ChessBoardPosition.of(command.get(SOURCE_POSITION_INDEX));
            ChessBoardPosition targetPosition = ChessBoardPosition.of(command.get(TARGET_POSITION_INDEX));

            chessGame.move(sourcePosition, targetPosition);
            printCurrentChessBoard(ChessMenDto.of(chessGame.getBlackChessMen()),
                    ChessMenDto.of(chessGame.getWhiteChessMen()));
        } catch (Exception e) {
            printExceptionMessage(e.getMessage());
            executeCommand(chessGame);
        }
    }
}
