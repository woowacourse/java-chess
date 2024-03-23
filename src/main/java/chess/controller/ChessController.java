package chess.controller;

import chess.domain.Board;
import chess.domain.BoardFactory;
import chess.domain.ChessGame;
import chess.dto.BoardStatusDto;
import chess.dto.MovementDto;
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
            MovementDto movementDto = InvalidCommandExceptionHandler.handle(InputView::inputMovement);
            BoardStatusDto boardStatusDto = ImpossibleMoveExceptionHandler
                    .handle(() -> chessGame.movePiece(movementDto));
            OutputView.printGameStatus(boardStatusDto);
        }
    }
}
