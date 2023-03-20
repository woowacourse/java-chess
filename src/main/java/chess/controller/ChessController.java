package chess.controller;

import chess.controller.request.RequestType;
import chess.domain.game.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.response.PieceResponse;
import java.util.List;
import java.util.stream.Collectors;

public class ChessController {

    private final OutputView outputView;
    private final InputView inputView;
    private final ChessGame chessGame;

    public ChessController(OutputView outputView, InputView inputView) {
        this.outputView = outputView;
        this.inputView = inputView;
        chessGame = new ChessGame();
    }

    public void run() {
        outputView.printInitialMessage();
        while (true) {
            try {
                RequestType requestType = inputView.inputGameCommand();
                requestType.execute(this);
            } catch (Exception e) {
                outputView.printError(e);
            }
        }
    }

    public void move(String origin, String destination) {
        chessGame.move(origin, destination);
        outputView.printBoard(makeBoardResponse());
    }

    private List<List<PieceResponse>> makeBoardResponse() {
        return chessGame.getPieces()
                .stream()
                .map(row -> row.stream()
                        .map(PieceResponse::from)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    public void start() {
        chessGame.start();
        outputView.printBoard(makeBoardResponse());
    }

    public void finish() {
        chessGame.end();
    }
}
