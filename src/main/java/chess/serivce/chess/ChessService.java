package chess.serivce.chess;

import chess.domain.board.Board;
import chess.domain.dto.PieceDto;
import chess.domain.dto.RoomDto;
import chess.domain.game.Room;
import chess.domain.gamestate.State;

import chess.domain.gamestate.finished.End;
import chess.domain.piece.Piece;
import chess.domain.team.Team;
import chess.repository.piece.JdbcPieceRepository;
import chess.repository.piece.PieceRepository;
import chess.repository.room.JdbcRoomRepository;
import chess.repository.room.RoomRepository;
import chess.utils.PieceUtil;
import chess.utils.StateUtil;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class ChessService {

    private final RoomRepository roomRepository = new JdbcRoomRepository();
    private final PieceRepository pieceRepository = new JdbcPieceRepository();

    public List<PieceDto> start(long roomId) throws SQLException {
        RoomDto roomDto = roomRepository.findRoomById(roomId);

        Room room = generateRoomDomainFromDtos(roomDto, Collections.emptyList());
        room.play("start");
        roomRepository.update(roomId, roomDto.getName(), room);

        Board board = room.state().getBoard();
        for (Piece piece : board.getPieces()) {
            pieceRepository.insert(roomId, piece);
        }
        return pieceRepository.findPiecesByRoomId(roomId);
    }

    public List<PieceDto> end(Long roomId) throws SQLException {
        RoomDto roomDto = roomRepository.findRoomById(roomId);
        List<PieceDto> pieces = pieceRepository.findPiecesByRoomId(roomId);

        Room room = generateRoomDomainFromDtos(roomDto, pieces);
        room.play("end");
        roomRepository.update(roomId, roomDto.getName(), room);

        return pieces;
    }

    public List<PieceDto> move(Long roomId, String source, String target) throws SQLException {
        RoomDto roomDto = roomRepository.findRoomById(roomId);

        List<PieceDto> pieces = pieceRepository.findPiecesByRoomId(roomId);
        Board board = Board.of(PieceUtil.generatePiecesByPieceDtos(pieces));
        return null;
    }

    private Room generateRoomDomainFromDtos(RoomDto roomDto, List<PieceDto> pieceDtos) {
        Board board = Board.of(PieceUtil.generatePiecesByPieceDtos(pieceDtos));
        State state = StateUtil.generateState(roomDto.getState(), board);
        Room room = new Room(state, Team.of(roomDto.getCurrentTeam()));
        return room;
    }
}
