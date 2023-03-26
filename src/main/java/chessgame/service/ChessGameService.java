package chessgame.service;

import chessgame.dao.GameRoomDao;
import chessgame.dao.PiecesDao;
import chessgame.domain.chessgame.Camp;
import chessgame.domain.chessgame.ChessGame;
import chessgame.domain.coordinate.Coordinate;
import chessgame.domain.piece.Piece;
import chessgame.domain.piece.PieceType;
import chessgame.dto.GameRoomDto;
import chessgame.dto.PieceDto;

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

        Map<Coordinate, Piece> pieces = new HashMap<>();
        for (PieceDto pieceDto : pieceDtos) {
            Coordinate coordinate = Coordinate.fromOnBoard(pieceDto.getRank(), pieceDto.getFile());
            pieces.put(coordinate, makePiece(pieceDto));
        }
        return pieces;
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
}
