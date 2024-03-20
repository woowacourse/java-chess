package chess;

import java.util.Map;

import chess.domain.attribute.Square;
import chess.domain.chessboard.Chessboard;
import chess.domain.piece.Piece;
import chess.view.InputView;
import chess.view.ResultView;
import chess.view.command.StartCommand;
import chess.view.dto.ChessboardDto;

public class ChessGame {

    private final InputView inputView;
    private final ResultView resultView;

    public ChessGame(final InputView inputView, final ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void run() {
        StartCommand command = inputView.askStartMessage();
        if (command.isStart()) {
            play();
        }
        resultView.printGameEnd();
    }

    private void play() {
        Map<Square, Piece> chessboard = Chessboard.create().getChessboard();
        ChessboardDto chessboardDto = new ChessboardDto(chessboard);
        resultView.printBoard(chessboardDto);
    }
}
