package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.position.Position;
import chess.domain.TeamColor;
import java.util.HashSet;
import java.util.Set;

public class Pawn extends PieceOnBoard {

    public Pawn(TeamColor teamColor) {
        super(teamColor, PieceInformation.PAWN);
    }

    public Pawn(TeamColor teamColor, Position position) {
        super(teamColor, PieceInformation.PAWN, position);
    }

    @Override
    public boolean isMoveAble(Position target, ChessBoard chessBoard) {
        Set<Position> candidates = new HashSet<>();

        Position position;
        if (this.getColor() == TeamColor.BLACK) {
            position = getCurrentPosition().moveDown();
            if (validBlank(position, chessBoard)) {
                candidates.add(position);
                position = position.moveDown();
                if (validBlank(position, chessBoard) && getCurrentPosition().pawnLine(this.getColor())) {
                    candidates.add(position);
                }
            }
            position = getCurrentPosition().moveLeftDown();
            if (isMeetEnemy(position, target, chessBoard)) {
                candidates.add(position);
            }
            position = getCurrentPosition().moveRightDown();
            if (isMeetEnemy(position, target, chessBoard)) {
                candidates.add(position);
            }
        }

        if (this.getColor() == TeamColor.WHITE) {
            position = getCurrentPosition().moveUp();
            if (validBlank(position, chessBoard)) {
                candidates.add(position);
                position = position.moveUp();
                if (validBlank(position, chessBoard) && getCurrentPosition().pawnLine(this.getColor())) {
                    candidates.add(position);
                }
            }
            position = getCurrentPosition().moveLeftUp();
            if (isMeetEnemy(position, target, chessBoard)) {
                candidates.add(position);
            }
            position = getCurrentPosition().moveRightUp();
            if (isMeetEnemy(position, target, chessBoard)) {
                candidates.add(position);
            }
        }
        return candidates.contains(target);
    }


}
