package chess.domain;

import chess.BoardJson;
import chess.dao.RoundDao;
import chess.dto.RoundDto;
import com.google.gson.JsonObject;

import java.sql.SQLException;
import java.util.List;

public class Game {
    private Round round;
    private Board board;
    private RoundDao roundDao;

    public Game() {
        round = new Round(0);
        roundDao = new RoundDao();
        this.board = BoardFactory.create();
    }

    public JsonObject play(int from, int to) throws SQLException {
        Spot startSpot = Spot.valueOf(from);
        Spot endSpot = Spot.valueOf(to);

        if (!board.checkTeam(startSpot, round.getTeam())) {
            return new BoardJson(board).getBoardJson();
        }

        Board movedBoard = board.move(startSpot, endSpot);
        if (!movedBoard.equals(board)) {
            RoundDto roundDto = new RoundDto();
            roundDto.setRound(round.getRound());
            roundDto.setFrom(from);
            roundDto.setTo(to);
            roundDao.addRound(roundDto);
            round.nextRound();
            board = movedBoard;
        }

        return new BoardJson(board).getBoardJson();
    }

    public JsonObject reload() throws SQLException {
        List<RoundDto> roundDtos = roundDao.selectRound();
        board = BoardFactory.create();
        roundDtos.forEach(roundDto -> {
            Spot from = Spot.valueOf(roundDto.getFrom());
            Spot to = Spot.valueOf(roundDto.getTo());
            board.move(from, to);
            round.nextRound();
        });
        return new BoardJson(board).getBoardJson();
    }

    public StatusBoard getStatusBoard() {
        return StatusBoardFactory.create(board);
    }
}
