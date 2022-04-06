package chess.serviece;

import chess.dao.PieceDao;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.position.Position;
import chess.dto.PieceDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessService {

    private final PieceDao pieceDao;

    public ChessService(PieceDao pieceDao) {
        this.pieceDao = pieceDao;
    }

    public List<PieceDto> gameStart() {
        pieceDao.removeAll();
        pieceDao.saveAll(getInitPieceDtos());
        return pieceDao.findAll();
    }

    private List<PieceDto> getInitPieceDtos() {
        Map<Position, Piece> initPieces = PieceFactory.createChessPieces();
        return initPieces.entrySet()
                .stream()
                .map(entry -> PieceDto.from(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
}
