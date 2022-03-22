package chess;

import chess.model.Board;
import chess.model.Piece;
import chess.model.Square;
import java.util.ArrayList;
import java.util.List;

public class ChessController {
    public void game() {
        OutputView.startGame();
        String commandLine = InputView.inputStartOrEndGame();
        GameStartCommand.findCommand(commandLine).execute(this::initGame);
    }

    private void initGame() {
        Board board = new Board();
        OutputView.startGameBoard(new BoardDto(toDto(board)));
    }

    private List<String> toDto(Board board) {
        List<String> boardDto = new ArrayList<>();
        for (Square square : board.keySet()) {
            boardDto.add(toDto(board.get(square)));
        }
        return boardDto;
    }

    private String toDto(Piece piece) {
        String pieceDto = piece.value().getValue();
        if (piece.isBlack()) {
            return pieceDto.toUpperCase();
        }
        return pieceDto;
    }
}
