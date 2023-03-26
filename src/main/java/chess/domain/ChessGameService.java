package chess.domain;

import chess.domain.game.state.ChessGame;
import chess.domain.game.state.RunGame;
import chess.domain.piece.Camp;
import chess.domain.piece.Piece;
import chess.domain.position.ChessBoard;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.repository.BoardDao;
import chess.domain.repository.PieceDao;
import chess.domain.repository.dto.FileDtoMapper;
import chess.domain.repository.dto.PieceDtoMapper;
import chess.domain.repository.dto.RankDtoMapper;
import chess.domain.repository.entity.BoardEntity;
import chess.domain.repository.entity.PieceEntity;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ChessGameService {
    private final BoardDao boardDao = new BoardDao();
    private final PieceDao pieceDao = new PieceDao();

    public boolean isGameExist() {
        return boardDao.existBoard();
    }

    public ChessGame getExistGame() {
        List<PieceEntity> allPieces = pieceDao.findAllPieces();
        BoardEntity board = boardDao.findExistBoard();
        Map<Position, Piece> pieceByPosition = convertToPieceByPosition(allPieces);

        ChessBoard chessBoard = new ChessBoard(pieceByPosition);

        return new RunGame(chessBoard, Camp.valueOf(board.getTurn()));
    }

    private Map<Position, Piece> convertToPieceByPosition(List<PieceEntity> allPieces) {
        Map<Position, Piece> pieceByPosition = new LinkedHashMap<>();

        for (PieceEntity pieceEntity : allPieces) {
            Position position = convertToPosition(pieceEntity);
            String pieceType = pieceEntity.getPieceType();
            String camp = pieceEntity.getCamp();
            Piece piece = PieceDtoMapper.convertToPiece(pieceType, Camp.valueOf(camp));
            pieceByPosition.put(position, piece);
        }

        return pieceByPosition;
    }

    private Position convertToPosition(PieceEntity pieceEntity) {
        String[] split = pieceEntity.getPosition().split("");
        File file = FileDtoMapper.convertToFile(split[0]);
        Rank rank = RankDtoMapper.convertToRank(split[1]);

        return Position.of(file, rank);
    }
}
