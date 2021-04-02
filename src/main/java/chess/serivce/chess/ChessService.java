package chess.serivce.chess;

import chess.domain.board.Board;
import chess.domain.dto.PieceDto;
import chess.domain.dto.RoomDto;
import chess.domain.dto.ScoreDto;
import chess.domain.dto.move.MoveResponseDto;
import chess.domain.game.Room;
import chess.domain.gamestate.State;

import chess.domain.gamestate.running.Ready;
import chess.domain.location.Location;
import chess.domain.piece.Piece;
import chess.domain.team.Team;
import chess.repository.piece.JdbcPieceRepository;
import chess.repository.piece.PieceRepository;
import chess.repository.room.JdbcRoomRepository;
import chess.repository.room.RoomRepository;
import chess.utils.BoardUtil;
import chess.utils.PieceUtil;
import chess.utils.StateUtil;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ChessService {

    private final RoomRepository roomRepository = new JdbcRoomRepository();
    private final PieceRepository pieceRepository = new JdbcPieceRepository();

    public MoveResponseDto start(String roomName) throws SQLException {
        RoomDto roomDto = roomRepository.findRoomByRoomName(roomName);

        Room room = generateRoomDomainFromDB(roomDto, Collections.emptyList());
        room.play("start");
        roomRepository.update(roomDto.getId(), roomDto.getName(), room);

        Board board = room.state().getBoard();
        for (Piece piece : board.getPieces()) {
            pieceRepository.insert(roomDto.getId(), piece);
        }

        return new MoveResponseDto(
            pieceRepository.findPiecesByRoomId(roomDto.getId()),
            room.currentTeam().getValue(),
            room.judgeResult()
        );
    }

    public MoveResponseDto end(String roomName) throws SQLException {
        RoomDto roomDto = roomRepository.findRoomByRoomName(roomName);
        List<PieceDto> pieces = pieceRepository.findPiecesByRoomId(roomDto.getId());

        Room room = generateRoomDomainFromDB(roomDto, pieces);
        room.play("end");
        roomRepository.update(roomDto.getId(), roomDto.getName(), room);

        return new MoveResponseDto(
            pieceRepository.findPiecesByRoomId(roomDto.getId()),
            room.currentTeam().getValue(),
            room.judgeResult()
        );
    }

    public MoveResponseDto move(String roomName, String source, String target) throws SQLException {
        RoomDto roomDto = roomRepository.findRoomByRoomName(roomName);
        List<PieceDto> piecesInDb = pieceRepository.findPiecesByRoomId(roomDto.getId());

        Room room = generateRoomDomainFromDB(roomDto, piecesInDb);
        room.play("move " + source + " " + target);
        roomRepository.update(roomDto.getId(), roomDto.getName(), room);

        Board board = room.state().getBoard();
        List<Piece> pieces = room.state().getBoard().getPieces();

        if (pieces.size() != piecesInDb.size()) {
            PieceDto pieceInDBAtSource = piecesInDb.stream()
                .filter(pieceDto -> pieceDto.getLocation().equals(source))
                .findFirst().get();
            PieceDto pieceInDBAtTarget = piecesInDb.stream()
                .filter(pieceDto -> pieceDto.getLocation().equals(target))
                .findFirst().get();

            Piece pieceInTarget = board.find(Location.of(target));
            pieceRepository.update(pieceInDBAtSource.getId(), pieceInTarget);
            pieceRepository.deletePieceById(pieceInDBAtTarget.getId());
            return new MoveResponseDto(
                pieceRepository.findPiecesByRoomId(roomDto.getId()),
                room.currentTeam().getValue(),
                room.judgeResult()
            );
        }

        for (int i = 0; i < pieces.size(); i++) {
            pieceRepository.update(piecesInDb.get(i).getId(), pieces.get(i));
        }

        return new MoveResponseDto(
            pieceRepository.findPiecesByRoomId(roomDto.getId()),
            room.currentTeam().getValue(),
            room.judgeResult()
        );
    }

    private Room generateRoomDomainFromDB(RoomDto roomDto, List<PieceDto> pieceDtos) {
        Board board = Board.of(PieceUtil.generatePiecesByPieceDtos(pieceDtos));
        State state = StateUtil.generateState(roomDto.getState(), board);
        Room room = new Room(state, Team.of(roomDto.getCurrentTeam()));
        return room;
    }

    public MoveResponseDto findPiecesByRoomName(String roomName) throws SQLException {
        RoomDto roomDto = roomRepository.findRoomByRoomName(roomName);
        List<PieceDto> piecesInDb = pieceRepository.findPiecesByRoomId(roomDto.getId());

        Room room = generateRoomDomainFromDB(roomDto, piecesInDb);

        return new MoveResponseDto(
            piecesInDb,
            room.currentTeam().getValue(),
            room.judgeResult()
        );
    }

    public void createRoom(String roomName) throws SQLException {
        if (!roomRepository.isExistRoomName(roomName)) {
            throw new IllegalArgumentException("[ERROR] 이미 존재하는 방입니다. 다른 이름을 사용해주세요.");
        }

        int userId = 0; // TODO: 임시 아이디 (유저 기능 구현시 활용)
        Room newRoom = new Room(new Ready(BoardUtil.generateInitialBoard()), Team.WHITE);

        roomRepository.insert(userId, roomName, newRoom);
    }
}
