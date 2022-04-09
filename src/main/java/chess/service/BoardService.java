package chess.service;

import chess.dao.BoardDao;
import chess.domain.game.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.dto.BoardDto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardService {

    private BoardDao boardDao;

    public BoardService(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    public void saveBoard(ChessGame chessGame, int gameId) {
        Map<String, Piece> model = chessGame.getChessBoard().toMap();
        List<BoardDto> boardDtos = new ArrayList<>();

        for (String key : model.keySet()) {
            Piece piece = model.get(key);
            String symbol = piece.getSymbol().name();
            String color = piece.getColor().name();
            BoardDto boardDto = new BoardDto(key, symbol, color);
            boardDtos.add(boardDto);
        }
        boardDao.save(boardDtos, gameId);
    }

    public Map<String, Piece> findBoard() {
        Map<String, Piece> model = new HashMap<>();

        List<BoardDto> boardDtos = boardDao.findAll();

        for (BoardDto boardDto : boardDtos) {
            String position = boardDto.getPosition();
            String color = boardDto.getColor();
            String symbol = boardDto.getSymbol();
            model.put(position, Piece.of(color, symbol));
        }

        return model;
    }

    public Map<Position, Piece> loadBoard() {
        Map<Position, Piece> pieces = new HashMap<>();

        List<BoardDto> boardDtos = boardDao.findAll();

        for (BoardDto boardDto : boardDtos) {
            String positionName = boardDto.getPosition();
            String symbol = boardDto.getSymbol();
            String color = boardDto.getColor();
            Position position = Position.of(positionName);
            pieces.put(position, Piece.of(color, symbol));
        }
        return pieces;
    }

    public void update(ChessGame chessGame, String position) {
        Map<String, Piece> model = chessGame.getChessBoard().toMap();

        Piece piece = model.get(position);
        String symbol = piece.getSymbol().name();
        String color = piece.getColor().name();
        BoardDto boardDto = new BoardDto(position, symbol, color);
        boardDao.update(boardDto);
    }
}
