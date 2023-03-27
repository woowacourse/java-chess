package chess.domain.chessgame;

import chess.domain.board.*;
import chess.domain.piece.Piece;
import chess.domain.result.Judge;
import chess.domain.result.Score;
import chess.domain.side.Color;
import chess.domain.side.Side;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RunningChessGame implements ChessGame {
    public static final Side BLACK = Side.from(Color.BLACK);
    public static final Side WHITE = Side.from(Color.WHITE);
    public static final Side NOTHING = Side.from(Color.NOTHING);

    private final Board board;

    public RunningChessGame(Board board) {
        this.board = board;
    }

    @Override
    public ChessGame start() {
        throw new IllegalStateException("게임이 이미 시작되었습니다.");
    }

    @Override
    public ChessGame pause() {
        return new PauseChessGame(board);
    }

    @Override
    public ChessGame move(final String sourceSquareInput, final String targetSquareInput) {
        Square sourceSquare = Square.from(sourceSquareInput);
        Square targetSquare = Square.from(targetSquareInput);
        board.makeMove(sourceSquare, targetSquare);
        return checkKingDead();
    }

    private ChessGame checkKingDead() {
        Side sideKingDied = Judge.findSideKingDied(board);
        if (sideKingDied.equals(NOTHING)) {
            return this;
        }
        return new KingDiedGame(board);
    }

    @Override
    public Side findWinner() {
        Map<Side, Score> sideScore = status();
        Score blackScore = sideScore.get(BLACK);
        Score whiteScore = sideScore.get(WHITE);
        //TODO: compareTo로 바꾸기
        if (blackScore.getValue() > whiteScore.getValue()) {
            return BLACK;
        }
        if (blackScore.getValue() < whiteScore.getValue()) {
            return WHITE;
        }
        return Side.from(Color.NOTHING);
    }

    @Override
    public Map<Side, Score> status() {
        return Judge.calculateScore(board);
    }

    @Override
    public List<List<Piece>> findChessBoard() {
        List<List<Piece>> pieces = new ArrayList<>();
        List<File> files = List.of(File.values());
        List<Rank> ranks = List.of(Rank.values());
        for (Rank rank : ranks) {
            List<Piece> pieceByRank = files.stream()
                    .map(file -> board.findPiece(file, rank))
                    .collect(Collectors.toList());
            pieces.add(pieceByRank);
        }
        return pieces;
    }

    @Override
    public boolean isContinue() {
        return true;
    }
}
