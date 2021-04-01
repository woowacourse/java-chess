package chess.domain.state;

import chess.domain.ScoreStatus;
import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import chess.domain.piece.info.Color;
import chess.domain.position.Direction;
import chess.domain.position.Position;

import java.util.List;

public abstract class Running implements State {
    protected final Pieces pieces;
    private final Color color;

    public Running(Pieces pieces, Color color) {
        this.pieces = pieces;
        this.color = color;
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public State start() {
        throw new IllegalArgumentException("[ERROR] 이미 게임이 진행중입니다.");
    }

    @Override
    public State end() {
        return new End();
    }

    @Override
    public List<Piece> allPieces() {
        return pieces.piecesByAllPosition();
    }

    @Override
    public Color color() {
        return color;
    }

    @Override
    public void movePieceFromSourceToTarget(Position source, Position target) {
        Piece sourcePiece = pieces.findByPosition(source);
        Piece targetPiece = pieces.findByPosition(target);
        checkAbleToMove(sourcePiece, target);
        Direction direction = Direction.findDirectionByTwoPosition(source, target);
        sourcePiece.checkMovable(targetPiece, direction);
        if (!(sourcePiece.isKnight())) {
            checkPieceInPath(source, target, direction);
        }
        pieces.removePieceByPosition(target);
        sourcePiece.move(target);
    }

    private void checkAbleToMove(Piece sourcePiece, Position target) {
        Piece targetPiece = pieces.findByPosition(target);
        if (!sourcePiece.isSameTeam(color)) {
            throw new IllegalArgumentException("[ERROR] 현재 턴이 아닌 말은 움직일 수 없습니다.");
        }
        if (sourcePiece.isSameTeam(targetPiece)) {
            throw new IllegalArgumentException("[ERROR] target에 같은 편 말이 있습니다.");
        }
    }

    public void checkPieceInPath(Position source, Position target, Direction direction) {
        int countX = Math.abs(source.xDistance(target));
        for (int i = 1; i < countX; i++) {
            checkInYPath(source, target, direction, i);
        }
    }

    private void checkInYPath(Position source, Position target, Direction direction, int i) {
        int countY = Math.abs(source.yDistance(target));
        for (int j = 1; j < countY; j++) {
            Piece piece = pieces.findByPosition(
                    source.movedByNumber(direction.getXChange() * i, direction.getYChange() * j));
            checkAbleToJump(piece);
        }
    }

    private void checkAbleToJump(Piece piece) {
        if (!(piece.isEmpty())) {
            throw new IllegalArgumentException("[ERROR] 기물을 뛰어 넘어 이동할 수 없습니다.");
        }
    }

    @Override
    public ScoreStatus scoreStatus() {
        return ScoreStatus.generateByColor(pieces);
    }

    @Override
    public State checkRunnable() {
        if (pieces.isAliveAllKings()) {
            return this;
        }
        return new End();
    }
}
