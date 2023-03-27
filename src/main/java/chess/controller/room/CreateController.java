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

    @Override
    public Response execute(Request request) {
        try {
            validate();
            String currentLoginId = LoginSession.getCurrentLoginId();
            String roomName = getRoomName(request);
            String gameId = currentLoginId + roomName;
            boolean enrolled = GameDao.enrollGameOf(currentLoginId, gameId, "WHITE", roomName);
            if (!enrolled) {
                return new Response(ResponseType.FAIL, "해당 이름의 게임을 만들 수 없습니다.");
            }
            Game game = Game.of(BoardGenerator.makeBoard());
            PieceDao.saveBoard(BoardSaveDto.from(game.getBoard(), gameId));
            LoginSession.enterRoom(roomName);
            GameSession.makeSession(game);
            return new Response(ResponseType.CREATE, makeBoardDto(game));
        } catch (IllegalStateException e) {
            return new Response(ResponseType.FAIL, e.getMessage());
        }
    }

    private String getRoomName(Request request) {
        String input = request.getInput();
        String[] split = input.split(" ");
        return split[ROOM_NAME_INDEX];
    }

    private void validate() {
        loggedIn();
        validateBoard();
    }

    private void loggedIn() {
        if (!LoginSession.isLoggedIn()) {
            throw new IllegalStateException("로그인 되지 않았습니다.");
        }
    }

    private void validateBoard() {
        if (LoginSession.getCurrentPlayingRoomName() != null) {
            throw new IllegalStateException("이미 게임을 진행중입니다.");
        }
    }

    public BoardDto makeBoardDto(Game game) {
        Board board = game.getBoard();
        return BoardDto.of(board);
    }
}
