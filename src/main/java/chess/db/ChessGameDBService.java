package chess.db;

import chess.domain.ChessGame;
import chess.domain.color.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import chess.dto.PieceDto;
import chess.dto.TurnDto;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class ChessGameDBService {
    private final PiecesDao piecesDao;
    private final TurnsDao turnsDao;

    public ChessGameDBService(Supplier<Connection> connector) {
        this.piecesDao = new PiecesDao(connector);
        this.turnsDao = new TurnsDao(connector);
    }

    public boolean hasPreviousData() {
        return piecesDao.count() != 0;
    }

    public ChessGame getCurrentChessGame() {
        return new ChessGame(getCurrentBoard(), getCurrentTurn());
    }

    private Map<Position, Piece> getCurrentBoard() {
        List<PieceDto> pieces = piecesDao.findAll();
        Map<Position, Piece> board = new HashMap<>(pieces.size());
        for (PieceDto piece : pieces) {
            board.put(piece.getPosition(), piece.getPiece());
        }
        return board;
    }

    private Color getCurrentTurn() {
        TurnDto turn = turnsDao.find();
        return turn.getColor();
    }

    public void saveGame(ChessGame chessGame) {
        Map<Position, PieceType> board = chessGame.collectBoard();
        List<PieceDto> pieces = board.entrySet().stream()
                .map(entry -> PieceDto.from(entry.getKey(), entry.getValue()))
                .toList();
        TurnDto turn = TurnDto.of(chessGame.turn());
        updatePieces(pieces);
        updateTurns(turn);
    }

    private void updatePieces(List<PieceDto> pieces) {
        piecesDao.deleteAll();
        for (PieceDto piece : pieces) {
            piecesDao.create(piece);
        }
    }

    private void updateTurns(TurnDto turn) {
        turnsDao.deleteAll();
        turnsDao.create(turn);
    }
}
