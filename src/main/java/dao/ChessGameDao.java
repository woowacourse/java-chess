package dao;

import domain.game.GameState;
import domain.piece.Piece;
import domain.piece.Position;
import domain.piece.Side;
import dto.dao.ChessGameDaoResponseDto;

import java.util.List;
import java.util.Map;

public interface ChessGameDao {
    public Long createRoom();

    public boolean saveChessBoard(Map<Position, Piece> board, Side currentTurn, Long roomId);

    public ChessGameDaoResponseDto loadGame(Long roomId);

    public List<Long> findAllGameRooms();

    public void updateChessBoard(Long roomId, Map<Position, Piece> board);

    public void updateGameRoom(Long roomId, Side currentTurn, GameState state);

    public boolean hasGame(Long roomId);

    public void deleteGameRoom(Long roomId);
}
