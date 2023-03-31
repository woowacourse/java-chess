package dao;

import domain.game.GameState;
import domain.piece.Piece;
import domain.piece.Position;
import domain.piece.Side;
import dto.dao.ChessGameDaoResponseDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessGameDaoFake implements ChessGameDao {
    private final Map<Long, GameRoomDto> gameRoom = new HashMap<>();
    private final Map<Long, List<PieceDto>> chessBoard = new HashMap<>();
    private Long gameRoomPrimaryKey = 0L;

    @Override
    public Long createRoom() {
        gameRoomPrimaryKey += 1;
        gameRoom.put(gameRoomPrimaryKey, new GameRoomDto(GameState.RUN, Side.WHITE));
        return gameRoomPrimaryKey;
    }

    @Override
    public boolean saveChessBoard(Map<Position, Piece> board, Side currentTurn, Long roomId) {
        if (!gameRoom.containsKey(roomId)) {
            throw new IllegalArgumentException();
        }
        gameRoom.put(roomId, new GameRoomDto(GameState.RUN, currentTurn));
        List<PieceDto> pieces = new ArrayList<>();
        for (Map.Entry<Position, Piece> positionPieceEntry : board.entrySet()) {
            Position position = positionPieceEntry.getKey();
            Piece piece = positionPieceEntry.getValue();
            pieces.add(new PieceDto(position, piece));
        }
        chessBoard.put(roomId, pieces);
        return true;
    }

    @Override
    public ChessGameDaoResponseDto loadGame(Long roomId) {
        GameRoomDto gameRoomDto = gameRoom.get(roomId);
        Map<Position, Piece> board = new HashMap<>();
        List<PieceDto> pieces = chessBoard.get(roomId);
        for (PieceDto piece : pieces) {
            board.put(piece.getPosition(), piece.getPiece());
        }
        return new ChessGameDaoResponseDto(board, gameRoomDto.getCurrentTurn(), gameRoomDto.getGameState());
    }

    @Override
    public List<Long> findAllGameRooms() {
        List<Long> roomIds = new ArrayList<>();
        roomIds.addAll(gameRoom.keySet());
        return roomIds;
    }

    @Override
    public void updateChessBoard(Long roomId, Map<Position, Piece> board) {
        if (!gameRoom.containsKey(roomId)) {
            throw new IllegalArgumentException();
        }
        List<PieceDto> pieces = new ArrayList<>();
        for (Map.Entry<Position, Piece> positionPieceEntry : board.entrySet()) {
            Position position = positionPieceEntry.getKey();
            Piece piece = positionPieceEntry.getValue();
            pieces.add(new PieceDto(position, piece));
        }
        chessBoard.put(roomId, pieces);
    }

    @Override
    public void updateGameRoom(Long roomId, Side currentTurn, GameState state) {
        if (!gameRoom.containsKey(roomId)) {
            throw new IllegalArgumentException();
        }
        GameRoomDto gameRoomDto = new GameRoomDto(state, currentTurn);
        gameRoom.put(roomId, gameRoomDto);
    }

    @Override
    public boolean hasGame(Long roomId) {
        return gameRoom.containsKey(roomId);
    }

    @Override
    public void deleteGameRoom(Long roomId) {
        if (!gameRoom.containsKey(roomId)) {
            throw new IllegalArgumentException();
        }
        gameRoom.remove(roomId);
        chessBoard.remove(roomId);
    }

    class GameRoomDto {
        private final GameState gameState;
        private final Side currentTurn;

        public GameRoomDto(GameState gameState, Side currentTurn) {
            this.gameState = gameState;
            this.currentTurn = currentTurn;
        }

        public GameState getGameState() {
            return gameState;
        }

        public Side getCurrentTurn() {
            return currentTurn;
        }
    }

    class PieceDto {
        private final Position position;
        private final Piece piece;

        public PieceDto(Position position, Piece piece) {
            this.position = position;
            this.piece = piece;
        }

        public Position getPosition() {
            return position;
        }

        public Piece getPiece() {
            return piece;
        }
    }
}
