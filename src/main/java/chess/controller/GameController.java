package chess.controller;

import static chess.controller.Command.END;
import static chess.controller.Command.MOVE;
import static chess.controller.Command.START;
import static chess.controller.Command.STATUS;
import static chess.domain.GameState.DONE;
import static chess.domain.GameState.READY;

import chess.command.ResponseCommand;
import chess.domain.GameRoom;
import chess.dto.BoardDto;
import chess.dto.GameResultDto;
import chess.service.GameService;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class GameController {
    private final Map<Command, CommandAction> commands = Map.of(STATUS, this::status, MOVE, this::move, END, this::end);
    private final GameService gameService = new GameService();
    private final InputView inputView;
    private final OutputView outputView;

    public GameController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        GameRoom gameRoom = readValidate(this::selectGame);
        outputView.printGameNumberRoomNotice(gameRoom.roomNumber());
        Command command = progress(gameRoom);
        while (command != END) {
            command = readValidate(() -> play(gameRoom));
        }
        GameResultDto gameResultDto = GameResultDto.from(gameService.winningTeams(gameRoom));
        outputView.printStartWinningTeam(gameResultDto.getWinningTeams());
        deleteGameIfEndGame(gameRoom);
    }

    private void deleteGameIfEndGame(final GameRoom gameRoom) {
        if (gameRoom.state() == DONE) {
            gameService.deleteAll(gameRoom);
        }
    }

    private GameRoom selectGame() {
        outputView.printCheckNewGameNotice();
        if (checkNewGame()) {
            return gameService.createGameRoom();
        }
        final List<Integer> gameRooms = gameService.findAllGameRooms();
        outputView.printEnterGameRoomNotice(gameRooms);
        final int roomNumber = inputView.readRoomNumber();
        validateRoomNumber(gameRooms, roomNumber);
        return gameService.loadGameRoom(roomNumber);
    }

    private void validateRoomNumber(final List<Integer> gameRooms, final int roomNumber) {
        if (!gameRooms.contains(roomNumber)) {
            throw new IllegalArgumentException("개설된 게임방 중에 참여할 게임방 번호를 입력하세요.");
        }
    }

    public boolean checkNewGame() {
        final String response = inputView.readUserInput();
        return ResponseCommand.isYes(response);
    }

    private Command progress(GameRoom gameRoom) {
        outputView.printStartNotice();
        if (gameRoom.state() == READY) {
            gameRoom.updateState();
            return readValidate(this::ready);
        }
        return readValidate(() -> play(gameRoom));
    }

    private Command ready() {
        List<String> inputs = inputView.readGameCommand();
        Command command = Command.from(inputs);
        if (command != START) {
            throw new IllegalArgumentException("start를 입력하여 게임을 실행하세요");
        }
        return command;
    }

    private Command play(GameRoom gameRoom) {
        outputView.printTeamInTurn(gameRoom.turn().name());
        BoardDto boardDto = BoardDto.of(gameRoom.board());
        outputView.printChessBoard(boardDto.getPieces());
        List<String> inputs = inputView.readGameCommand();
        Command command = Command.from(inputs);
        validateStart(command);
        CommandAction commandAction = commands.get(command);
        return commandAction.get(inputs, gameRoom);
    }

    private void validateStart(final Command command) {
        if (command == START) {
            throw new IllegalArgumentException("이미 게임이 시작되었습니다.");
        }
    }

    private Command move(List<String> commands, GameRoom gameRoom) {
        gameService.move(commands, gameRoom);
        gameService.updateGame(gameRoom);
        if (gameService.isCheckmate(gameRoom)) {
            gameRoom.updateState();
            return END;
        }
        return MOVE;
    }

    private Command status(List<String> commands, GameRoom gameRoom) {
        outputView.printScore(gameService.scores(gameRoom));
        GameResultDto gameResultDto = GameResultDto.from(gameService.winningTeams(gameRoom));
        outputView.printStartWinningTeam(gameResultDto.getWinningTeams());
        return STATUS;
    }

    private Command end(List<String> commands, GameRoom gameRoom) {
        outputView.printEndNotice();
        gameService.updateGame(gameRoom);
        return END;
    }

    private <T> T readValidate(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
