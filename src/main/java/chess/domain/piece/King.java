package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.TeamColor;
import java.util.HashSet;
import java.util.Set;

public class King extends PieceOnBoard {

    public King(TeamColor teamColor) {
        super(teamColor, PieceInformation.KING);
    }

    public King(TeamColor teamColor, Position position) {
        super(teamColor, PieceInformation.KING, position);
    }

    @Override
    public boolean isMoveAble(Position source, Position target, ChessBoard chessBoard) {
        Set<Position> candidates = new HashSet<>();
        Position position = source.moveUp();
        if (position != Position.ERROR && chessBoard.isBlank(position) ||
            (position == target && isEnemyTeam(chessBoard.getPiece(position)))) {
            candidates.add(position);
        }
        position = source.moveDown();
        if (position != Position.ERROR && chessBoard.isBlank(position) ||
            (position == target && isEnemyTeam(chessBoard.getPiece(position)))) {
            candidates.add(position);
        }
        position = source.moveLeft();
        if (position != Position.ERROR && chessBoard.isBlank(position) ||
            (position == target && isEnemyTeam(chessBoard.getPiece(position)))) {
            candidates.add(position);
        }
        position = source.moveRight();
        if (position != Position.ERROR && chessBoard.isBlank(position) ||
            (position == target && isEnemyTeam(chessBoard.getPiece(position)))) {
            candidates.add(position);

        }

        position = source.moveLeftUp();
        if (position != Position.ERROR && chessBoard.isBlank(position) ||
            (position == target && isEnemyTeam(chessBoard.getPiece(position)))) {
            candidates.add(position);
        }
        position = source.moveLeftDown();
        if (position != Position.ERROR && chessBoard.isBlank(position) ||
            (position == target && isEnemyTeam(chessBoard.getPiece(position)))) {
            candidates.add(position);
        }
        position = source.moveRightUp();
        if (position != Position.ERROR && chessBoard.isBlank(position) ||
            (position == target && isEnemyTeam(chessBoard.getPiece(position)))) {
            candidates.add(position);
        }
        position = source.moveRightDown();
        if (position != Position.ERROR && chessBoard.isBlank(position) ||
            (position == target && isEnemyTeam(chessBoard.getPiece(position)))) {
            candidates.add(position);
        }

        return candidates.contains(target);
    }
}
