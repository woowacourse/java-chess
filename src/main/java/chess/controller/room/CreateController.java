package chess.controller.room;

import chess.controller.Controller;
import chess.controller.Request;
import chess.controller.Response;
import chess.controller.ResponseType;
import chess.controller.login.LoginSession;
import chess.dao.GameDao;
import chess.dao.PieceDao;
import chess.domain.board.Board;
import chess.domain.board.BoardGenerator;
import chess.domain.dto.BoardDto;
import chess.domain.dto.BoardSaveDto;
import chess.domain.game.Game;

public class CreateController implements Controller {

    private final static CreateController INSTANCE = new CreateController();
    private static final int ROOM_NAME_INDEX = 1;

    private CreateController() {
    }

    public static CreateController getInstance() {
        return INSTANCE;
    }

    private static Game makeGame(String gameId) {
        Game game = Game.of(BoardGenerator.makeBoard());
        PieceDao.saveBoard(BoardSaveDto.from(game.getBoard(), gameId));
        return game;
    }

    @Override
    public Response execute(Request request) {
        if (invalidRequest(request)) {
            return new Response(ResponseType.FAIL, "방을 생성할 수 없습니다.");
        }
        return makeResponse(request);
    }

    private Response makeResponse(Request request) {
        String currentLoginId = LoginSession.getCurrentLoginId();
        String gameId = makeGameId(request);
        String roomName = getRoomName(request);
        GameDao.enrollGameOf(currentLoginId, gameId, "WHITE", roomName);
        Game game = makeGame(gameId);
        LoginSession.enterRoom(roomName);
        GameSession.makeSession(game);
        return new Response(ResponseType.CREATE, makeBoardDto(game));
    }

    private String makeGameId(Request request) {
        String currentLoginId = LoginSession.getCurrentLoginId();
        return currentLoginId + getRoomName(request);
    }

    private boolean invalidRequest(Request request) {
        return GameDao.existRoomName(getRoomName(request));
    }

    private String getRoomName(Request request) {
        String input = request.getInput();
        String[] split = input.split(" ");
        return split[ROOM_NAME_INDEX];
    }


    public BoardDto makeBoardDto(Game game) {
        Board board = game.getBoard();
        return BoardDto.of(board);
    }
}
