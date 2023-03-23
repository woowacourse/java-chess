package controller;


import static controller.Command.COMMAND_INDEX;
import static controller.Command.CURRENT_SQUARE_INDEX;
import static controller.Command.EMPTY;
import static controller.Command.END;
import static controller.Command.MOVE;
import static controller.Command.MOVE_COMMAND_LENGTH;
import static controller.Command.STANDARD_COMMAND_LENGTH;
import static controller.Command.START;
import static controller.Command.TARGET_SQUARE_INDEX;
import static controller.GameRoomCommand.CREATE;
import static controller.GameRoomCommand.EXIT;
import static controller.GameRoomCommand.JOIN;
import static controller.GameRoomCommand.LIST;

import java.util.EnumMap;
import java.util.List;

import dto.GameInfoDto;
import dto.ScoreDto;
import repository.JdbcChessDao;
import repository.ProdConnector;
import service.ChessService;
import service.GameRoomService;
import view.InputView;
import view.OutputView;

public class ChessController {
    private final ChessService chessService;
    private final GameRoomService gameRoomService = new GameRoomService(new JdbcChessDao(new ProdConnector()));
    private final EnumMap<Command, Action> actions = new EnumMap<>(Command.class);
    private final EnumMap<GameRoomCommand, GameRoomAction> gameRoomActions = new EnumMap<>(GameRoomCommand.class);

    public ChessController(ChessService chessService) {
        this.chessService = chessService;
        actions.put(START, this::start);
        actions.put(MOVE, this::move);
        actions.put(END, this::end);
        gameRoomActions.put(CREATE, this::createGameRoom);
        gameRoomActions.put(LIST, this::readGameRooms);
        gameRoomActions.put(EXIT, this::exitGame);
        gameRoomActions.put(JOIN, this::joinGame);
    }


    private void exitGame(List<String> inputs) {
    }

    public void run() {
        GameRoomCommand gameRoomCommand = GameRoomCommand.NONE;
        while (gameRoomCommand != EXIT) {
            OutputView.printGameRoomInfo();
            List<String> inputs = InputView.requestCommand();
            gameRoomCommand = GameRoomCommand.find(inputs.get(0));
            GameRoomAction gameRoomAction = gameRoomActions.get(gameRoomCommand);
            gameRoomAction.execute(inputs);
        }
    }

    private void createGameRoom(List<String> inputs) {
        if(inputs.size() != 2) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
        gameRoomService.createGameRoom(inputs.get(1));
    }

    private void joinGame(List<String> inputs) {
        if(inputs.size() != 2) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
        gameStart(inputs.get(1));
    }

    private void readGameRooms(List<String> inputs) {
        List<String> gameRooms = gameRoomService.readGameRooms();
        OutputView.printGameRooms(gameRooms);
    }

    private void gameStart(String gameName) {
        GameInfoDto gameInfo = gameRoomService.getGameInfo(gameName);
        OutputView.printIsSavedGame(gameInfo.getBoardDtos().size());
        chessService.setUp(gameInfo);
        OutputView.printChessBoard(chessService.getChessBoard());
        OutputView.printChessInfo();
        Command command = START;
        while (command != END) {
            command = play();
        }
        List<ScoreDto> scoreDto = chessService.calculateFinalScore();
        OutputView.printScores(scoreDto);
    }

    private Command play() {
        try {
            List<String> inputs = InputView.requestCommand();
            Command command = Command.find(inputs.get(COMMAND_INDEX));
            Action action = actions.get(command);
            action.execute(inputs);
            return refreshCommandByResult(command);
        } catch (IllegalArgumentException | IllegalStateException e) {
            OutputView.printErrorMessage(e);
            return EMPTY;
        }
    }

    private Command refreshCommandByResult(Command command) {
        if (chessService.isFinished()) {
            return END;
        }
        return command;
    }

    private void start(List<String> inputs) {
        Command.validateCommandLength(inputs.size(), STANDARD_COMMAND_LENGTH);
        OutputView.printChessBoard(chessService.getChessBoard());
    }

    private void move(List<String> inputs) {
        Command.validateCommandLength(inputs.size(), MOVE_COMMAND_LENGTH);
        String currentSquareInput = inputs.get(CURRENT_SQUARE_INDEX);
        String targetSquareInput = inputs.get(TARGET_SQUARE_INDEX);
        chessService.move(currentSquareInput, targetSquareInput);
        OutputView.printChessBoard(chessService.getChessBoard());
    }

    private void end(List<String> inputs) {
        Command.validateCommandLength(inputs.size(), STANDARD_COMMAND_LENGTH);
        GameInfoDto gameInfoDto = chessService.end();
        gameRoomService.saveGameInfo(gameInfoDto);
    }
}
