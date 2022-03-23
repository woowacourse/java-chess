package chess;

import java.util.Map;

import chess.domain.ChessGame;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.dto.ChessBoardDto;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public void run() {
        InputView.startGuideMessage();

        while (InputView.requestGameStartCommand().equals("start")) {
            ChessGame chessGame = new ChessGame();
            Map<Position, Piece> chessBoard = chessGame.start();
            OutputView.printChessBoard(ChessBoardDto.of(chessBoard));
        }
    }
}
