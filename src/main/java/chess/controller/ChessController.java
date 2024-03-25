package chess.controller;

import static chess.domain.Status.NORMAL;

import chess.domain.Board;
import chess.domain.BoardFactory;
import chess.domain.ChessGame;
import chess.domain.Movement;
import chess.dto.BoardStatusDto;
import chess.dto.MovementDto;
import chess.exception.ImpossibleMoveException;
import chess.exception.InvalidCommandException;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    public void run() {
        validateStartCommand();
        Board board = new Board(BoardFactory.generateStartBoard());
        ChessGame chessGame = new ChessGame(board);
        OutputView.printGameStatus(new BoardStatusDto(board.getPieces(), NORMAL));

        play(chessGame, board);
    }

    private void validateStartCommand() {
        try {
            InputView.inputStartCommand();
        } catch (InvalidCommandException e) {
            OutputView.printErrorMessage(e.getMessage());
            validateStartCommand();
        }
    }

    private void play(ChessGame chessGame, Board board) {
        try {
            playTurns(chessGame, board);
        } catch (InvalidCommandException | ImpossibleMoveException e) {
            OutputView.printErrorMessage(e.getMessage());
            play(chessGame, board);
        }
    }

    private void playTurns(ChessGame chessGame, Board board) {
        MovementDto movementDto;
        while ((movementDto = InputView.inputCommand()).isMove()) {
            Movement movement = movementDto.toDomain();
            chessGame.movePiece(movement);
            OutputView.printGameStatus(new BoardStatusDto(board.getPieces(), chessGame.checkStatus()));
        }
    }
}
