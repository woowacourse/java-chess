package chess.domain;

import chess.domain.piece.info.Color;
import chess.domain.piece.CurrentPieces;
import chess.domain.piece.Piece;
import chess.domain.piece.info.Position;

import java.util.List;

public class ChessGame {
    private static final String TURN_ERROR = "[ERROR] 현재 턴이 아닌 말은 움직일 수 없습니다.";

    //private final CurrentPieces currentPieces;
    private Color turn;

//    public ChessGame() {
//        this.currentPieces = CurrentPieces.generate();
//        turn = Color.WHITE;
//    }

//    //public CurrentPieces getCurrentPieces() {
//        return currentPieces;
//    }

//    public void play(List<String> sourceTarget) {
//        Position source = Position.of(sourceTarget.get(0).charAt(0), sourceTarget.get(0).charAt(1));
//        Position target = Position.of(sourceTarget.get(1).charAt(0), sourceTarget.get(1).charAt(1));
//        Piece sourcePiece = currentPieces.findByPosition(source);
//        validateTurn(sourcePiece);
//        sourcePiece.move(target, currentPieces);
//        next();
//    }

    private void next() {
        this.turn = turn.reverse();
    }

    private void validateTurn(Piece sourcePiece) {
        if (!sourcePiece.isSameColor(turn)) {
            throw new IllegalArgumentException(TURN_ERROR);
        }
    }

//    public boolean isRunnable(CommandType commandType) {
//        return !(commandType == CommandType.END) && currentPieces.isAliveAllKings();
//    }
}
