package chess.serivce.chess;

import chess.domain.board.Board;
import chess.domain.dto.PieceDto;
import chess.domain.dto.RoomDto;
import chess.domain.game.Room;
import chess.domain.gamestate.State;

import chess.domain.gamestate.finished.End;
import chess.domain.gamestate.running.Start;
import chess.domain.piece.Piece;
import chess.domain.team.Team;
import chess.repository.piece.JdbcPieceRepository;
import chess.repository.piece.PieceRepository;
import chess.repository.room.JdbcRoomRepository;
import chess.repository.room.RoomRepository;
import chess.utils.BoardUtil;
import chess.utils.PieceUtil;
import java.sql.SQLException;
import java.util.List;

public class ChessService {

    private final RoomRepository roomRepository = new JdbcRoomRepository();
    private final PieceRepository pieceRepository = new JdbcPieceRepository();

    public List<PieceDto> start(long roomId) throws SQLException {
        RoomDto roomDto = roomRepository.findRoomById(roomId);
        if (!roomDto.getState().equals("ready")) {
            throw new IllegalArgumentException("[ERROR] 이미 게임이 실행되었거나 종료된 방입니다. 다시 시작할 수 없습니다.");
        }

        Board newBoard = BoardUtil.generateInitialBoard();
        State start = new Start(newBoard);
        Room newRoom = new Room(start, Team.WHITE);
        roomRepository.update(roomId, roomDto.getName(), newRoom);

        for (Piece piece : newBoard.getPieces()) {
            pieceRepository.insert(roomId, piece);
        }
        return pieceRepository.findPiecesByRoomId(roomId);
    }

    public List<PieceDto> end(Long roomId) throws SQLException {
        RoomDto roomDto = roomRepository.findRoomById(roomId);
        if (!roomDto.getState().equals("move")) {
            throw new IllegalArgumentException("[ERROR] 아직 게임을 종료할 수 없습니다.");
        }

        List<PieceDto> pieces = pieceRepository.findPiecesByRoomId(roomId);
        Board board = Board.of(PieceUtil.generatePiecesByPieceDtos(pieces));
        State state = new End(board);
        Room room = new Room(state, Team.valueOf(roomDto.getCurrentTeam()));
        roomRepository.update(roomId, roomDto.getName(), room);

        return pieces;
    }
}
