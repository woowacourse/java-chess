package chess.controller;

import chess.domain.Board;
import chess.domain.BoardFactory;
import chess.domain.ChessGame;
import chess.domain.Position;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessController {
    public void play() {
        OutputView.printStartMessage();
        InputView.inputStartCommand();
        Board board = new Board(BoardFactory.generateStartBoard());
        ChessGame chessGame = new ChessGame(board);
        OutputView.printChessBoard(board.mapPositionToCharacter());
        List<Position> positions;
        while (!(positions = InputView.inputNextCommand()).isEmpty()) {
            chessGame.movePiece(positions, OutputView::printChessBoard, OutputView::printCheck);
        }
    }
}
