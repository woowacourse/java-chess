package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.position.Position;
import chess.domain.piece.score.Score;
import chess.domain.piece.team.Team;

import java.util.Map;

public interface Board {
    Board movePiece();
    Piece getPiece(Position position);
    Map<Position, Piece> getPieces();
    boolean isNotFinished();
    Result concludeResult();
    Score calculateScore(Team team);
}
