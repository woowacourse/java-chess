package chess.controller;

import chess.domain.Board;
import chess.domain.BoardFactory;
import chess.domain.ChessGame;
import chess.domain.Movement;
import chess.dto.BoardStatusDto;
import chess.exception.ImpossibleMoveExceptionHandler;
import chess.exception.InvalidCommandExceptionHandler;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    public void play() {
        InvalidCommandExceptionHandler.handle(InputView::inputStartCommand);
        Board board = new Board(BoardFactory.generateStartBoard());
        ChessGame chessGame = new ChessGame(board);
        OutputView.printChessBoard(board.mapPositionToCharacter());

        while (InvalidCommandExceptionHandler.handle(InputView::isInputMove)) {
            Movement movement = InvalidCommandExceptionHandler.handle(InputView::inputMovement).movement();
            ImpossibleMoveExceptionHandler.handle(() -> chessGame.movePiece(movement));
            OutputView.printGameStatus(new BoardStatusDto(board.mapPositionToCharacter(), chessGame.checkStatus()));
        }
    }
}
