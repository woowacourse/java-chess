package chess.repository;

import chess.domain.PieceDto;
import chess.domain.chessGame.ChessGameState;
import chess.domain.piece.Color;
import chess.domain.position.Position;

import java.util.Map;

public interface ChessGameDao {
    ChessGameState findChessGame();

    void savePieces(Map<Position, PieceDto> pieces);

    void updateTurn(Color color);

    void updateChessGame(ChessGameState gameState);

    void deleteAll();

    Color findTurn();
}
