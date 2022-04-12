package chess;

import chess.dao.BoardDao;
import chess.dao.MemberDao;
import chess.dao.PieceDao;
import chess.dao.RoomDao;
import chess.dao.SquareDao;
import chess.dto.BoardDto;
import chess.dto.RoomsDto;
import chess.dto.RoomDto;
import chess.dto.ScoreDto;
import chess.model.Board;
import chess.model.piece.Color;
import chess.model.piece.Empty;
import chess.model.piece.Initializer;
import chess.model.piece.Piece;
import chess.model.square.File;
import chess.model.square.Square;
import chess.model.status.Running;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessService {

    private static final int PROPER_KING_COUNT = 2;

    private final BoardDao<Board> chessBoardDao;
    private final SquareDao<Square> chessSquareDao;
    private final PieceDao<Piece> chessPieceDao;
    private final RoomDao<Room> chessRoomDao;
    private final MemberDao<Member> chessMemberDao;

    public ChessService(
            BoardDao<Board> chessBoardDao,
            SquareDao<Square> chessSquareDao,
            PieceDao<Piece> chessPieceDao,
            RoomDao<Room> chessRoomDao,
            MemberDao<Member> chessMemberDao) {
        this.chessBoardDao = chessBoardDao;
        this.chessSquareDao = chessSquareDao;
        this.chessPieceDao = chessPieceDao;
        this.chessRoomDao = chessRoomDao;
        this.chessMemberDao = chessMemberDao;
    }

    public Room init(String roomTitle, String member1, String member2) {
        Board board = chessBoardDao.init(new Board(new Running()), Initializer.initialize());
        Room room = chessRoomDao.save(new Room(roomTitle, board.getId()));
        chessMemberDao.saveAll(List.of(new Member(member1), new Member(member2)), room.getId());
        return room;
    }

    public RoomsDto getRooms() {
        List<RoomDto> roomsDto = new ArrayList<>();
        List<Room> rooms = chessRoomDao.findAllWithRunning();
        for (Room room : rooms) {
            List<Member> membersByRoom = chessMemberDao.getAllByRoomId(room.getId());
            roomsDto.add(
                    new RoomDto(room.getId(), room.getTitle(), membersByRoom));
        }
        return new RoomsDto(roomsDto);
    }

    public BoardDto getBoard(int roomId) {
        final Room room = chessRoomDao.getById(roomId);
        final Board board = chessBoardDao.getById(room.getBoardId());
        final Map<Square, Piece> allPositionsAndPieces = chessSquareDao.findAllSquaresAndPieces(board.getId());
        List<Member> members = chessMemberDao.getAllByRoomId(roomId);
        return BoardDto.of(
                allPositionsAndPieces,
                room.getTitle(),
                members.get(0),
                members.get(1));
    }

    public void move(String source, String target, int roomId) {
        Room room = chessRoomDao.getById(roomId);
        Square sourceSquare = chessSquareDao.getBySquareAndBoardId(Square.fromString(source), room.getBoardId());
        Square targetSquare = chessSquareDao.getBySquareAndBoardId(Square.fromString(target), room.getBoardId());
        Piece piece = chessPieceDao.findBySquareId(sourceSquare.getId());
        checkMovable(sourceSquare, targetSquare, piece, room.getBoardId());
        chessPieceDao.deletePieceBySquareId(targetSquare.getId());
        chessPieceDao.updatePieceSquareId(sourceSquare.getId(), targetSquare.getId());
        chessPieceDao.save(new Empty(), sourceSquare.getId());
        checkKingDead(room.getBoardId());
    }

    private void checkKingDead(int boardId) {
        long kingCount = chessPieceDao.getAllPiecesByBoardId(boardId).stream()
                .filter(Piece::isKing)
                .count();
        if (kingCount != PROPER_KING_COUNT) {
            chessBoardDao.finishGame(boardId);
        }
    }

    private void checkMovable(Square sourceSquare, Square targetSquare, Piece piece, int boardId) {
        Piece targetPiece = chessPieceDao.findBySquareId(targetSquare.getId());
        if (!piece.movable(targetPiece, sourceSquare, targetSquare)) {
            throw new IllegalArgumentException("해당 위치로 움직일 수 없습니다.");
        }
        List<Square> route = piece.getRoute(sourceSquare, targetSquare);

        if (piece.isPawn() && !route.isEmpty() && !piece.isNotAlly(targetPiece)) {
            throw new IllegalArgumentException("같은 팀이 있는 곳으로 갈 수 없습니다.");
        }

        checkMoveWithoutObstacle(route, boardId, piece, targetPiece);
    }

    private void checkMoveWithoutObstacle(List<Square> route, int boardId, Piece sourcePiece, Piece targetPiece) {
        List<Square> realSquares = chessSquareDao.getPaths(route, boardId);
        for (Square square : realSquares) {
            Piece piece = chessPieceDao.findBySquareId(square.getId());
            if (piece.equals(targetPiece) && sourcePiece.isNotAlly(targetPiece)) {
                return;
            }
            if (piece.isNotEmpty()) {
                throw new IllegalArgumentException("경로 중 기물이 있습니다.");
            }
        }
    }

    public boolean isEnd(int roomId) {
        Room room = chessRoomDao.getById(roomId);
        return chessBoardDao.isEnd(room.getBoardId());
    }

    public ScoreDto status(int roomId) {
        Map<String, Double> statusDto = Color.getPlayerColors().stream()
                .collect(Collectors.toMap(Enum::name, color -> calculateScore(roomId, color)));
        return ScoreDto.of(statusDto);
    }

    public double calculateScore(int roomId, final Color color) {
        return calculateDefaultScore(roomId, color) - 0.5 * countPawnsOnSameColumns(roomId, color);
    }

    private double calculateDefaultScore(int roomId, Color color) {
        return existPieces(roomId)
                .stream()
                .filter(piece -> piece.isSameColor(color))
                .mapToDouble(Piece::getPoint)
                .sum();
    }

    public List<Piece> existPieces(int roomId) {
        return chessPieceDao.getAllPiecesByBoardId(roomId);
    }

    private int countPawnsOnSameColumns(int roomId, final Color color) {
        return Arrays.stream(File.values())
                .mapToInt(file -> chessPieceDao.countPawnsOnSameColumn(roomId, file, color))
                .filter(count -> count > 1)
                .sum();
    }

    public void end(int roomId) {
        Room room = chessRoomDao.getById(roomId);
        chessBoardDao.finishGame(room.getBoardId());
    }
}
