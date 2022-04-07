package chess.dao;

import chess.domain.Board;
import chess.domain.Team;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public interface ChessDAO {

    int saveGame(Board board, String team);

    int getLastInsertKey();

    Board findBoardByGameId(int gameId);

    Piece findPieceByPosition(Position position, int gameId);

    void updatePiece(Piece piece, int gameId);

    Team findTurnByGameId(int gameId);

    void updateTurn(Team team, int gameId);
}
