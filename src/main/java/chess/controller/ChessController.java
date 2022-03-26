package chess.controller;

import chess.domain.ChessBoardPosition;
import chess.domain.ChessGame;
import chess.dto.ChessMenDto;
import chess.dto.ChessStatusDto;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessController {

    private static final int SOURCE_POSITION_INDEX = 1;
    private static final int TARGET_POSITION_INDEX = 2;

    public void run() {
        OutputView.printChessGameStart();

        while (InputView.requestPlay()) {
            playChessGame();
        }
    }

    private void playChessGame() {
        ChessGame chessGame = ChessGame.create();
        OutputView.printCurrentChessBoard(ChessMenDto.of(chessGame.getBlackChessMen()),
                ChessMenDto.of(chessGame.getWhiteChessMen()));

        while (!chessGame.isGameEnd()) {
            List<String> command = InputView.requestMoveOrStatus();
            inGameCommand(chessGame, command);
        }
    }

    private void inGameCommand(ChessGame chessGame, List<String> command) {
        if (command.get(0).equals("status")) {
            OutputView.printStatus(ChessStatusDto.of(chessGame));
        }
        if (command.get(0).equals("move")) {
            ChessBoardPosition sourcePosition = ChessBoardPosition.of(command.get(SOURCE_POSITION_INDEX));
            ChessBoardPosition targetPosition = ChessBoardPosition.of(command.get(TARGET_POSITION_INDEX));

            chessGame.move(sourcePosition, targetPosition);
            OutputView.printCurrentChessBoard(ChessMenDto.of(chessGame.getBlackChessMen()),
                    ChessMenDto.of(chessGame.getWhiteChessMen()));
        }
    }
}
