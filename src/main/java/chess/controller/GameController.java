package chess.controller;

import static chess.controller.IllegalArgumentExceptionHandler.repeat;

import chess.controller.dto.PieceResponse;
import chess.domain.game.Game;
import chess.domain.game.GameResult;
import chess.domain.piece.Piece;
import chess.domain.position.Move;
import chess.domain.position.Position;
import chess.repository.jdbc.JdbcMoveDao;
import chess.repository.jdbc.JdbcRoomDao;
import chess.service.GameService;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.dto.CommandType;
import chess.view.dto.MoveRequest;
import chess.view.dto.Request;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class GameController extends Controller {

    private final GameService gameService;
    private final long roomId;

    public GameController(InputView inputView, OutputView outputView, long roomId) {
        super(inputView, outputView);
        this.gameService = new GameService(new JdbcRoomDao(), new JdbcMoveDao());
        this.roomId = roomId;
    }

    @Override
    public void run() {
        repeat(this::askToStart);
    }

    private void askToStart() {
        outputView.printStartMessage();
        Request request = inputView.askCommand();
        CommandType commandType = request.getCommandType();
        if (commandType == CommandType.START) {
            play(setUpGame());
        }
        if (Set.of(CommandType.MOVE, CommandType.STATUS).contains(commandType)) {
            throw new IllegalArgumentException("아직 게임이 시작되지 않은 상태입니다.");
        }
    }

    private Game setUpGame() {
        List<Move> moves = gameService.findMoves(roomId);
        return Game.from(moves);
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
        if (commandType == CommandType.STATUS) {
            outputView.printStatus(game.getResult());
        }
        if (commandType == CommandType.MOVE) {
            move(game, request);
            if (game.isGameOver()) {
                GameResult gameResult = game.getResult();
                gameService.updateWinner(roomId, gameResult.getWinner());
                outputView.printFinalWinner(gameResult);
                return CommandType.END;
            }
        }
        return commandType;
    }

    private void move(Game game, Request request) {
        MoveRequest moveRequest = request.getMoveRequest();
        game.movePiece(moveRequest.getSource(), moveRequest.getTarget());
        gameService.create(roomId, moveRequest.getSource(), moveRequest.getTarget());
    }
}
