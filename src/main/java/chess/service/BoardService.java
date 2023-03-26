package chess.service;

import chess.domain.board.Board;
import database.dto.SquareDto;
import database.BoardDao;

import java.util.List;
import java.util.stream.Collectors;

public class BoardService {

    private final BoardDao boardDao = new BoardDao();

    public void save(Board board, int gameId) {
        List<SquareDto> squareDtos = convertSquareDto(board);
        boardDao.save(squareDtos, gameId);
    }

    public void update(Board board, int gameId) {
        List<SquareDto> squareDtos = convertSquareDto(board);
        boardDao.update(squareDtos, gameId);
    }

    private static List<SquareDto> convertSquareDto(Board board) {
        return board.getBoard().entrySet().stream()
                .map(entry -> SquareDto.of(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
}
