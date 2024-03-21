package chess.controller;

import chess.domain.board.ChessBoard;
import chess.domain.state.GameState;
import chess.domain.state.Ready;
import chess.dto.ChessBoardDto;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessGame {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.initialBoard();
        ChessBoardDto chessBoardDto;
        GameState gameState = new Ready(chessBoard);

        while (!gameState.isEnd()) {
            List<String> command = inputView.readCommand();
            gameState = gameState.play(command);

            if (!gameState.isEnd()) {
                chessBoardDto = chessBoard.convertToDto();
                outputView.printChessBoard(chessBoardDto);
            }
        }
    }
}
