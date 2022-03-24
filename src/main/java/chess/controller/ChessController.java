package chess.controller;

import chess.model.Board;
import chess.model.GameStartCommand;
import chess.model.Square;
import chess.model.piece.Piece;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public final class ChessController {

    private final ChessService service;

    public ChessController(ChessService service) {
        this.service = service;
    }

    public void game() {
        OutputView.startGame();
        GameStartCommand command;
        do {
            List<String> request = InputView.inputStartOrEndGame();
            command = GameStartCommand.findCommand(request.get(0));
            request.remove(0);
            execute(command,request);
        } while (command.isNotEnd());
    }

    private void execute(GameStartCommand command, List<String> sqaures) {
        if (command.isStart()) {
            initGame();
        }
        if (command.isMove()) {
            move(sqaures.get(0), sqaures.get(1));
        }
    }

    private void initGame() {
        OutputView.startGameBoard(new BoardDto(toDto(service.initBoard())));
    }

    private void move(String from, String to) {
        Board movedBoard = service.move(from, to);
        OutputView.startGameBoard(new BoardDto(toDto(movedBoard)));
    }

    private List<String> toDto(final Board board) {
        List<String> boardDto = new ArrayList<>();
        for (Piece piece : board.getBoard()) {
            boardDto.add(toDto(piece));
        }
        return boardDto;
    }

    private String toDto(final Piece piece) {
        String pieceDto = piece.getLetter();
        if (piece.isBlack()) {
            return pieceDto.toUpperCase();
        }
        return pieceDto;
    }
}
