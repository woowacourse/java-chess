package chess.domain;

import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.Pieces;
import chess.domain.piece.info.Color;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import chess.domain.state.Ready;
import chess.domain.state.State;

import java.util.List;

public class ChessGame {
    private final Pieces pieces;
    private Color turn;
    private State state;

    public ChessGame() {
        this(new Pieces(PieceFactory.initialPieces()));
    }

    public ChessGame(Pieces pieces) {
        this(pieces, Color.WHITE, new Ready());
    }

    public ChessGame(Pieces pieces, State state) {
        this.pieces = pieces;
        this.state = state;
    }

    public ChessGame(Pieces pieces, Color turn, State state) {
        this.pieces = pieces;
        this.turn = turn;
        this.state = state;
    }

    public List<Piece> getPiecesByAllPosition() {
        return pieces.piecesByAllPosition();
    }

    public void movePieceFromSourceToTarget(Position source, Position target) {
        Piece sourcePiece = pieces.findByPosition(source);
        Piece targetPiece = pieces.findByPosition(target);
        checkAbleToMove(sourcePiece, target, turn);
        Direction direction = Direction.findDirectionByTwoPosition(source, target);
        sourcePiece.checkMovable(targetPiece, direction);
        if (!(sourcePiece instanceof Knight)) {
            checkPieceInPath(source, target, direction);
        }
        pieces.removePieceByPosition(target);
        sourcePiece.move(target);
        next();
    }

    private void checkAbleToMove(Piece sourcePiece, Position target, Color turn) {
        Piece targetPiece = pieces.findByPosition(target);
        if (!sourcePiece.isSameTeam(turn)) {
            throw new IllegalArgumentException("[ERROR] 현재 턴이 아닌 말은 움직일 수 없습니다.");
        }
        if (sourcePiece.isSameTeam(targetPiece)) {
            throw new IllegalArgumentException("[ERROR] target에 같은 편 말이 있습니다.");
        }
    }

    public void checkPieceInPath(Position source, Position target, Direction direction) {
        int countX = Math.abs(source.xDistance(target));
        for (int i = 1; i < countX; i++) {
            int countY = Math.abs(source.yDistance(target));
            for (int j = 1; j < countY; j++) {
                Piece piece = pieces.findByPosition(
                        source.movedPositionByNumber(direction.getXChange() * i, direction.getYChange() * j));
                checkAbleToJump(piece);
            }
        }
    }

    private void checkAbleToJump(Piece piece) {
        if (!(piece.isEmpty())) {
            throw new IllegalArgumentException("[ERROR] 기물을 뛰어 넘어 이동할 수 없습니다.");
        }
    }

    public void next() {
        this.turn = turn.reverse();
    }

    public ScoreStatus scoreStatus() {
        return ScoreStatus.generateByColor(pieces);
    }

    private void changeState(State state) {
        this.state = state;
    }

    public void start() {
        changeState(state.start());
    }

    public boolean runnable() {
        return pieces.isAliveAllKings() && !state.isFinish();
    }

    public boolean notStartYet() {
        return state.isReady();
    }

    public void end() {
        changeState(state.end());
    }
}
