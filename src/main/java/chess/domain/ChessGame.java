package chess.domain;

import chess.domain.command.Command;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.Pieces;
import chess.domain.piece.info.Color;
import chess.domain.position.Position;

public class ChessGame {
    private final Pieces pieces;
    private Color turn;

    public ChessGame() {
        this(new Pieces(PieceFactory.initialPieces()));
    }

    public ChessGame(Pieces pieces) {
        this(pieces, Color.WHITE);
    }

    public ChessGame(Pieces pieces, Color turn) {
        this.pieces = pieces;
        this.turn = turn;
    }

    public Pieces getCurrentPieces() {
        return pieces;
    }

    public void next() {
        this.turn = turn.reverse();
    }

    public void movePieceFromSourceToTarget(Command command) {
        Position source = Position.of(command.secondCommand());
        Position target = Position.of(command.thirdCommand());
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

    public boolean runnable() {
        return pieces.isAliveAllKings();
    }

    public ScoreStatus scoreStatus() {
        return ScoreStatus.generateByColor(pieces);
    }
}
