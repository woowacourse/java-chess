package chess.controller;

import chess.controller.request.Input;
import chess.controller.request.RequestType;
import chess.controller.resposne.Output;
import chess.controller.resposne.PieceResponse;
import chess.domain.game.ChessGame;
import java.util.List;
import java.util.stream.Collectors;

public class ChessController {

    private final Output output;
    private final Input input;
    private final ChessGame chessGame;

    public ChessController(Output output, Input input) {
        this.output = output;
        this.input = input;
        chessGame = new ChessGame();
    }

    public void run() {
        output.printInitialMessage();
        while (true) {
            try {
                RequestType requestType = input.inputGameCommand();
                requestType.execute(this);
            } catch (Exception e) {
                output.printError(e);
            }
        }
    }

    public void move(String origin, String destination) {
        chessGame.move(origin, destination);
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
    }

    public void finish() {
        chessGame.end();
    }

    public void printBoard() {
        output.printBoard(makeBoardResponse());
    }
}
