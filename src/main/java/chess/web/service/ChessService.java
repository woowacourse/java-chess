package chess.web.service;

import static java.util.stream.Collectors.toMap;

import chess.model.ChessGame;
import chess.model.board.Board;
import chess.model.board.ChessInitializer;
import chess.model.board.Square;
import chess.model.piece.Piece;
import chess.model.piece.PieceLetter;
import chess.model.status.Playing;
import chess.web.dao.RuntimeChessGameDao;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class ChessService {
    private final RuntimeChessGameDao dao;

    public ChessService(RuntimeChessGameDao dao) {
        this.dao = dao;
    }

    public void initBoard() {
        ChessGame chessGame = new ChessGame(new ChessInitializer(), new Playing());
        Map<Square, Piece> board = chessGame.getBoard().getBoard();
        dao.saveAll(board, chessGame.getTurn(), new Playing());
    }

    private PieceDto toDto(Piece piece) {
        String pieceName = PieceLetter.getName(piece);
        return new PieceDto(pieceName, piece.getColor().name());
    }

    public BoardDto getBoard() {
        Map<Square, Piece> allPieces = dao.getAllPieces();
        return new BoardDto(allPieces.keySet().stream()
                .collect(toMap(Square::getName, key -> toDto(allPieces.get(key)))));
    }

    public void move(String from, String to) {
        Square fromSquare = Square.of(from);
        Square toSquare = Square.of(to);
        ChessGame chessGame = getChessGameFromDao();
        chessGame.move(fromSquare, toSquare);
        dao.saveAll(chessGame.getBoard().getBoard(), chessGame.getTurn(), chessGame.getStatus());
    }

    private ChessGame getChessGameFromDao() {
        return new ChessGame(new Board(dao.getAllPieces()), dao.getTurn(), dao.getStatus());
    }

    public Map<String, Double> getScores() {
        return getChessGameFromDao().getPlayersScore().entrySet()
                .stream()
                .collect(Collectors.toMap(entry -> entry.getKey().name(), Entry::getValue));
    }
}
