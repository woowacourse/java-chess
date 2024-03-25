package chess.controller;

import chess.domain.Board;
import chess.domain.BoardFactory;
import chess.domain.ChessGame;
import chess.domain.Positions;
import chess.dto.BoardDto;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    public void play() {
        InputView.inputStartCommand();
        Board board = new Board(BoardFactory.generateStartBoard());
        ChessGame chessGame = new ChessGame(board);

        OutputView.printChessBoard(new BoardDto(board));

        while (InputView.hasNextMove()) {
            Positions positions = InputView.inputPositions();
            chessGame.movePiece(positions);
            OutputView.printCheck(chessGame.findCheck());
            OutputView.printChessBoard(new BoardDto(chessGame.getBoard()));
        }
    }
}
