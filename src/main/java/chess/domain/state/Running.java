package chess.domain.state;

import chess.domain.Result;
import chess.domain.piece.Blank;
import chess.domain.piece.Piece;
import chess.domain.pieceinformations.TeamColor;
import chess.domain.position.Position;

import java.util.Collections;
import java.util.Map;

public class Running extends Game {
    private final Map<Position, Piece> chessBoard;
    private final TeamColor turn;

    public Running(Map<Position, Piece> chessBoard, TeamColor turn) {
        this.chessBoard = chessBoard;
        this.turn = turn;
    }

    public GameState move(Position source, Position target) {
        Piece startPiece = chessBoard.get(source);
        Piece targetPiece = chessBoard.get(target);
        if (startPiece.getColor() != turn) {
            throw new IllegalArgumentException("차례가 아닙니다.");
        }
        if (startPiece.isMovable(source, target, chessBoard)) {
            return moveBoard(source, target, startPiece, targetPiece);
        }
        throw new IllegalArgumentException("잘못된 이동입니다.");
    }

    private GameState moveBoard(Position source, Position target, Piece startPiece,
                                Piece targetPiece) {
        if (chessBoard.get(target) == Blank.INSTANCE) {
            movePieces(source, target, startPiece);
            return new Running(chessBoard, turn.counterpart());
        }

        targetPiece.dead();
        movePieces(source, target, startPiece);
        return kingCase(targetPiece);
    }

    private GameState kingCase(Piece targetPiece) {
        if (targetPiece.isKing()) {
            return new Finished(chessBoard);
        }
        return new Running(chessBoard, turn.counterpart());
    }

    private void movePieces(Position source, Position target, Piece startPiece) {
        chessBoard.put(source, Blank.INSTANCE);
        chessBoard.put(target, startPiece);
    }


    @Override
    public Map<Position, Piece> getChessBoard() {
        return Collections.unmodifiableMap(chessBoard);
    }

    @Override
    public GameState terminate() {
        return new Finished(chessBoard);
    }

    @Override
    public boolean isRunning() {
        return true;
    }


    @Override
    public Result result() {
        return calculateResult(chessBoard);
    }

}
