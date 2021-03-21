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
        this.currentPieces = new CurrentPieces(PieceFactory.initialPieces());
        turn = Color.WHITE;
    }

    public void next() {
        this.turn = turn.reverse();
    }

    public CurrentPieces getCurrentPieces() {
        return currentPieces;
    }

    public void play(Command command) {
        String[] sourceTarget = command.sourceAndTarget();
        Position source = Position.of(sourceTarget[0]);
        Position target = Position.of(sourceTarget[1]);
        Piece sourcePiece = currentPieces.findByPosition(source);
        if (!sourcePiece.getColor().same(turn)) {
            throw new IllegalArgumentException("[ERROR] 현재 턴이 아닌 말은 움직일 수 없습니다.");
        }
        sourcePiece.move(target, currentPieces);
        next();
    }

    public boolean runnable() {
        return currentPieces.isAliveAllKings();
    }
}
