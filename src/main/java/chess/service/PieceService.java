package chess.service;

import chess.dao.PieceDao;
import chess.dao.PieceDaoImpl;
import chess.domain.Board;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.dto.NavigatorDto;
import chess.dto.PieceDto;

import java.util.List;
import java.util.Map;

public class PieceService {
    private static PieceDao pieceDao = PieceDaoImpl.getInstance();

    private PieceService() {}

    public static List<PieceDto> findByGameId(int gameId) {
        return pieceDao.findByGameId(gameId);
    }

    public static void updatePiece(NavigatorDto navigatorDto) {
        pieceDao.updatePiece(navigatorDto);
    }

    public static Board setBoard(Board board, List<PieceDto> pieceDtos, int gameId) {
        if (pieceDtos.size() == 0) {
            board.initBoard();
            Map<Position, Piece> pieces = board.getPieces();

            for (Position position : pieces.keySet()) {
                int teamId = pieces.get(position).getAliance().getTeamId();
                int kindId = pieces.get(position).getPieceValue().getKindId();
                PieceDto pieceDto = new PieceDto(teamId, gameId, kindId, position.toString());
                pieceDao.addPiece(pieceDto);
            }
        }

        if (pieceDtos.size() != 0) {
            for (PieceDto piece : pieceDtos) {
                board.putPiece(piece.getPosition(), piece.getAliance().getTeamId(), piece.getKindId());
            }
        }
        return board;
    }
}
