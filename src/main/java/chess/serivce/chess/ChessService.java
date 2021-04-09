package chess.serivce.chess;

import chess.domain.board.Board;
import chess.domain.dto.PieceDto;
import chess.domain.dto.move.MoveResponseDto;
import chess.domain.game.Room;
import chess.domain.gamestate.running.Ready;
import chess.domain.location.Location;
import chess.domain.piece.Piece;
import chess.domain.team.Team;
import chess.repository.piece.JdbcPieceRepository;
import chess.repository.piece.PieceRepository;
import chess.repository.room.JdbcRoomRepository;
import chess.repository.room.RoomRepository;
import chess.utils.BoardUtil;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class ChessService {

    private final RoomRepository roomRepository = new JdbcRoomRepository();
    private final PieceRepository pieceRepository = new JdbcPieceRepository();

    public MoveResponseDto start(String roomName) throws SQLException {
        Room room = roomRepository.findRoomByRoomName(roomName);

        room.play("start");
        roomRepository.update(room);

        Board board = room.getBoard();
        for (Piece piece : board.getPieces()) {
            pieceRepository.insert(room.getId(), piece);
        }

        List<PieceDto> pieceDtos = board.getPieces()
            .stream()
            .map(piece -> PieceDto.from(piece))
            .collect(Collectors.toList());
        return new MoveResponseDto(
            pieceDtos,
            room.getCurrentTeam().getValue(),
            room.judgeResult()
        );
    }

    public MoveResponseDto end(String roomName) throws SQLException {
        Room room = roomRepository.findRoomByRoomName(roomName);

        room.play("end");
        roomRepository.update(room);

        Board board = room.getBoard();
        List<PieceDto> pieceDtos = board.getPieces()
            .stream()
            .map(piece -> PieceDto.from(piece))
            .collect(Collectors.toList());
        return new MoveResponseDto(
            pieceDtos,
            room.getCurrentTeam().getValue(),
            room.judgeResult()
        );
    }

    public MoveResponseDto move(String roomName, String source, String target) throws SQLException {
        Room room = roomRepository.findRoomByRoomName(roomName);
        Board board = room.getBoard();
        Piece sourcePiece = board.find(Location.of(source));
        List<Piece> beforeMovePieces = board.getPieces();

        room.play("move " + source + " " + target);
        roomRepository.update(room);
        List<Piece> afterMovePieces = board.getPieces();

        if (beforeMovePieces.size() != afterMovePieces.size()) {
            Piece removedPiece = beforeMovePieces
                .stream()
                .filter(piece -> piece.getLocation().equals(Location.of(target)))
                .filter(piece -> !piece.equals(sourcePiece))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 기물입니다."));
            pieceRepository.deletePieceById(removedPiece.getId());
        }

        for (Piece piece : board.getPieces()) {
            pieceRepository.update(piece);
        }

        List<PieceDto> pieceDtos = board.getPieces()
            .stream()
            .map(piece -> PieceDto.from(piece))
            .collect(Collectors.toList());
        return new MoveResponseDto(
            pieceDtos,
            room.getCurrentTeam().getValue(),
            room.judgeResult()
        );
    }

    public MoveResponseDto findPiecesByRoomName(String roomName) throws SQLException {
        Room room = roomRepository.findRoomByRoomName(roomName);

        List<PieceDto> pieceDtos = pieceRepository.findPiecesByRoomId(room.getId())
            .stream()
            .map(piece -> PieceDto.from(piece))
            .collect(Collectors.toList());

        return new MoveResponseDto(
            pieceDtos,
            room.getCurrentTeam().getValue(),
            room.judgeResult()
        );
    }

    public void createRoom(String roomName) throws SQLException {
        if (!roomRepository.isExistRoomName(roomName)) {
            throw new IllegalArgumentException("[ERROR] 이미 존재하는 방입니다. 다른 이름을 사용해주세요.");
        }

        roomRepository.insert(new Room(0, roomName, new Ready(BoardUtil.generateInitialBoard()), Team.WHITE));
    }
}
