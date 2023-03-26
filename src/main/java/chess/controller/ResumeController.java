package chess.controller;

import chess.controller.login.LoginSession;
import chess.dao.GameDao;
import chess.dao.PieceDao;
import chess.domain.board.Board;
import chess.domain.dto.BoardDto;
import chess.domain.game.Game;
import chess.domain.game.GameSession;

public class ResumeController implements Controller {

    private final static ResumeController INSTANCE = new ResumeController();
    private static final int ROOM_NAME_INDEX = 1;

    private ResumeController() {
    }

    public static ResumeController getInstance() {
        return INSTANCE;
    }

    @Override
    public Response execute(Request request) {
        try {
            validate();
            String roomName = getRoomName(request);
            if (!GameDao.haveRoomOf(roomName, LoginSession.getCurrentLoginId())) {
                return new Response(ResponseType.FAIL, "해당 이름의 게임은 존재하지 않습니다.");
            }
            String gameId = GameDao.getGameIdOf(roomName);
            Game game = Game.of(new Board(PieceDao.getBoardDataOf(gameId), GameDao.getGameTurnOf(gameId)));
            GameSession.makeSession(game);
            LoginSession.enterRoom(roomName);
            return new Response(ResponseType.RESUME, makeBoardDto(game));
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
