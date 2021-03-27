package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.Pieces;
import chess.domain.piece.info.Color;
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

    public void next() {
        this.turn = turn.reverse();
    }

    public void movePieceFromSourceToTarget(Position source, Position target) {
        Piece sourcePiece = pieces.findByPosition(source);
        checkAbleToMove(sourcePiece, target);
        pieces.removePieceByPosition(target);
        sourcePiece.move(target, pieces);
        next();
    }

    private void checkAbleToMove(Piece sourcePiece, Position target) {
        Piece targetPiece = pieces.findByPosition(target);
        if (!sourcePiece.isSameTeam(turn)) {
            throw new IllegalArgumentException("[ERROR] 현재 턴이 아닌 말은 움직일 수 없습니다.");
        }
        if (sourcePiece.isSameTeam(targetPiece)) {
            throw new IllegalArgumentException("[ERROR] taget에 같은 편 말이 있습니다.");
        }
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
