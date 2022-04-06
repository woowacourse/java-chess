package chess.service;

import chess.dao.RoomDao;
import chess.dao.SquareDao;
import chess.domain.ChessBoard;
import chess.domain.Status;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.dto.BoardDto;
import chess.dto.MoveDto;
import chess.entity.Room;
import chess.entity.Square;
import chess.game.WebChessGame;
import chess.utils.JdbcTemplate;
import chess.utils.PieceFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public BoardDto startNewGame(long roomId) {
        WebChessGame webChessGame = new WebChessGame();
        webChessGame.start();
        squareDao.removeAll(roomId);
        Map<Position, Piece> board = webChessGame.getBoard();
        List<Square> squares = convertBoardToSquares(board);
        squareDao.saveAll(squares, roomId);
        roomDao.update(roomId, webChessGame.getTurn());

        return BoardDto.of(board, webChessGame.getTurn());
    }

    private List<Square> convertBoardToSquares(Map<Position, Piece> board) {
        return board.keySet().stream()
                .map(position -> new Square(position.convertToString(), board.get(position).convertToString()))
                .collect(Collectors.toList());
    }

    public BoardDto move(long roomId, MoveDto moveDto) {
        Room room = roomDao.findById(roomId);
        WebChessGame webChessGame = WebChessGame.of(loadChessBoard(roomId), room.getTurn());
        webChessGame.move(moveDto.getFrom(), moveDto.getTo());
        roomDao.update(roomId, webChessGame.getTurn());
        updateMovement(roomId, moveDto);

        return BoardDto.of(webChessGame.getBoard(), webChessGame.getTurn());
    }

    private void updateMovement(long roomId, MoveDto moveDto) {
        String fromPiece = squareDao.findByRoomIdAndPosition(roomId, moveDto.getFrom()).getPiece();
        squareDao.update(roomId, moveDto.getFrom(), "empty");
        squareDao.update(roomId, moveDto.getTo(), fromPiece);
    }

    private ChessBoard loadChessBoard(long roomId) {
        List<Square> squares = squareDao.findByRoomId(roomId);
        Map<Position, Piece> board = new HashMap<>();
        for (Square square : squares) {
            Position position = Position.of(square.getPosition());
            Piece piece = PieceFactory.convertToPiece(square.getPiece());
            board.put(position, piece);
        }
        return new ChessBoard(() -> board);
    }

    public Status status(long roomId) {
        Room room = roomDao.findById(roomId);
        ChessBoard chessBoard = loadChessBoard(roomId);
        WebChessGame webChessGame = WebChessGame.of(chessBoard, room.getTurn());

        return webChessGame.status();
    }
}
