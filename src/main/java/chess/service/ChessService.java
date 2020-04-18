package chess.service;

import chess.command.Command;
import chess.controller.dto.RequestDto;
import chess.controller.dto.ResponseDto;
import chess.dao.ChessDao;
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
    private final ChessDao chessDAO;

    public ChessService(ChessDao chessDAO) {
        this.chessDAO = chessDAO;
        commands.put(Command.MOVE, this::move);
        commands.put(Command.END, this::end);
        commands.put(Command.UNKNOWN, this::unknown);
    }

    public ResponseDto run(RequestDto requestDto) {
        Command command = requestDto.getCommand();
        if (command == Command.START) {
            return start();
        }
        return commands.get(requestDto.getCommand()).apply(requestDto);
    }

    public ResponseDto start() {
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
            responseDto.setMessage(chessGame.getWinner() + "가 승리했습니다.");
        }
    }

    public ResponseDto end(RequestDto requestDto) {
        long id = requestDto.getId();
        ChessGame chessGame = chessGames.get(id);
        List<String> parameter = requestDto.getParameter();
        try {
            saveGame(id, chessGame, parameter);
            endGame(id, parameter);
            chessGames.remove(id);
            return new ResponseDto(chessDAO.getRoomId());
        } catch (SQLException e) {
            return new ResponseDto(e.getMessage());
        }
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
        return ResponseDto.unknownCommand();
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
        ResponseDto responseDto = getRoomId();
        long id = requestDto.getId();
        try {
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
