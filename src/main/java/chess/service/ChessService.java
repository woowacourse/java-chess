package chess.service;

import chess.controller.dto.RequestDto;
import chess.controller.dto.ResponseDto;
import chess.dao.ChessDAO;
import chess.domain.game.ChessGame;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class ChessService {
    private final Map<Command, Function<RequestDto, ResponseDto>> commands = new HashMap<>();
    private final Map<Long, ChessGame> chessGames = new HashMap<>();
    private final ChessDAO chessDAO = new ChessDAO();

    public ChessService() {
        commands.put(Command.START, this::start);
        commands.put(Command.MOVE, this::move);
        commands.put(Command.END, this::end);
        commands.put(Command.STATUS, this::status);
        commands.put(Command.UNKNOWN, this::unknown);
    }

    public ResponseDto run(RequestDto requestDto) {
        return commands.get(requestDto.getCommand()).apply(requestDto);
    }

    private ResponseDto start(final RequestDto requestDto) {
        try {
            ChessGame chessGame = ChessGame.start();
            long id = chessDAO.createChessGame(chessGame);
            return new ResponseDto(chessGame.getBoardAndString(), id);
        } catch (SQLException | IllegalArgumentException | UnsupportedOperationException e) {
            return new ResponseDto(e.getMessage());
        }
    }

    private ResponseDto move(final RequestDto requestDto) {
//        long id = Long.valueOf(parameters.get(1));
//        chessGames.get(id).move(MoveParameter.of(parameters));
//        return new ResponseDto(chessGame.getBoardAndString());
        return null;
    }

    private ResponseDto end(RequestDto requestDto) {
//        chessGame.end();
//        return new ResponseDto(chessGame.getBoardAndString());
        return null;
    }

    private ResponseDto status(RequestDto requestDto) {
//        chessGame.status();
//        return new ResponseDto(chessGame.getStatus());
        return null;
    }

    private ResponseDto unknown(RequestDto requestDto) {
        return new ResponseDto("알 수 없는 명령어 입니다.");
    }

    public boolean isEnd() {
//        return chessGame.isEnd();
        return false;
    }

    public ResponseDto getRoomId() {
        try {
            return new ResponseDto(chessDAO.getRoomId());
        } catch (SQLException e) {
            return new ResponseDto(e.getMessage());
        }
    }
}
