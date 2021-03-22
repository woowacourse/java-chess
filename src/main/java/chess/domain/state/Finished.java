package chess.domain.state;

import chess.domain.Result;
import chess.domain.piece.Piece;
import chess.domain.pieceinformations.TeamColor;
import chess.domain.player.PieceSet;
import chess.domain.player.Score;
import chess.domain.position.Position;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Finished implements GameState {
    private final Map<Position, Piece> chessBoard;

    public Finished(Map<Position, Piece> chessBoard) {
        this.chessBoard = chessBoard;
    }

    @Override
    public GameState move(Position source, Position target) {
        throw new IllegalArgumentException("게임이 종료되었으므로 움직일 수 없습니다.");
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
