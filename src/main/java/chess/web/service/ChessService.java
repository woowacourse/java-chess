package chess.web.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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
    private static final int MAX_RANK = 8;
    private static final int MIN_RANK = 1;
    private static final char MIN_FILE = 'a';
    private static final char MAX_FILE = 'h';

    private ChessGame chessGame = new ChessGame();
    private final PieceDao pieceDao = new PieceDao();
    private final RoomDao roomDao = new RoomDao();

    public Map<String, Object> initializeRoomData(int roomId) throws SQLException {
        Map<String, Object> model = new HashMap<>();
        List<PieceDto> pieces = initializePieceData(roomId);
        putRowPieces(pieces, model);
        putScore(model, pieces);
        return model;
    }

    public List<PieceDto> initializePieceData(int roomId) throws SQLException {
        List<PieceDto> pieces = pieceDao.findAllByRoomId(roomId);
        Player player = roomDao.findTurnById(roomId);
        chessGame = ChessGame.of(ChessBoard.of(pieces), player);
        return pieces;
    }

    private void putRowPieces(List<PieceDto> pieces, Map<String, Object> model) {
        for (int i = MAX_RANK; i >= MIN_RANK; i--) {
            List<PieceDto> columnPieces = putColumnPieces(pieces, i);
            model.put("pieces" + i, columnPieces);
        }
    }

    private List<PieceDto> putColumnPieces(List<PieceDto> pieces, int rank) {
        List<PieceDto> columnPieces = new ArrayList<>();

        for (char currentFile = MIN_FILE; currentFile <= MAX_FILE; currentFile++) {
            String position = currentFile + String.valueOf(rank);
            PieceDto pieceDto = pieces.stream()
                .filter(piece -> piece.getPosition().equals(position))
                .findFirst()
                .orElse(PieceDto.of(position, "", "", ""));
            columnPieces.add(pieceDto);
        }
        return columnPieces;
    }

    private void putScore(Map<String, Object> model, List<PieceDto> pieces) {
        if (!pieces.isEmpty()) {
            Map<Color, Double> status = getStatus();
            model.put("blackScore", status.get(Color.Black));
            model.put("WhiteScore", status.get(Color.White));
        }
    }

    public Map<Color, Double> getStatus() {
        return chessGame.getState().status();
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
        List<PieceDto> pieces = new ArrayList<>();
        for (Position position : board.keySet()) {
            String positionName = position.getFile().name() + position.getRank().getValue();
            pieces.add(PieceDto.of(positionName, board.get(position)));
        }
        return pieces;
    }

    public void move(String command, int roomId) throws SQLException {
        moveByCommand(command);
        if (chessGame.isFinished()) {
            pieceDao.deleteByRoomId(roomId);
            return;
        }
        Player player = roomDao.findTurnById(roomId);
        roomDao.updateById(player.change(), roomId);
    }

    private void moveByCommand(String command) throws SQLException {
        String[] positions = command.split(",");
        updateChessGame(positions);
        updateDB(positions);
    }

    private void updateDB(String[] positions) throws SQLException {
        pieceDao.deleteByPosition(positions[1]);
        pieceDao.updatePosition(positions[0], positions[1]);
        pieceDao.updatePawnState(positions[1]);
    }

    private void updateChessGame(String[] positions) {
        Position from = getPositionFrom(positions[0]);
        Position to = getPositionFrom(positions[1]);
        chessGame.move(from, to);
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

    public void deleteRoom(int roomId) throws SQLException {
        roomDao.remove(roomId);
    }
}
