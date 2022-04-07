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

    private static final String NO_ROOM_MESSAGE = "해당 ID와 일치하는 Room이 존재하지 않습니다.";
    private static final String NO_SQUARE_MESSAGE = "해당 방, 위치에 존재하는 Square가 없습니다.";
    public static final String NO_SQUARES_MESSAGE = "해당 ID에 체스게임이 초기화되지 않았습니다.";

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
        Room room = roomDao.findById(roomId)
                .orElseThrow(() -> new IllegalStateException(NO_ROOM_MESSAGE));

        WebChessGame webChessGame = new WebChessGame();
        webChessGame.start();
        squareDao.removeAll(roomId);
        Map<Position, Piece> board = webChessGame.getBoard();
        List<Square> squares = convertBoardToSquares(board);
        squareDao.saveAll(squares, roomId);
        roomDao.update(roomId, webChessGame.getTurn());
        return BoardDto.of(board, webChessGame.getTurn());
    }

    public BoardDto load(long roomId) {
        Room room = roomDao.findById(roomId)
                .orElseThrow(() -> new IllegalStateException(NO_ROOM_MESSAGE));
        ChessBoard chessBoard = loadChessBoard(roomId);

        return BoardDto.of(chessBoard.getPieces(), room.getTurn());
    }

    public BoardDto move(long roomId, MoveDto moveDto) {
        Room room = roomDao.findById(roomId)
                .orElseThrow(() -> new IllegalStateException(NO_ROOM_MESSAGE));
        WebChessGame webChessGame = WebChessGame.of(loadChessBoard(roomId), room.getTurn());
        webChessGame.move(moveDto.getFrom(), moveDto.getTo());
        roomDao.update(roomId, webChessGame.getTurn());
        updateMovement(roomId, moveDto);

        return BoardDto.of(webChessGame.getBoard(), webChessGame.getTurn());
    }

    private void updateMovement(long roomId, MoveDto moveDto) {
        String fromPiece = squareDao.findByRoomIdAndPosition(roomId, moveDto.getFrom())
                .orElseThrow(() -> new IllegalStateException(NO_SQUARE_MESSAGE))
                .getPiece();
        squareDao.update(roomId, moveDto.getFrom(), "empty");
        squareDao.update(roomId, moveDto.getTo(), fromPiece);
    }

    private List<Square> convertBoardToSquares(Map<Position, Piece> board) {
        return board.keySet().stream()
                .map(position -> new Square(position.convertToString(), board.get(position).convertToString()))
                .collect(Collectors.toList());
    }

    private ChessBoard loadChessBoard(long roomId) {
        List<Square> squares = squareDao.findByRoomId(roomId);
        if (squares.isEmpty()) {
            throw new IllegalStateException(NO_SQUARES_MESSAGE);
        }
        Map<Position, Piece> board = new HashMap<>();
        for (Square square : squares) {
            Position position = Position.of(square.getPosition());
            Piece piece = PieceFactory.convertToPiece(square.getPiece());
            board.put(position, piece);
        }
        return new ChessBoard(() -> board);
    }

    public Status status(long roomId) {
        Room room = roomDao.findById(roomId)
                .orElseThrow(() -> new IllegalStateException(NO_ROOM_MESSAGE));
        ChessBoard chessBoard = loadChessBoard(roomId);
        WebChessGame webChessGame = WebChessGame.of(chessBoard, room.getTurn());

        return webChessGame.status();
    }
}
