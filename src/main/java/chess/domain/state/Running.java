package chess.domain.state;

import chess.domain.Result;
import chess.domain.piece.Blank;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.pieceinformations.TeamColor;
import chess.domain.player.Score;
import chess.domain.position.Position;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Running implements GameState {
    private final Map<Position, Piece> chessBoard;

    public Running(Map<Position, Piece> chessBoard) {
        this.chessBoard = chessBoard;
    }

    @Override
    public GameState move(Position source, Position target) {
        Piece startPiece = chessBoard.get(source);
        Piece goingToDie = chessBoard.get(target);

        if (chessBoard.get(source).isMoveAble(target, chessBoard)) {
            //blank 경우
            if (chessBoard.get(target) == Blank.INSTANCE) {
                chessBoard.put(source, Blank.INSTANCE);
                chessBoard.put(target, startPiece);
                startPiece.changePosition(target);
                return new Running(chessBoard);
            }
            // 상대편이 있는 경우
            goingToDie.dead();
            chessBoard.put(target, startPiece);
            chessBoard.put(source, Blank.INSTANCE);
            startPiece.changePosition(target);
            if (goingToDie instanceof King) {
                return new Finished(chessBoard);
            }
            return new Running(chessBoard);
        }
        throw new IllegalArgumentException("잘못된 이동입니다.");
    }

    @Override
    public Result result() {
        Map<TeamColor, Score> result = new HashMap<>();
//        result.put(TeamColor.BLACK, blackPieces.calculateScore());
//        result.put(TeamColor.WHITE, whitePieces.calculateScore());
//
//        if (result.get(TeamColor.BLACK).compareTo(result.get(TeamColor.WHITE)) > 0) {
//            return new Result(result, TeamColor.BLACK);
//        }
//        if (result.get(TeamColor.BLACK).compareTo(result.get(TeamColor.WHITE)) < 0) {
//            return new Result(result, TeamColor.WHITE);
//        }
//
        return new Result(result, TeamColor.NONE);
    }

    @Override
    public Map<Position, Piece> getChessBoard() {
        return Collections.unmodifiableMap(chessBoard);
    }

    @Override
    public boolean containsKey(Position position) {
        return chessBoard.containsKey(position);
    }

    @Override
    public Piece getPiece(Position position) {
        return chessBoard.get(position);
    }

    @Override
    public void put(Position position, Piece piece) {
        chessBoard.put(position, piece);
    }

}
