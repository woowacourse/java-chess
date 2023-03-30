package chess.controller;

import static chess.controller.IllegalArgumentExceptionHandler.repeat;

import chess.controller.dto.PieceResponse;
import chess.domain.game.Game;
import chess.domain.game.GameResult;
import chess.domain.piece.Piece;
import chess.domain.position.Move;
import chess.domain.position.Position;
import chess.service.GameService;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.dto.game.GameCommandType;
import chess.view.dto.game.GameRequest;
import chess.view.dto.game.MoveRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class GameController {

    private final InputView inputView;
    private final OutputView outputView;
    private final GameService gameService;

    public GameController(InputView inputView, OutputView outputView, GameService gameService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.gameService = gameService;
    }

    public void play(long roomId) {
        repeat(() -> askToStart(roomId));
    }

    private void askToStart(long roomId) {
        outputView.printStartMessage();
        GameRequest request = inputView.askGameCommand();
        GameCommandType commandType = request.getCommandType();
        if (commandType == GameCommandType.START) {
            process(setUpGame(roomId));
        }
        if (Set.of(GameCommandType.MOVE, GameCommandType.STATUS).contains(commandType)) {
            throw new IllegalArgumentException("아직 게임이 시작되지 않은 상태입니다.");
        }
    }

    private Game setUpGame(long roomId) {
        List<Move> moves = gameService.findMoves(roomId);
        return Game.from(roomId, moves);
    }

    private void process(Game game) {
        outputView.printPieces(createResponses(game.getPieces()));
        while (repeat(() -> playOnce(game)) != GameCommandType.END) {
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

    private GameCommandType playOnce(Game game) {
        GameRequest request = inputView.askGameCommand(game.getTurn());
        GameCommandType commandType = request.getCommandType();
        if (commandType == GameCommandType.START) {
            throw new IllegalArgumentException("게임이 진행중입니다.");
        }
        if (commandType == GameCommandType.STATUS) {
            outputView.printStatus(game.getResult());
        }
        if (commandType == GameCommandType.MOVE) {
            move(game, request);
            GameResult gameResult = game.getResult();
            if (gameResult.isGameOver()) {
                gameService.updateWinner(game.getRoomId(), gameResult.getWinner());
                outputView.printFinalWinner(gameResult);
                return GameCommandType.END;
            }
        }
        return commandType;
    }

    private void move(Game game, GameRequest request) {
        MoveRequest moveRequest = request.getMoveRequest();
        game.movePiece(moveRequest.getSource(), moveRequest.getTarget());
        gameService.createMove(game.getRoomId(), moveRequest.getSource(), moveRequest.getTarget());
    }
}
