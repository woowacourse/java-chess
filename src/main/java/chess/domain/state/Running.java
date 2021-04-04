package chess.domain.state;

import chess.domain.Result;
import chess.domain.piece.Blank;
import chess.domain.piece.Piece;
import chess.domain.pieceinformations.TeamColor;
import chess.domain.position.Position;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Running implements GameState {
    public static final double MINUS_HALF_POINT = -0.5;

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
        Map<TeamColor, Score> result = teamScores111(chessBoard);

        if (result.get(TeamColor.BLACK).compareTo(result.get(TeamColor.WHITE)) > 0) {
            return new Result(result, TeamColor.BLACK);
        }
        if (result.get(TeamColor.BLACK).compareTo(result.get(TeamColor.WHITE)) < 0) {
            return new Result(result, TeamColor.WHITE);
        }

        return new Result(result, TeamColor.NONE);
    }

    private Map<TeamColor, Score> teamScores111(Map<Position, Piece> chessBoard) {
        Map<TeamColor, Score> result = new HashMap<>();
        result.put(TeamColor.BLACK, calculateScore(TeamColor.BLACK, chessBoard));
        result.put(TeamColor.WHITE, calculateScore(TeamColor.WHITE, chessBoard));
        return result;
    }

    private Score calculateScore(TeamColor teamColor, Map<Position, Piece> chessBoard) {
        Score sum = Score.ZERO;
        //todo: Character 수정
        Map<Character, Integer> pawnCount = new HashMap<>();
        for (Map.Entry<Position, Piece> item : chessBoard.entrySet()) {
            if (item.getValue().getColor() == teamColor && item.getValue().isAlive()) {
                sum = sum.add(item.getValue().getScore());
                recordPawns(pawnCount, item);
            }
        }

        return sum.add(subtractWhenOnSameLine(pawnCount));
    }

    private void recordPawns(Map<Character, Integer> pawnCount, Map.Entry<Position, Piece> item) {
        if (item.getValue().isPawn()) {
            pawnCount.put(item.getKey().getColumn(),
                    pawnCount.getOrDefault(item.getKey().getColumn(), 0) + 1);
        }
    }

    private Score subtractWhenOnSameLine(Map<Character, Integer> pawnCount) {
        return pawnCount.values().stream()
                .filter(number -> number > 1)
                .map(number -> new Score(MINUS_HALF_POINT * number))
                .reduce(Score.ZERO, Score::add);
    }
}
