package chess.controller;

import chess.model.Board;
import chess.model.GameStartCommand;
import chess.model.Piece;
import chess.model.Square;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public final class ChessController {
    public void game() {
        OutputView.startGame();
        String commandLine = InputView.inputStartOrEndGame();
        GameStartCommand.findCommand(commandLine).execute(this::initGame);
    }

    private void initGame() {
        Board board = new Board();
        OutputView.startGameBoard(new BoardDto(toDto(board)));
    }

    private List<String> toDto(final Board board) {
        List<String> boardDto = new ArrayList<>();
        for (Square square : board.keySet()) {
            boardDto.add(toDto(board.get(square)));
        }
        return boardDto;
    }

    private String toDto(final Piece piece) {
        String pieceDto = piece.getName().getValue();
        if (piece.isBlack()) {
            return pieceDto.toUpperCase();
        }
        return pieceDto;
    }
}
