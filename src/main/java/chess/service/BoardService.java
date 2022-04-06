package chess.service;

import chess.dao.BoardDao;
import chess.domain.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.dto.BoardDto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class BoardService {

    private BoardDao boardDao;

    public BoardService(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    public void saveBoard(ChessGame chessGame, int gameId) {
        Map<String, Piece> mapper = chessGame.getChessBoard().toMap();
        List<BoardDto> boardDtos = new ArrayList<>();

        for (String key : mapper.keySet()) {
            Piece piece = mapper.get(key);
            String symbol = piece.getSymbol().name().toLowerCase(Locale.ROOT);
            String color = piece.getColor().name().toLowerCase(Locale.ROOT);
            BoardDto boardDto = new BoardDto(key, symbol, color);
            boardDtos.add(boardDto);
        }
        boardDao.save(boardDtos, gameId);
    }

    public Map<String, Piece> findBoard() {
        Map<String, Piece> mapper = new HashMap<>();

        List<BoardDto> boardDtos = boardDao.findAll();

        for (BoardDto boardDto : boardDtos) {
            String position = boardDto.getPosition();
            String color = boardDto.getColor();
            String symbol = boardDto.getSymbol();
            mapper.put(position, Piece.of(color, symbol));
        }

        return mapper;
    }

    public Map<Position, Piece> loadBoard() {
        Map<Position, Piece> pieces = new HashMap<>();

        List<BoardDto> boardDtos = boardDao.findAll();

        for (BoardDto boardDto : boardDtos) {
            String position = boardDto.getPosition();
            String symbol = boardDto.getSymbol();
            String color = boardDto.getColor();
            Position position1 = Position.of(position);
            pieces.put(position1, Piece.of(color, symbol));
        }
        return pieces;
    }

    public void update(ChessGame chessGame, String position) {
        Map<String, Piece> mapper = chessGame.getChessBoard().toMap();

        Piece piece = mapper.get(position);
        String symbol = piece.getSymbol().name().toLowerCase(Locale.ROOT);
        String color = piece.getColor().name().toLowerCase(Locale.ROOT);
        BoardDto boardDto = new BoardDto(position, symbol, color);
        boardDao.update(boardDto);
    }
}
