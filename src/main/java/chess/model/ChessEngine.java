package chess.model;

import chess.model.exceptions.*;

import java.util.Map;

public class ChessEngine {

    private final ChessBoard board;
    private ChessPieceColor thisTurnColor;

    public ChessEngine(final ChessBoard board, final ChessPieceColor color) {
        this.board = board;
        this.thisTurnColor = color;
    }

    public ChessEngine() {
        this.board = new ChessBoard();
        this.thisTurnColor = ChessPieceColor.WHITE;
    }

    public GameFlow move(Point source, Point target) {
        if (source.equals(target)) {
            throw new SameTwoPointsException("source 위치와 target 위치가 같을 수 없습니다.");
        }

        if (board.isEmpty(source)) {
            throw new NoPieceAtSourceException("source 위치에는 체스말이 존재해야합니다.");
        }

        if (!board.isSameColor(source, thisTurnColor)) {
            throw new InvalidTurnException("당신의 차례가 아닙니다.");
        }

        if (!board.isEmpty(target) && board.isSameColor(target, thisTurnColor)) {
            throw new AttackMyTeamException("같은 색상의 말을 공격할 수 없습니다.");
        }

        if (!board.canMove(source, target)) {
            throw new InvalidMovePointException("적절하지 않은 움직임입니다.");
        }

        GameFlow gameFlow = GameFlow.CONTINUE;

        if (!board.isEmpty(target) && board.isSameType(target, ChessPieceType.KING)) {
            gameFlow = GameFlow.valueOf(thisTurnColor);
        }

        board.move(source, target);

        thisTurnColor = ChessPieceColor.nextTurnColor(thisTurnColor);

        return gameFlow;
    }

    public GameResult getGameStatus() {
        double blackScore = board.getScore(ChessPieceColor.BLACK);
        double whiteScore = board.getScore(ChessPieceColor.WHITE);
        GameFlow result = GameFlow.valueOf(blackScore, whiteScore);
        return new GameResult(result, whiteScore, blackScore);
    }

    public Map<Point, AbstractChessPiece> getBoard() {
        return board.getBoard();
    }

    public ChessPieceColor getTurn() {
        return thisTurnColor;
    }
}