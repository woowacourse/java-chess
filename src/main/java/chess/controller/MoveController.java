package chess.controller;

import chess.controller.login.LoginSession;
import chess.dao.GameDao;
import chess.dao.PieceDao;
import chess.domain.Position;
import chess.domain.board.Board;
import chess.domain.dto.BoardDto;
import chess.domain.exception.IllegalPieceMoveException;
import chess.domain.game.Game;
import chess.domain.game.GameSession;

import java.util.Arrays;
import java.util.List;

public class MoveController implements Controller {
    private final static MoveController INSTANCE = new MoveController();

    private MoveController() {
    }

    public static MoveController getInstance() {
        return INSTANCE;
    }

    @Override
    public Response execute(Request request) {
        try {
            validate(request);
            Game game = getGame();
            Game afterGame = game.move(makeStartingPosition(request), makeDestinationPosition(request));
            if (afterGame.isEnd()) {
                handleFinishGame();
                return new Response(ResponseType.FINISH);
            }
            GameSession.replaceSession(afterGame);
            return new Response(ResponseType.MOVE, makeBoardDto(afterGame));
        } catch (IllegalStateException | IllegalPieceMoveException e) {
            return new Response(ResponseType.FAIL, e.getMessage());
        }
    }

    private void handleFinishGame() {
        String gameId = GameDao.getGameIdOf(LoginSession.getCurrentPlayingRoomName());
        GameDao.deleteGameOf(LoginSession.getCurrentPlayingRoomName());
        PieceDao.delete(gameId);
    }

    private void validate(Request request) {
        if (request.getGameCommand() != GameCommand.MOVE) {
            throw new IllegalArgumentException("잘못된 커맨드 요청입니다.");
        }
        if (!GameSession.existGame()) {
            throw new IllegalStateException("게임이 시작하지 않았습니다");
        }
    }

    private Game getGame() {
        if (!GameSession.existGame()) {
            throw new IllegalStateException("게임이 초기화 되지 않았습니다.");
        }
        return GameSession.getGame();
    }

    private Position makeStartingPosition(Request request) {
        String inputData = request.getInput();
        List<String> data = Arrays.asList(inputData.split(" "));
        return Position.of(data.get(1).charAt(0), data.get(1).charAt(1));
    }

    private Position makeDestinationPosition(Request request) {
        String inputData = request.getInput();
        List<String> data = Arrays.asList(inputData.split(" "));
        return Position.of(data.get(2).charAt(0), data.get(2).charAt(1));
    }

    public BoardDto makeBoardDto(Game game) {
        Board board = game.getBoard();
        return BoardDto.of(board);
    }

}
