package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.board.Position;
import chess.domain.state.TeamColor;
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
    public boolean isMovable(Position source, Position target, ChessBoard chessBoard) {
        Set<Position> candidates = new HashSet<>();

        if (isBlackPawn()) {
            candidates = blackPawnMovePattern(source, target, chessBoard);
        }
        if (isWhitePawn()) {
            candidates = whitePawnMovePattern(source, target, chessBoard);
        }
        return candidates.contains(target);
    }

    private Set<Position> whitePawnMovePattern(Position source, Position target,
        ChessBoard chessBoard) {
        Set<Position> candidates = new HashSet<>();

        candidates.addAll(whiteFront(source, target, chessBoard));
        candidates.addAll(whiteCatchEnemyPiece(source, target, chessBoard));
        return candidates;
    }

    private Set<Position> whiteFront(Position source, Position target, ChessBoard chessBoard) {
        Set<Position> candidates = new HashSet<>();
        Position position = source.moveUp();
        if (validBlank(position, chessBoard)) {
            candidates.add(position);
        }
        position = source.moveUp().moveUp();
        if (validBlank(source.moveUp().moveUp(), chessBoard) && source
            .isPawnNotMoving(this.getColor())) {
            candidates.add(position);
        }
        return candidates;
    }

    private Set<Position> whiteCatchEnemyPiece(Position source, Position target,
        ChessBoard chessBoard) {
        Set<Position> candidates = new HashSet<>();
        Position position = source.moveLeftUp();
        if (isMeetEnemy(position, target, chessBoard)) {
            candidates.add(position);
        }
        position = source.moveRightUp();
        if (isMeetEnemy(position, target, chessBoard)) {
            candidates.add(position);
        }
        return candidates;
    }

    private boolean isWhitePawn() {
        return this.getColor() == TeamColor.WHITE;
    }

    private Set<Position> blackPawnMovePattern(Position source, Position target,
        ChessBoard chessBoard) {
        Set<Position> candidates = new HashSet<>();

        candidates.addAll(blackFront(source, target, chessBoard));
        candidates.addAll(blackCatchEnemyPiece(source, target, chessBoard));
        return candidates;
    }

    private Set<Position> blackFront(Position source, Position target, ChessBoard chessBoard) {
        Set<Position> candidates = new HashSet<>();
        Position position = source.moveDown();
        if (validBlank(position, chessBoard)) {
            candidates.add(position);
        }
        position = source.moveDown().moveDown();
        if (validBlank(source.moveUp().moveUp(), chessBoard) && source
            .isPawnNotMoving(this.getColor())) {
            candidates.add(position);
        }
        return candidates;
    }

    private Set<Position> blackCatchEnemyPiece(Position source, Position target,
        ChessBoard chessBoard) {
        Set<Position> candidates = new HashSet<>();
        Position position = source.moveLeftDown();
        if (isMeetEnemy(position, target, chessBoard)) {
            candidates.add(position);
        }
        position = source.moveRightDown();
        if (isMeetEnemy(position, target, chessBoard)) {
            candidates.add(position);
        }
        return candidates;
    }

    private boolean isBlackPawn() {
        return this.getColor() == TeamColor.BLACK;
    }


}
