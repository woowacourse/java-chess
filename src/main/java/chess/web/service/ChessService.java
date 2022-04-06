package chess.web.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import chess.domain.game.ChessGame;
import chess.domain.game.state.ChessBoard;
import chess.domain.game.state.Player;
import chess.domain.game.state.position.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.property.Color;
import chess.web.dao.PieceDao;
import chess.web.dao.RoomDao;
import chess.web.dto.PieceDto;
import chess.web.dto.RoomDto;
import chess.web.utils.Converter;

public class ChessService {
    private ChessGame chessGame = new ChessGame();
    private final PieceDao pieceDao = new PieceDao();
    private final RoomDao roomDao = new RoomDao();

    public List<PieceDto> initializeData(int roomId) throws SQLException {
        List<PieceDto> pieces = pieceDao.findAllByRoomId(roomId);
        Player player = roomDao.findTurnById(roomId);
        chessGame = ChessGame.of(ChessBoard.of(pieces), player);
        return pieces;
    }

    public void start(int roomId) throws SQLException {
        initPieceDB(roomId);
        Map<Position, Piece> pieces = chessGame.start();
        pieceDao.saveAll(extractPieceDtos(pieces), roomId);
        roomDao.updateById(Player.White, roomId);
    }

    private void initPieceDB(int roomId) throws SQLException {
        pieceDao.removeAllByRoomId(roomId);
    }

    private List<PieceDto> extractPieceDtos(Map<Position, Piece> board) {
        List<PieceDto> pieceDtos = new ArrayList<>();
        for (Position position : board.keySet()) {
            String positionStr = position.getFile().name() + position.getRank().getValue();
            pieceDtos.add(PieceDto.of(positionStr, board.get(position)));
        }
        return pieceDtos;
    }

    public Map<Color, Double> getStatus() {
        return chessGame.getState().status();
    }

    public void move(String command, int roomId) throws SQLException {
        Map<Position, Piece> pieces = moveByCommand(command);
        pieceDao.update(extractPieceDtos(pieces), roomId);
        Player player = roomDao.findTurnById(roomId);
        roomDao.updateById(player.change(), roomId);
    }

    private Map<Position, Piece> moveByCommand(String command) {
        String[] positions = command.split(",");
        Position from = getPositionFrom(positions[0]);
        Position to = getPositionFrom(positions[1]);
        return chessGame.move(from, to);
    }

    private static Position getPositionFrom(String position) {
        return Converter.positionFrom(position);
    }

    public void saveRoom(String name) throws SQLException {
        roomDao.save(new RoomDto(name, Player.White));
    }

    public List<RoomDto> findAllRoom() throws SQLException {
        return roomDao.findAll();
    }
}
