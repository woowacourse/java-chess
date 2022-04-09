package chess.service;

import chess.dao.BoardDao;
import chess.dto.BoardDto;
import chess.dto.CommandDto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FakeBoardDao implements BoardDao {

    Map<String, List<String>> board = new HashMap<>();

    @Override
    public BoardDto loadBoard() {
        board.put("a2", List.of("WHITE", "PAWN"));
        return new BoardDto(board);
    }

    @Override
    public void updatePiecePosition(CommandDto commandDto) {
        String from = commandDto.getFrom();
        String to = commandDto.getTo();

        board.put(to, board.get(from));
        board.remove(from);
    }
}
