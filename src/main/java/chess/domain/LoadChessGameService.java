package chess.domain;

import chess.domain.game.state.ChessGame;
import chess.domain.game.state.RunGame;
import chess.domain.piece.Camp;
import chess.domain.piece.Piece;
import chess.domain.position.ChessBoard;
import chess.domain.position.Position;
import chess.domain.repository.BoardDao;
import chess.domain.repository.PieceDao;
import chess.domain.repository.entity.BoardEntity;
import chess.domain.repository.entity.PieceEntity;
import chess.domain.repository.mapper.PieceDtoMapper;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LoadChessGameService {
    private final BoardDao boardDao;
    private final PieceDao pieceDao;

    public LoadChessGameService(BoardDao boardDao, PieceDao pieceDao) {
        this.boardDao = boardDao;
        this.pieceDao = pieceDao;
    }

    public boolean isGameExist() {
        return boardDao.existBoard();
    }

    public ChessGame loadExistGame() {
        if (!isGameExist()) {
            throw new IllegalStateException("불러올 게임이 존재하지 않습니다.");
        }

        List<PieceEntity> allPieces = pieceDao.findAllPieces();
        BoardEntity board = boardDao.findExistBoard();
        Map<Position, Piece> pieceByPosition = convertToPieceByPosition(allPieces);

        ChessBoard chessBoard = new ChessBoard(pieceByPosition);

        return new RunGame(chessBoard, Camp.valueOf(board.getTurn()));
    }

    private Map<Position, Piece> convertToPieceByPosition(List<PieceEntity> allPieces) {
        Map<Position, Piece> pieceByPosition = new LinkedHashMap<>();

        for (PieceEntity pieceEntity : allPieces) {
            Position position = Position.valueOf(pieceEntity.getPosition());
            String pieceType = pieceEntity.getPieceType();
            String camp = pieceEntity.getCamp();
            Piece piece = PieceDtoMapper.convertToPiece(pieceType, Camp.valueOf(camp));
            pieceByPosition.put(position, piece);
        }

        return pieceByPosition;
    }

    public void cleanUpGame() {
        pieceDao.deleteAll();
        boardDao.deleteAll();
    }
}
