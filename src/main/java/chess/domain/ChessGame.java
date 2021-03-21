package chess.domain;

import chess.domain.command.Command;
import chess.domain.piece.CurrentPieces;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.Position;

public class ChessGame {
    private final CurrentPieces currentPieces;
    private Color turn;

    public ChessGame() {
        this(new CurrentPieces(PieceFactory.initialPieces()));
    }

    public ChessGame(CurrentPieces currentPieces) {
        this(currentPieces, Color.WHITE);
    }

    public ChessGame(CurrentPieces currentPieces, Color turn) {
        this.currentPieces = currentPieces;
        this.turn = turn;
    }

    public CurrentPieces getCurrentPieces() {
        return currentPieces;
    }

    public void next() {
        this.turn = turn.reverse();
    }

    public void movePieceFromSourceToTarget(Command command) {
        Position source = Position.of(command.secondCommand());
        Position target = Position.of(command.thirdCommand());
        Piece sourcePiece = currentPieces.findByPosition(source);
        checkAbleToMove(sourcePiece, target);
        currentPieces.removePieceByPosition(target);
        sourcePiece.move(target, currentPieces);
        next();
    }

    private void checkAbleToMove(Piece sourcePiece, Position target) {
        Piece targetPiece = currentPieces.findByPosition(target);
        if (!sourcePiece.getColor().same(turn)) {
            throw new IllegalArgumentException("[ERROR] 현재 턴이 아닌 말은 움직일 수 없습니다.");
        }
        if (sourcePiece.isSameTeam(targetPiece)) {
            throw new IllegalArgumentException("[ERROR] taget에 같은 편 말이 있습니다.");
        }
    }

    public boolean runnable() {
        return currentPieces.isAliveAllKings();
    }


}
