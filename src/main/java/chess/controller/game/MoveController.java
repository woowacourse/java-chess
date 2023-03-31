package chess.controller.game;

import chess.controller.Controller;
import chess.controller.Request;
import chess.controller.Response;
import chess.controller.ResponseType;
import chess.controller.login.LoginSession;
import chess.controller.room.GameSession;
import chess.dao.GameDao;
import chess.dao.PieceDao;
import chess.domain.Position;
import chess.domain.board.Board;
import chess.domain.dto.BoardDto;
import chess.domain.exception.IllegalPieceMoveException;
import chess.domain.game.Game;

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
            Game game = GameSession.getGame();
            Game afterGame = game.move(makeStartingPosition(request), makeDestinationPosition(request));
            GameSession.replaceSession(afterGame);
            if (afterGame.isEnd()) {
                handleFinishGame();
                return new Response(ResponseType.FINISH);
            }
            return new Response(ResponseType.MOVE, makeBoardDto(afterGame));
        } catch (IllegalPieceMoveException | IllegalStateException e) {
            return new Response(ResponseType.FAIL, e.getMessage());

        }
    }

    private void handleFinishGame() {
        String gameId = GameDao.getGameIdOf(LoginSession.getCurrentPlayingRoomName());
        GameDao.deleteGameOf(LoginSession.getCurrentPlayingRoomName());
        PieceDao.delete(gameId);
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
