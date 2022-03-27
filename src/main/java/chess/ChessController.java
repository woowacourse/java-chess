package chess;

import java.util.Map;
import java.util.NoSuchElementException;

import chess.domain.game.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.piece.position.Position;
import chess.dto.ChessBoardDto;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public void run() {
        ChessGame chessGame = new ChessGame();
        InputView.startGuideMessage();
        start(chessGame);
    }

    private void start(ChessGame chessGame) {
        while (!chessGame.isFinished()) {
            act(chessGame);
        }
    }

    private void act(ChessGame chessGame) {
        try {
            String input = InputView.requestCommand();
            Map<Position, Piece> chessBoard = Command.act(input, chessGame);
            OutputView.printChessBoard(ChessBoardDto.of(chessBoard));
        } catch (NoSuchElementException | IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
        }
    }
}
