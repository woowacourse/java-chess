package chess.service;

import chess.dao.RoomDAO;
import chess.domain.ChessGame;
import chess.domain.Team;
import chess.dto.PiecesDTO;
import chess.dto.RoomDTO;
import chess.dto.UsersDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class RoomService {
    private final RoomDAO roomDAO;

    public RoomService(final RoomDAO roomDAO) {
        this.roomDAO = roomDAO;
    }

    public List<RoomDTO> allRooms() throws SQLException {
        return roomDAO.allRooms();
    }

    public void createRoom(final String name) throws SQLException {
        roomDAO.createRoom(name);
    }

    public void gameInformation(final ChessGame chessGame, final Map<String, Object> model,
                                final String roomId, final UsersDTO users) {
        PiecesDTO piecesDTOs = PiecesDTO.create(chessGame.board());
        model.put("pieces", piecesDTOs.toList());
        model.put("button", "초기화");
        model.put("isWhite", Team.WHITE.equals(chessGame.turn()));
        model.put("black-score", chessGame.scoreByTeam(Team.BLACK));
        model.put("white-score", chessGame.scoreByTeam(Team.WHITE));
        model.put("number", roomId);
        model.put("users", users);
    }

    public void changeStatus(final String roomId) throws SQLException {
        roomDAO.changeStatusEndByRoomId(roomId);
    }
}
