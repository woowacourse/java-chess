package chess.controller;

import chess.controller.resposne.Output;
import chess.controller.resposne.PieceResponse;
import chess.controller.resposne.StatusResponse;
import chess.domain.game.ChessGame;
import chess.domain.game.command.BoardQuery;
import chess.domain.game.command.Command;
import chess.domain.game.command.EndCommand;
import chess.domain.game.command.MoveCommand;
import chess.domain.game.command.StartCommand;
import chess.domain.game.command.StatusQuery;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessController {

    private final Output output;
    private final ChessGame chessGame;


    public ChessController(Output output) {
        this.output = output;
        chessGame = new ChessGame();
    }

    public void move(String origin, String destination) {
        Command command = MoveCommand.of(origin, destination);
        command.execute(chessGame);
    }

    public void start() {
        Command command = new StartCommand();
        command.execute(chessGame);
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
                .map(row -> row.stream()
                        .map(PieceResponse::from)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    public void printStatus() {
        Map<Color, Double> result = new StatusQuery().execute(chessGame);
        output.printStatus(makeStatusResponse(result));
    }

    private StatusResponse makeStatusResponse(Map<Color, Double> query) {
        double whiteScore = query.get(Color.WHITE);
        double blackScore = query.get(Color.BLACK);
        return new StatusResponse(whiteScore, blackScore);
    }
}
