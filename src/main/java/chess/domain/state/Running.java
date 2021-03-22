package chess.domain.state;

import chess.domain.Result;
import chess.domain.piece.Blank;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.pieceinformations.TeamColor;
import chess.domain.player.PieceSet;
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
        Piece targetPiece = chessBoard.get(target);

        if (chessBoard.get(source).isMoveAble(target, chessBoard)) {
            return moveBoard(source, target, startPiece, targetPiece);
        }
        throw new IllegalArgumentException("잘못된 이동입니다.");
    }

    private GameState moveBoard(Position source, Position target, Piece startPiece,
        Piece targetPiece) {
        if (chessBoard.get(target) == Blank.INSTANCE) {
            movePieces(source, target, startPiece);
            return new Running(chessBoard);
        }

        targetPiece.dead();
        movePieces(source, target, startPiece);
        return kingCase(targetPiece);
    }

    private GameState kingCase(Piece targetPiece) {
        if (targetPiece instanceof King) {
            return new Finished(chessBoard);
        }
        return new Running(chessBoard);
    }

    private void movePieces(Position source, Position target, Piece startPiece) {
        chessBoard.put(source, Blank.INSTANCE);
        chessBoard.put(target, startPiece);
        startPiece.changePosition(target);
    }

    @Override
    public Result result(PieceSet black, PieceSet white) {
        Map<TeamColor, Score> result = teamScores(black, white);

        if (result.get(TeamColor.BLACK).compareTo(result.get(TeamColor.WHITE)) > 0) {
            return new Result(result, TeamColor.BLACK);
        }
        if (result.get(TeamColor.BLACK).compareTo(result.get(TeamColor.WHITE)) < 0) {
            return new Result(result, TeamColor.WHITE);
        }

        return new Result(result, TeamColor.NONE);
    }


    private Map<TeamColor, Score> teamScores(PieceSet black, PieceSet white) {
        Map<TeamColor, Score> result = new HashMap<>();
        result.put(TeamColor.BLACK, black.calculateScore());
        result.put(TeamColor.WHITE, white.calculateScore());
        return result;
    }


    @Override
    public Map<Position, Piece> getChessBoard() {
        return Collections.unmodifiableMap(chessBoard);
    }

    @Override
    public boolean containsKey(Position position) {
        return chessBoard.containsKey(position);
    }


}
