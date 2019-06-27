package chess.model;

import chess.model.board.Board;
import chess.model.dto.BoardInfo;
import chess.model.dto.GameInfo;
import chess.model.dto.MoveResult;
import chess.model.unit.Piece;
import chess.model.unit.Side;
import chess.model.unit.UnitType;

import java.util.*;
import java.util.stream.Collectors;

public class ChessGame {
    private static final int DEFAULT_KING_COUNT = 2;

    private Board board;
    private Side turn;
    private MoveHandler handler;
    private Map<Side, Double> scores = new HashMap<>();

    public ChessGame(final Board board, final Side side) {
        this.board = board;
        turn = side;
        handler = new MoveHandler(board);
    }

    public boolean canMove(final Square beginSquare, final Square endSquare) {
        return handler.move(beginSquare, endSquare, turn);
    }

    public void move(final Square beginSquare, final Square endSquare) {
        board.move(beginSquare, endSquare);
        turn = turn == Side.BLACK ? Side.WHITE : Side.BLACK;
    }

    public boolean isKingAlive() {
        return new HashSet<>(board.getPieces(Piece::isKing)).size() == DEFAULT_KING_COUNT;
    }

    double calculateScore(Side side) {
        scores.put(side, 0.0);
        addNotPawnScore(side);
        addPawnScore(side);
        return scores.get(side);
    }

    private void addPawnScore(Side side) {
        List<Iterator<Square>> iterator = Arrays.stream(Row.values())
                .map(row -> new SquareNavigator(Direction.N, Square.of(Column.Col_1, row), Integer.MAX_VALUE))
                .map(n -> n.findSquares().iterator())
                .collect(Collectors.toList());

        for (Iterator<Square> squareIterator : iterator) {
            addScore(side, calculatePawnScoreEachColumn(squareIterator, side));
        }
    }

    private double calculatePawnScoreEachColumn(Iterator<Square> iterator, Side side) {
        int countOfPawn = 0;
        while (iterator.hasNext()) {
            Piece piece = board.getPiece(iterator.next());
            countOfPawn = piece != null && piece.isSameSide(side) && piece.isPawn() ? countOfPawn + 1 : countOfPawn;
        }
        double scoreInColumn = UnitType.PAWN.getScore() * countOfPawn;
        return countOfPawn < 2 ? scoreInColumn : scoreInColumn / 2;
    }

    private void addNotPawnScore(Side side) {
        addScore(side, board.getPieces(piece -> !piece.isPawn() && piece.isSameSide(side)).stream()
                .mapToDouble(Piece::getScore).sum());
    }

    private void addScore(Side side, Double score) {
        scores.put(side, scores.get(side) + score);
    }

    public GameInfo createGameInfo() {
        GameInfo gameInfo = new GameInfo();
        gameInfo.setFinished(!isKingAlive());
        gameInfo.setTurn(turn.getSymbol());
        return gameInfo;
    }

    public BoardInfo createBoardInfo() {
        BoardInfo boardInfo = new BoardInfo();
        boardInfo.setFinished(!isKingAlive());
        boardInfo.setTurn(turn.getSymbol());
        boardInfo.setPieceMap(board.createPieceMap());
        boardInfo.setWhiteScore(calculateScore(Side.WHITE));
        boardInfo.setBlackScore(calculateScore(Side.BLACK));
        return boardInfo;
    }

    public MoveResult createFailureMoveResult() {
        MoveResult moveResult = new MoveResult();
        moveResult.setCanMove(false);
        return moveResult;
    }

    public MoveResult createSuccessMoveResult() {
        MoveResult moveResult = new MoveResult();
        moveResult.setBlackScore(calculateScore(Side.BLACK));
        moveResult.setWhiteScore(calculateScore(Side.WHITE));
        moveResult.setFinished(!isKingAlive());
        moveResult.setTurn(turn.getSymbol());
        moveResult.setCanMove(true);
        return moveResult;
    }
}
