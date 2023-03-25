package chess.controller;

import chess.domain.game.ChessGame;
import chess.domain.game.command.BoardQuery;
import chess.domain.game.command.Command;
import chess.domain.game.command.EndCommand;
import chess.domain.game.command.MoveCommand;
import chess.domain.game.command.StartCommand;
import chess.domain.game.command.StatusQuery;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.resposne.PieceResponse;
import chess.view.resposne.StatusResponse;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessController {

    private final OutputView output;
    private final InputView input;
    private final ChessGame chessGame;
    private final Map<String, Action> actions = Map.of(
            "start", new Action(ignored -> start()),
            "end", new Action(ignored -> finish()),
            "move", new Action(this::move),
            "status", new Action(ignored -> printStatus()));


    public ChessController(OutputView output, InputView input) {
        this.output = output;
        this.input = input;
        chessGame = new ChessGame();
    }

    public void run() {
        output.printInitialMessage();
        while (true) {
            List<String> commands = input.inputGameCommand();
            executeCommandAndHandleError(commands);
        }
    }

    private void executeCommandAndHandleError(List<String> commands) {
        try {
            executeCommand(commands);
        } catch (Exception e) {
            output.printError(e);
        }
    }

    private void executeCommand(List<String> commands) {
        String command = commands.get(0);
        Action action = actions.get(command);
        if (action == null) {
            throw new IllegalArgumentException("잘못된 명령입니다.");
        }
        action.execute(commands);
    }

    public void move(List<String> commands) {
        String origin = commands.get(1);
        String destination = commands.get(2);
        Command command = MoveCommand.of(origin, destination);
        command.execute(chessGame);
        printBoard();
    }

    public void start() {
        Command command = new StartCommand();
        command.execute(chessGame);
        printBoard();
    }

    public void finish() {
        Command command = new EndCommand();
        command.execute(chessGame);
    }

    public void printBoard() {
        List<List<Piece>> boardResult = getBoardResult();
        output.printBoard(makeBoardResponse(boardResult));
    }

    private List<List<Piece>> getBoardResult() {
        return new BoardQuery().execute(chessGame);
    }

    private List<List<PieceResponse>> makeBoardResponse(List<List<Piece>> boardResult) {
        return boardResult.stream()
                .map(this::makeRankResponse)
                .collect(Collectors.toList());
    }

    private List<PieceResponse> makeRankResponse(List<Piece> row) {
        return row.stream()
                .map(PieceResponse::from)
                .collect(Collectors.toList());
    }

    private void printStatus() {
        Map<Color, Double> result = new StatusQuery().execute(chessGame);
        output.printStatus(makeStatusResponse(result));
    }

    private StatusResponse makeStatusResponse(Map<Color, Double> query) {
        double whiteScore = query.get(Color.WHITE);
        double blackScore = query.get(Color.BLACK);
        return new StatusResponse(whiteScore, blackScore);
    }
}
