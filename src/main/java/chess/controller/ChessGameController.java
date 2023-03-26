package chess.controller;

import static chess.controller.IllegalArgumentExceptionHandler.repeat;

import chess.controller.dto.PieceResponse;
import chess.domain.game.Game;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.user.User;
import chess.service.UserService;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.dto.CommandType;
import chess.view.dto.MoveRequest;
import chess.view.dto.Request;
import chess.view.dto.user.UserCommandType;
import chess.view.dto.user.UserRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ChessGameController {

    private final InputView inputView;
    private final OutputView outputView;
    private final UserService userService;

    public ChessGameController(InputView inputView, OutputView outputView, UserService userService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.userService = userService;
    }

    public void start() {
        login();

        outputView.printStartMessage();
        ready();
    }

    private void login() {
        repeat(this::selectUser);
    }

    private void selectUser() {
        printAskUserNameMessages();
        UserRequest userRequest = inputView.askUserCommand();
        if (userRequest.getCommandType() == UserCommandType.USE) {
            User user = userService.findByName(userRequest.getUserName());
            askGame(user.getId());
            return;
        }
        long userId = userService.create(userRequest.getUserName());
        askGame(userId);
    }
    
    private void askGame(long userId) {

    }

    private void printAskUserNameMessages() {
        List<String> userNames = userService.findUserNames();
        if (userNames.size() > 1) {
            outputView.printSelectUserMessage(userNames);
        }
        outputView.printCreateUserMessage();
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
        if (Set.of(CommandType.MOVE, CommandType.STATUS).contains(commandType)) {
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
        if (commandType == CommandType.STATUS) {
            outputView.printStatus(game.getResult());
        }
        if (commandType == CommandType.MOVE) {
            MoveRequest moveRequest = request.getMoveRequest();
            game.movePiece(moveRequest.getSource(), moveRequest.getTarget());
            if (game.isGameOver()) {
                outputView.printFinalWinner(game.getResult());
                return CommandType.END;
            }
        }
        return commandType;
    }
}
