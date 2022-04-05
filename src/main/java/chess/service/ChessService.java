package chess.service;

import chess.utils.PieceFactory;
import chess.dao.RoomDao;
import chess.dao.SquareDao;
import chess.domain.ChessBoard;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.dto.BoardDto;
import chess.entity.Square;
import chess.utils.JdbcTemplate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessService {

    private final RoomDao roomDao;
    private final SquareDao squareDao;

    public ChessService() {
        this.roomDao = new RoomDao(JdbcTemplate.getConnection());
        this.squareDao = new SquareDao(JdbcTemplate.getConnection());
    }

    public ChessService(RoomDao roomDao, SquareDao squareDao) {
        this.roomDao = roomDao;
        this.squareDao = squareDao;
    }

    public BoardDto start(long roomId) {
        List<Square> squares = squareDao.findByRoomId(roomId);
        Map<Position, Piece> board = new HashMap<>();
        for (Square square : squares) {
            Position position = Position.of(square.getPosition());
            Piece piece = PieceFactory.convertToPiece(square.getPiece());
            board.put(position, piece);
        }
        ChessBoard chessBoard = new ChessBoard(() -> board);
        return BoardDto.of(chessBoard.getPieces());
    }
}
