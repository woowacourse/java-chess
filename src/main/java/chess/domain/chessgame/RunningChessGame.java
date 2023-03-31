package chess.domain.chessgame;

import chess.domain.board.Board;
import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import chess.domain.piece.Piece;
import chess.domain.side.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RunningChessGame implements ChessGame {

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
        Color sideKingDied = board.findColorKingDied();
        if (sideKingDied.equals(Color.NOTHING)) {
            return this;
        }
        return new KingDiedGame(board);
    }

    @Override
    public Color findWinner() {
        Map<Color, Double> sideScore = status();
        Double whiteScore = sideScore.get(Color.WHITE);
        Double blackScore = sideScore.get(Color.BLACK);

        if (whiteScore.compareTo(blackScore) > 0) {
            return Color.BLACK;
        }
        if (whiteScore.compareTo(blackScore) < 0) {
            return Color.WHITE;
        }
        return Color.NOTHING;
    }

    @Override
    public Map<Color, Double> status() {
        return board.calculateScore();
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

    @Override
    public Board getBoard() {
        return board;
    }
}
