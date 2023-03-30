package chess.controller;

import chess.controller.command.Command;
import chess.controller.command.CommandType;
import chess.controller.dto.InputRenderer;
import chess.controller.dto.OutputRenderer;
import chess.dao.DBBoardDao;
import chess.dao.DBRoomDao;
import chess.domain.Room;
import chess.service.ChessGameService;
import chess.service.RoomConnectionService;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;
import java.util.Map;

import static chess.controller.command.CommandType.CREATE;
import static chess.controller.command.CommandType.LOAD;

public final class FrontController {

    private final ErrorController errorController;
    private final RoomConnectionService roomConnectionService;
    private final Map<CommandType, Action> commandMapper;

    public FrontController(final ErrorController errorController, final RoomConnectionService roomConnectionService) {
        this.errorController = errorController;
        this.roomConnectionService = roomConnectionService;
        this.commandMapper = Map.of(
                CREATE, (ignore) -> createRoom(),
                LOAD, (command) -> loadRoom(command)
        );
    }

    public void run() {
        List<Room> rooms = roomConnectionService.getRooms();
        OutputView.printInitialRoomMessage(OutputRenderer.toRoomDto(rooms));

        errorController.retryIfThrowsException(() -> {
            Command command = readCommand();
            Action action = commandMapper.getOrDefault(command.getCommandType(),
                    (ignore) -> {
                        throw new IllegalArgumentException("명령어를 확인해주세요.");
                    });
            action.act(command);
        });
    }

    private Command readCommand() {
        return errorController.retryIfThrowsException(() ->
                InputRenderer.toCommand(InputView.readCommand()));
    }

    private void createRoom() {
        Room room = roomConnectionService.createRoom();
        enterChessGame(room);
    }

    private void loadRoom(Command command) {
        int selectedId = command.getArguments().get(0);
        Room room = roomConnectionService.connectRoom(selectedId);
        enterChessGame(room);
    }

    private void enterChessGame(Room room) {
        OutputView.printRoomEntryMessage(room.getId());
        ChessGameService chessGameService = new ChessGameService(new DBRoomDao(), new DBBoardDao(), room);
        ChessController chessController = new ChessController(new ErrorController(), chessGameService);
        chessController.run();
    }
}
