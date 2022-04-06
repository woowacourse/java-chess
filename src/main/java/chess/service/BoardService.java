package chess.service;

import chess.dao.BoardDao;
import chess.domain.board.Board;
import chess.domain.board.coordinate.Coordinate;
import chess.domain.piece.Piece;
import chess.dto.BoardDto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardService {

    private final BoardDao chessDao;

    public BoardService(BoardDao chessDao) {
        this.chessDao = chessDao;
    }

    public Board findBoard(int gameId) {
        Map<Coordinate, Piece> board = new HashMap<>();
        List<BoardDto> boardDtos = chessDao.findByGameId(gameId);
        for (BoardDto boardDto : boardDtos) {
            board.put(Coordinate.of(boardDto.getPosition()), Piece.of(boardDto.getSymbol(), boardDto.getTeam()));
        }
        return new Board(board);
    }

    public void save(Map<String, Piece> board, int gameId) {
        List<BoardDto> boardDtos = new ArrayList<>();

        for (String key : board.keySet()) {
            Piece piece = board.get(key);
            boardDtos.add(new BoardDto(piece.getSymbol(), piece.getTeam(), key));
        }

        chessDao.save(boardDtos, gameId);
    }

    public void update(Piece piece, String position, int gameId) {
        BoardDto boardDto = new BoardDto(piece.getSymbol(), piece.getTeam(), position);
        chessDao.update(boardDto, gameId);
    }
}
