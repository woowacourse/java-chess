package chess.controller;

import chess.domain.Board;
import chess.domain.BoardFactory;
import chess.domain.ChessGame;
import chess.domain.Movement;
import chess.dto.BoardStatusDto;
import chess.dto.MovementDto;
import chess.exception.CustomExceptionHandler;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    public void play() {
        CustomExceptionHandler.handle(InputView::inputStartCommand);
        Board board = new Board(BoardFactory.generateStartBoard());
        ChessGame chessGame = new ChessGame(board);
        OutputView.printChessBoard(board.mapPositionToCharacter());

        CustomExceptionHandler.handle(() -> playTurn(chessGame, board));
    }

    private static void playTurn(ChessGame chessGame, Board board) {
        MovementDto movementDto;
        while ((movementDto = InputView.isInputMove()).isMove()) {
            Movement movement = movementDto.movement();
            chessGame.movePiece(movement);
            OutputView.printGameStatus(new BoardStatusDto(board.mapPositionToCharacter(), chessGame.checkStatus()));
        }
    }
}
