package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.board.Position;
import java.util.HashSet;
import java.util.Set;

public class Rook extends PieceOnBoard {

    public Rook(TeamColor teamColor) {
        super(teamColor, PieceInformation.ROOK);
    }

    public Rook(TeamColor teamColor, Position position) {
        super(teamColor, PieceInformation.ROOK, position);
    }

    @Override
    public boolean isMoveAble(Position source, Position target, ChessBoard chessBoard) {
        Set<Position> candidates = new HashSet<>();
        Position position = source.moveUp();
        while (movable(position, target, chessBoard)) {
            candidates.add(position);
            position = position.moveUp();

        }
        position = source.moveDown();
        while (movable(position, target, chessBoard)) {
            candidates.add(position);
            position = position.moveDown();
        }
        position = source.moveLeft();
        while (movable(position, target, chessBoard)) {
            candidates.add(position);
            position = position.moveLeft();
        }
        position = source.moveRight();
        while (movable(position, target, chessBoard)) {
            candidates.add(position);
            position = position.moveRight();
        }

        return candidates.contains(target);
    }


}
