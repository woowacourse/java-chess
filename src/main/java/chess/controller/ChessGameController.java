package chess.controller;

import static chess.controller.IllegalArgumentExceptionHandler.repeat;

import chess.controller.dto.PieceResponse;
import chess.domain.game.Game;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.dto.CommandType;
import chess.view.dto.MoveRequest;
import chess.view.dto.Request;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ChessGameController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessGameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        outputView.printStartMessage();
        ready();
    }

    private void ready() {
        repeat(this::askToStart);
    }

    private void askToStart() {
        Request request = inputView.askCommand();
        CommandType commandType = request.getCommandType();
        if (commandType == CommandType.START) {
            play(new Game());
        }
        if (commandType == CommandType.MOVE) {
            throw new IllegalArgumentException("아직 게임이 시작되지 않은 상태입니다.");
        }
    }

    private void play(Game game) {
        outputView.printPieces(createResponses(game.getPieces()));
        while (repeat(() -> playOnce(game)) != CommandType.END) {
            outputView.printPieces(createResponses(game.getPieces()));
        }
    }

    private List<PieceResponse> createResponses(Map<Position, Piece> pieces) {
        List<PieceResponse> responses = new ArrayList<>();
        for (Entry<Position, Piece> positionToPiece : pieces.entrySet()) {
            responses.add(PieceResponse.of(positionToPiece.getKey(), positionToPiece.getValue()));
        }
        return responses;
    }

    private CommandType playOnce(Game game) {
        Request request = inputView.askCommand(game.getTurn());
        CommandType commandType = request.getCommandType();
        if (commandType == CommandType.START) {
            throw new IllegalArgumentException("게임이 진행중입니다.");
        }
        if (commandType == CommandType.MOVE) {
            MoveRequest moveRequest = request.getMoveRequest();
            game.movePiece(moveRequest.getSource(), moveRequest.getTarget());
        }
        return commandType;
    }
}
