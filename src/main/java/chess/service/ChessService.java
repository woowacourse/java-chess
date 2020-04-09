package chess.service;

import chess.command.Command;
import chess.controller.dto.RequestDto;
import chess.controller.dto.ResponseDto;
import chess.dao.ChessDAO;
import chess.domain.game.ChessGame;
import chess.domain.game.MoveParameter;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class ChessService {
    private final Map<Command, Function<RequestDto, ResponseDto>> commands = new HashMap<>();
    private final Map<Long, ChessGame> chessGames = new HashMap<>();
    private final ChessDAO chessDAO;

    public ChessService(ChessDAO chessDAO) {
        this.chessDAO = chessDAO;
        commands.put(Command.START, this::start);
        commands.put(Command.MOVE, this::move);
        commands.put(Command.END, this::end);
        commands.put(Command.UNKNOWN, this::unknown);
    }

    public ResponseDto run(RequestDto requestDto) {
        return commands.get(requestDto.getCommand()).apply(requestDto);
    }

    public ResponseDto start(RequestDto requestDto) {
        try {
            ChessGame chessGame = ChessGame.start();
            long id = chessDAO.createChessGame(chessGame);
            chessGames.put(id, chessGame);
            return new ResponseDto(chessGame.getBoardAndString(), chessGame.getTurn(),
                    chessGame.getStatus(), id);
        } catch (SQLException | IllegalArgumentException | UnsupportedOperationException e) {
            return new ResponseDto(e.getMessage());
        }
    }

    public ResponseDto move(final RequestDto requestDto) {
        long id = requestDto.getId();
        ChessGame chessGame = chessGames.get(id);
        ResponseDto responseDto = new ResponseDto(chessGame.getBoardAndString(), chessGame.getTurn(),
                chessGame.getStatus(), id);
        responseDto.setStatus(chessGame.getStatus());
        try {
            chessGame.move(MoveParameter.of(requestDto.getParameter()));
            responseDto = new ResponseDto(chessGame.getBoardAndString(), chessGame.getTurn(),
                    chessGame.getStatus(), id);
            responseDto.setStatus(chessGame.getStatus());
            setWinner(chessGame, responseDto);
        } catch (UnsupportedOperationException | IllegalArgumentException e) {
            responseDto.setMessage(e.getMessage());
        }
        return responseDto;
    }

    private void setWinner(final ChessGame chessGame, final ResponseDto responseDto) {
        if (chessGame.isEnd()) {
            responseDto.setWinner(chessGame.getWinner());
        }
    }

    public ResponseDto end(RequestDto requestDto) {
        long id = requestDto.getId();
        ChessGame chessGame = chessGames.get(id);
        List<String> parameter = requestDto.getParameter();
        ResponseDto responseDto = new ResponseDto("");
        try {
            saveGame(id, chessGame, parameter);
            endGame(id, parameter);
            chessGames.remove(id);
            responseDto.setRoomId(chessDAO.getRoomId());
        } catch (SQLException e) {
            responseDto.setMessage(e.getMessage());
        }
        return responseDto;
    }

    private void endGame(final long id, final List<String> parameter) throws SQLException {
        if ("".equals(parameter.get(0))) {
            chessDAO.deleteGame(id);
        }
    }

    private void saveGame(final long id, final ChessGame chessGame, final List<String> parameter) throws SQLException {
        if ("save".equals(parameter.get(0))) {
            chessDAO.addBoard(id, chessGame);
        }
    }


    private ResponseDto unknown(RequestDto requestDto) {
        return new ResponseDto("알 수 없는 명령어 입니다.");
    }

    public boolean isEnd(long id) {
        return chessGames.get(id).isEnd();
    }

    public ResponseDto getRoomId() {
        try {
            return new ResponseDto(chessDAO.getRoomId());
        } catch (SQLException e) {
            return new ResponseDto(e.getMessage());
        }
    }

    public ResponseDto load(final RequestDto requestDto) {
        ResponseDto responseDto = new ResponseDto("");
        long id = requestDto.getId();
        try {
            responseDto.setRoomId(chessDAO.getRoomId());
            ChessGame chessGame = chessDAO.findGameById(id);
            chessGames.put(id, chessGame);
            responseDto = new ResponseDto(chessGame.getBoardAndString(), chessGame.getTurn(),
                    chessGame.getStatus(), id);
        } catch (SQLException e) {
            responseDto.setMessage(e.getMessage());
        }
        return responseDto;
    }

    public ResponseDto restart(final RequestDto requestDto) {
        ChessGame chessGame = ChessGame.start();
        chessGames.put(requestDto.getId(), chessGame);
        return new ResponseDto(chessGame.getBoardAndString(), chessGame.getTurn(),
                chessGame.getStatus(), requestDto.getId());
    }
}
