package chess.web.service;

import static java.util.stream.Collectors.toMap;

import chess.console.service.PieceLetter;
import chess.model.ChessGame;
import chess.model.board.Square;
import chess.model.piece.Piece;
import chess.web.dao.RuntimeChessGameDao;
import java.util.Map;

public class ChessService {
    private final RuntimeChessGameDao dao;

    public ChessService(RuntimeChessGameDao dao) {
        this.dao = dao;
    }

    public BoardDto initBoard() {
        ChessGame chessGame = new ChessGame();
        Map<Square, Piece> board = chessGame.getBoard().getBoard();
        dao.saveAll(board, chessGame.getTurn());
        Map<Square, Piece> allPieces = dao.getAllPieces();
        return new BoardDto(allPieces.keySet().stream()
                .collect(toMap(Square::getName, key -> toDto(allPieces.get(key)))));
    }

    private PieceDto toDto(Piece piece) {
        String pieceName = PieceLetter.getName(piece);
        return new PieceDto(pieceName, piece.getColor().name());
    }
}
