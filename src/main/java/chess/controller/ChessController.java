package chess.controller;

import chess.domain.board.ChessBoard;
import chess.domain.board.InitBoardGenerator;
import chess.domain.piece.Column;
import chess.domain.piece.Row;
import chess.view.InputView;
import chess.view.OutputVIew;

import java.util.Map.Entry;

public class ChessController {

    private ChessBoard chessBoard;

    public void run() {
        chessBoard = new ChessBoard(InitBoardGenerator.initChessBoard());
        OutputVIew.printChessBoard(chessBoard);

        while (!chessBoard.isEnd()) {
            moveWhite();
            OutputVIew.printChessBoard(chessBoard);
            if (chessBoard.isEnd()) break;
            moveBlack();
            OutputVIew.printChessBoard(chessBoard);
        }
    }

    private void moveWhite() {
        Entry<Column, Row> currentPosition = InputView.choiceWhitePiece();
        Entry<Column, Row> destination = InputView.choiceDestination();
        chessBoard.moveWhite(currentPosition, destination);
    }

    private void moveBlack() {
        Entry<Column, Row> currentPosition = InputView.choiceBlackPiece();
        Entry<Column, Row> destination = InputView.choiceDestination();
        chessBoard.moveBlack(currentPosition, destination);
    }
}
