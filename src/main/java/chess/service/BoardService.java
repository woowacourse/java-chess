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

    private final BoardDao boardDao;

    public BoardService(final BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    public void saveBoard(final ChessGame chessGame, final int gameId) {
        final Map<String, Piece> model = chessGame.getChessBoard().toMap();
        final List<BoardDto> boardDtos = new ArrayList<>();

        for (final String position : model.keySet()) {
            final Piece piece = model.get(position);
            final String symbol = piece.getSymbol().name();
            final String color = piece.getColor().name();
            boardDtos.add(new BoardDto(position, symbol, color));
        }
        boardDao.save(boardDtos, gameId);
    }

    public Map<String, Piece> findBoard() {
        final Map<String, Piece> model = new HashMap<>();
        final List<BoardDto> boardDtos = boardDao.findAll();

        for (final BoardDto boardDto : boardDtos) {
            final String position = boardDto.getPosition();
            final String color = boardDto.getColor();
            final String symbol = boardDto.getSymbol();
            model.put(position, Piece.of(color, symbol));
        }
        return model;
    }

    public Map<Position, Piece> loadBoard() {
        final Map<Position, Piece> pieces = new HashMap<>();
        final List<BoardDto> boardDtos = boardDao.findAll();

        for (final BoardDto boardDto : boardDtos) {
            final String positionName = boardDto.getPosition();
            final String symbol = boardDto.getSymbol();
            final String color = boardDto.getColor();
            pieces.put(Position.of(positionName), Piece.of(color, symbol));
        }
        return pieces;
    }

    public void update(final ChessGame chessGame, final String position) {
        final Map<String, Piece> model = chessGame.getChessBoard().toMap();

        final Piece piece = model.get(position);
        final String symbol = piece.getSymbol().name();
        final String color = piece.getColor().name();
        BoardDto boardDto = new BoardDto(position, symbol, color);
        boardDao.update(boardDto);
    }
}
