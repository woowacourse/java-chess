package chessgame.service;

import chessgame.domain.chessgame.Camp;
import chessgame.domain.chessgame.ChessGame;
import chessgame.domain.coordinate.Coordinate;
import chessgame.domain.piece.Piece;
import chessgame.domain.piece.PieceType;
import chessgame.dto.GameRoomDto;
import chessgame.dto.PieceDto;
import chessgame.repository.GameRoomDao;
import chessgame.repository.PiecesDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessGameService {

    private final GameRoomDao gameRoomDao = new GameRoomDao();
    private final PiecesDao piecesDao = new PiecesDao();

    public void addNewGame(Map<Coordinate, Piece> board, Camp camp) {
        gameRoomDao.addRoom(camp);
        GameRoomDto leastGameRoom = gameRoomDao.findLeastGameRoom();

        board.forEach((coordinate, piece) ->
                piecesDao.addPiece(leastGameRoom.getRoomId(), coordinate, piece));
    }

    public List<GameRoomDto> findAllGameRoom() {
        return gameRoomDao.findAllGameRoom();
    }

    public GameRoomDto findGameRoomById(long roomId) {
        return gameRoomDao.findGameRoomById(roomId);
    }

    public GameRoomDto findLeastPieces() {
        return gameRoomDao.findLeastGameRoom();
    }

    public Map<Coordinate, Piece> findPiecesByRoomId(long roomId) {
        List<PieceDto> pieceDtos = piecesDao.findPiecesByRoomId(roomId);
        validatePiecesSize(pieceDtos.size(), roomId);

        Map<Coordinate, Piece> pieces = new HashMap<>();
        for (PieceDto pieceDto : pieceDtos) {
            Coordinate coordinate = Coordinate.createOnBoard(pieceDto.getRank(), pieceDto.getFile());
            pieces.put(coordinate, makePiece(pieceDto));
        }
        return pieces;
    }

    private void validatePiecesSize(int size, long roomId) {
        if (size != 64) {
            deleteGame(roomId);
            throw new IllegalArgumentException("[ERROR] 기물 데이터 오류로 불러올 수 없습니다. 해당 방은 삭제 됩니다.");
        }
    }

    private Piece makePiece(PieceDto pieceDto) {
        String camp = pieceDto.getCamp();
        String type = pieceDto.getType();

        Camp pieceCamp = Camp.valueOf(camp);
        return PieceType.valueOf(type)
                        .createPiece(pieceCamp);
    }

    public void updateGame(ChessGame chessGame) {
        long roomId = chessGame.getRoomId();
        Map<Coordinate, Piece> board = chessGame.getBoard()
                                                .getBoard();

        gameRoomDao.updateGameRoomById(roomId, chessGame.getTurn());
        board.forEach((coordinate, piece) -> piecesDao.updatePieceByCoordinate(roomId, coordinate, piece));
    }

    public void deleteGame(ChessGame chessGame) {
        long roomId = chessGame.getRoomId();

        piecesDao.deletePiecesByRoomId(roomId);
        gameRoomDao.deleteGameRoomById(roomId);
    }

    public void deleteGame(long roomId) {
        piecesDao.deletePiecesByRoomId(roomId);
        gameRoomDao.deleteGameRoomById(roomId);
    }
}
