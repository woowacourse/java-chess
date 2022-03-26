package chess.controller;

import chess.model.Board;
import chess.model.GameStartCommand;
import chess.model.piece.Piece;
import chess.state.Ready;
import chess.state.Status;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public final class ChessController {

    private final ChessService service;

    public ChessController(ChessService service) {
        this.service = service;
    }

    public void playGame() {
        OutputView.startGame();
        Status status = new Ready();
        while (!status.isEnd()) {
            List<String> request = InputView.inputStartOrEndGame();
            GameStartCommand command = GameStartCommand.findCommand(request.get(0));
            status = status.changeStatus(command);
            request.remove(0);
            status = status.execute(service, request);
            outputBy(status, service.getBoard());
        }
    }

    private void outputBy(Status status, Board board) {
        if (status.isEnd()) {
            return;
        }
//        if (status.isStatus()) {
//            OutputView.점수()
//        }
        OutputView.startGameBoard(new BoardDto(toDto(board)));
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
