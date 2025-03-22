package chess.controller;

import chess.domain.board.ChessBoard;
import chess.domain.Color;
import chess.domain.Position;
import chess.domain.board.ChessBoardBasicInitializer;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        ChessBoard chessBoard = new ChessBoard(new ChessBoardBasicInitializer());
        List<Color> colors = Color.getGameColors();
        Color nowTurn = colors.get(0);

        boolean isKingCaptured = false;
        while (!isKingCaptured) {
            outputView.printChessBoard(chessBoard);
            outputView.printTurnMessage(nowTurn);

            processChessTurn(chessBoard, nowTurn);
            if (chessBoard.checkOppositeKingCaptured(nowTurn)) {
                break;
            }
            nowTurn = nowTurn.opposite();
        }

        outputView.printWinningMessage(nowTurn);
    }

    private void processChessTurn(ChessBoard chessBoard, Color nowTurn) {
        InputProcessor.processUntilSuccess(() -> {
            List<Position> originAndDestination = inputView.getMoveInput();
            Position origin = originAndDestination.get(0);
            if (chessBoard.getPieceOfPosition(origin).getColor() != nowTurn) {
                throw new IllegalArgumentException("현재는 %s의 차례입니다.".formatted(nowTurn.name()));
            }
            Position destination = originAndDestination.get(1);

            chessBoard.moveAndCapturePiece(origin, destination);
        }, OutputView::printErrorMessage);
    }
}
