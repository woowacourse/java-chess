package chess.controller.room;

import static chess.controller.room.RoomCommand.CREATE;
import static chess.controller.room.RoomCommand.EMPTY;
import static chess.controller.room.RoomCommand.END;
import static chess.controller.room.RoomCommand.HISTORY;
import static chess.controller.room.RoomCommand.JOIN;
import static chess.controller.room.RoomCommand.NAME_INDEX;
import static chess.controller.room.RoomCommand.ROOM_ID_INDEX;

import chess.controller.Action;
import chess.controller.CommandMapper;
import chess.controller.Controller;
import chess.controller.session.RoomSession;
import chess.controller.session.UserSession;
import chess.domain.room.Room;
import chess.service.RoomService;
import chess.view.input.RoomInputView;
import chess.view.output.RoomOutputView;
import java.util.List;
import java.util.Map;

public class RoomController implements Controller {
    private final RoomInputView inputView;
    private final RoomOutputView outputView;
    private final RoomService roomService;
    private final CommandMapper<RoomCommand, Action> commandMapper;

    public RoomController(
            final RoomInputView inputView,
            final RoomOutputView outputView,
            final RoomService roomService
    ) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.roomService = roomService;
        this.commandMapper = new CommandMapper<>(mappingCommand());
    }

    private Map<RoomCommand, Action> mappingCommand() {
        return Map.of(
                HISTORY, ignore -> history(),
                CREATE, this::create,
                JOIN, this::join,
                END, Action.EMPTY
        );
    }

    @Override
    public void run() {
        if (UserSession.get() == null) {
            throw new IllegalArgumentException("로그인 후 게임 관리를 할 수 있습니다.");
        }
        RoomCommand command = EMPTY;
        while (command != END) {
            command = play();
        }
    }

    private RoomCommand play() {
        try {
            final List<String> commands = inputView.readCommand(UserSession.getName(), RoomSession.getName());
            final RoomCommand command = RoomCommand.from(commands);
            command.validateCommandsSize(commands);
            final Action action = commandMapper.getValue(command);
            action.execute(commands);
            return command;
        } catch (IllegalArgumentException | IllegalStateException e) {
            outputView.printException(e.getMessage());
            return EMPTY;
        }
    }

    private void history() {
        final List<Room> rooms = roomService.findAllByUserId(UserSession.getId());
        outputView.printRooms(rooms);
    }

    private void create(final List<String> commands) {
        final String roomName = commands.get(NAME_INDEX);
        roomService.save(roomName, UserSession.getId());
        outputView.printSaveSuccess(roomName);
    }

    private void join(final List<String> commands) {
        try {
            final int roomId = Integer.parseInt(commands.get(ROOM_ID_INDEX));
            final Room room = roomService.findById(roomId, UserSession.getId());
            RoomSession.add(room);
        } catch (final NumberFormatException e) {
            throw new IllegalArgumentException("올바른 값을 입력해주세요.");
        }
    }
}
