package chess.controller;

import chess.controller.request.EndRequestType;
import chess.controller.request.MoveRequestType;
import chess.controller.request.RequestType;
import chess.controller.request.StartRequestType;
import chess.domain.game.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.response.PieceResponse;
import java.util.List;
import java.util.stream.Collectors;

public class ChessController {

    private final OutputView outputView;
    private final InputView inputView;
    private final List<RequestType> requestTypes;
    private final ChessGame chessGame;

    public ChessController(OutputView outputView, InputView inputView) {
        this.outputView = outputView;
        this.inputView = inputView;
        chessGame = new ChessGame();
        requestTypes = List.of(
                new MoveRequestType(),
                new StartRequestType(),
                new EndRequestType()
        );
    }

    public void run() {
        outputView.printInitialMessage();
        while (true) {
            try {
                List<String> commands = inputView.inputGameCommand();
                execute(commands);
            } catch (Exception e) {
                outputView.printError(e);
            }
        }
    }

    public void execute(List<String> request) {
        RequestType type = requestTypes.stream()
                .filter(requestType -> requestType.isMatch(request))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 명령어입니다."));
        type.execute(this, request);
    }

    public void move(List<String> request) {
        chessGame.move(request.get(1), request.get(2));
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
