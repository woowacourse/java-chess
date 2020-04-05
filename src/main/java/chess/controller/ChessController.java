package chess.controller;

import chess.controller.dto.Command;
import chess.controller.dto.PieceDto;
import chess.controller.dto.RequestDto;
import chess.controller.dto.ResponseDto;
import chess.domain.player.Team;
import chess.domain.position.Position;
import chess.service.ChessService;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ChessController {

    private ChessService chessService = new ChessService();
    private Map<Command, Consumer<List<String>>> runner;

    public ChessController() {
        this.runner = new HashMap<>();
        runner.put(Command.START, this::start);
        runner.put(Command.END, this::end);
        runner.put(Command.MOVE, this::move);
        runner.put(Command.UNKNOWN, this::unknown);
    }

    public boolean isEnd() {
        return chessService.isEnd();
    }

    public ResponseDto run() {
        ResponseDto responseDto = new ResponseDto();
        try {
            responseDto.setRoomId(chessService.getRoomId());
        } catch (SQLException e) {
            responseDto.setMessage(e.getMessage());
        }
        return responseDto;
    }

    public ResponseDto run(RequestDto requestDto) {
        String message = null;
        ResponseDto responseDto = new ResponseDto();
        try {
            Command command = requestDto.getCommand();
            runner.get(command).accept(requestDto.getParameter());
            if (chessService.isEnd()) {
                Team winner = chessService.getWinner();
                responseDto.setWinner(winner);
                responseDto.setMessage(winner.toString() + "가 승리했습니다.");
                responseDto.setRoomId(chessService.getRoomId());
                chessService.deleteGame();
            }
        } catch (IllegalArgumentException | UnsupportedOperationException | SQLException e) {
            responseDto.setMessage(e.getMessage());
        } finally {
            if (chessService.isReady()) {
                return run();
            }
            responseDto.setBoard(chessService.createBoardDto());
            responseDto.setScores(chessService.createScoreDto());
            responseDto.setTurn(chessService.createTurnDto());
            return responseDto;
        }
    }

    private void start(List<String> parameter) {
        try {
            chessService.start(parameter);
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
    }

    private void end(List<String> parameter) {
        try {
            chessService.end(parameter);
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
    }

    private void move(List<String> parameter) {
        chessService.move(parameter);
    }

    public ResponseDto getResponseDto() {
        Map<Position, PieceDto> boardDto = chessService.createBoardDto();
        Map<Team, Double> scoreDto = chessService.createScoreDto();
        return new ResponseDto(boardDto, scoreDto);
    }

    private ResponseDto unknown(List<String> parameter) {
        throw new IllegalArgumentException("잘못된 명령어입니다.");
    }
}
