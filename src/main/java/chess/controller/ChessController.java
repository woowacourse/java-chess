package chess.controller;

import chess.domain.ChessBoardPosition;
import chess.domain.ChessGame;
import chess.dto.ChessMenDto;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessController {

    private static final int SOURCE_POSITION_INDEX = 0;
    private static final int TARGET_POSITION_INDEX = 1;

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
        List<String> moveInput = InputView.requestMove();
        ChessBoardPosition sourcePosition = ChessBoardPosition.of(moveInput.get(SOURCE_POSITION_INDEX));
        ChessBoardPosition targetPosition = ChessBoardPosition.of(moveInput.get(TARGET_POSITION_INDEX));

        chessGame.move(sourcePosition, targetPosition);
        OutputView.printCurrentChessBoard(ChessMenDto.of(chessGame.getBlackChessMen()),
                ChessMenDto.of(chessGame.getWhiteChessMen()));
    }
}
