package chess.controller;

import chess.dao.ChessDB;
import chess.domain.board.Board;
import chess.domain.board.BoardGenerator;
import chess.domain.dto.BoardDto;
import chess.domain.game.Game;
import chess.domain.game.GameSession;

public class StartController implements Controller {
    private final static StartController INSTANCE = new StartController();

    private StartController() {
    }

    public static StartController getInstance() {
        return INSTANCE;
    }

    @Override
    public Response execute(Request request) {
        try {
            validate(request);
            ChessDB chessDB = new ChessDB();
            if (chessDB.existBoard()) {
                Game game = Game.of(chessDB.getBoardData());
                GameSession.makeSession(game);
                return new Response(ResponseType.START, makeBoardDto(game));
            }
            Game newGame = Game.of(BoardGenerator.makeBoard());
            GameSession.makeSession(newGame);
            return new Response(ResponseType.START, makeBoardDto(newGame));
        } catch (IllegalStateException e) {
            return new Response(ResponseType.FAIL, e.getMessage());
        }
    }

    private void validate(Request request) {
        validateRequest(request);
        validateBoard();
    }

    private void validateRequest(Request request) {
        if (request.getGameCommand() != GameCommand.START) {
            throw new IllegalArgumentException();
        }
    }

    private void validateBoard() {
        if (GameSession.existGame()) {
            throw new IllegalStateException("이미 게임이 시작되었습니다.");
        }
    }

    public BoardDto makeBoardDto(Game game) {
        Board board = game.getBoard();
        return BoardDto.of(board);
    }
}
