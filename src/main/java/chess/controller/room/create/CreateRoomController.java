package chess.controller.room.create;

import chess.controller.Controller;
import chess.controller.main.Request;
import chess.service.room.CreateRoomService;

public class CreateRoomController implements Controller {

    private final CreateRoomService createRoomService;
    private final CreateRoomOutput createRoomOutput;

    public CreateRoomController(CreateRoomService createRoomService, CreateRoomOutput createRoomOutput) {
        this.createRoomService = createRoomService;
        this.createRoomOutput = createRoomOutput;
    }

    @Override
    public void run(Request request) {
        CreateRoomRequest createRoomRequest = CreateRoomRequest.from(request);
        int roomId = createRoomService.createRoom(createRoomRequest.getUserId());
        createRoomOutput.printCreateRoomSuccess(roomId);
    }
}
