package chess.domain.piece;

import chess.domain.board.BoardSituation;
import chess.domain.player.Team;
import chess.domain.position.Position;

import java.util.List;

public interface PieceState {

    PieceState move(Position target, BoardSituation boardSituation);

    List<Position> getMovablePositions(BoardSituation boardSituation);

    PieceType getPieceType();

    Team getTeam();

    double getPoint(BoardSituation boardSituation);
}
