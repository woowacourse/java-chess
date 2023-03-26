package chess.domain.board;

import chess.domain.board.coordinate.Coordinate;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;

import java.util.Map;

public interface ChessBoard {
    void move(String fromCoordinate, String toCoordinate, Team currentTeam);
    
    boolean isAllyAtCoordinate(Piece piece, Coordinate coordinate);
    
    boolean isEnemyAtCoordinate(Piece piece, Coordinate coordinate);
    
    boolean isEmpty(Coordinate coordinate);
    
    
    Map<Coordinate, Piece> pieces();
    
    double calculateScore(Team white);
}
