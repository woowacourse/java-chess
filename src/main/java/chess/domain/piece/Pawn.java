package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.board.Position;
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
    public boolean isMoveAble(Position source, Position target, ChessBoard chessBoard) {
        Set<Position> candidates = new HashSet<>();

        Position position;
        if (this.getColor() == TeamColor.BLACK) {
            position = source.moveDown();
            if (validBlank(position, chessBoard)) {
                candidates.add(position);
                position = position.moveDown();
                if (validBlank(position, chessBoard) && source.startLine(this.getColor())) {
                    candidates.add(position);
                }
            }
            position = source.moveLeftDown();
            if (isMeetEnemy(position, target, chessBoard)) {
                candidates.add(position);
            }
            position = source.moveRightDown();
            if (isMeetEnemy(position, target, chessBoard)) {
                candidates.add(position);
            }
        }

        if (this.getColor() == TeamColor.WHITE) {
            position = source.moveUp();
            if (validBlank(position, chessBoard)) {
                candidates.add(position);
                position = position.moveUp();
                if (validBlank(position, chessBoard) && source.startLine(this.getColor())) {
                    candidates.add(position);
                }
            }
            position = source.moveLeftUp();
            if (isMeetEnemy(position, target, chessBoard)) {
                candidates.add(position);
            }
            position = source.moveRightUp();
            if (isMeetEnemy(position, target, chessBoard)) {
                candidates.add(position);
            }
        }
        return candidates.contains(target);
    }


}
