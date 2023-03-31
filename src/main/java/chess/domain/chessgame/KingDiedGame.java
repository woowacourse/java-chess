package chess.domain.chessgame;

import chess.domain.board.Board;
import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.piece.Piece;
import chess.domain.side.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class KingDiedGame implements ChessGame {
    public static final String ERROR_MESSAGE = "이미 게임이 종료되었습니다.";

    private final Board board;

    public KingDiedGame(Board board) {
        this.board = board;
    }

    @Override
    public ChessGame start() {
        throw new IllegalArgumentException(ERROR_MESSAGE);
    }

    @Override
    public ChessGame pause() {
        throw new IllegalArgumentException(ERROR_MESSAGE);
    }

    @Override
    public ChessGame move(String sourceSquareInput, String targetSquareInput) {
        throw new IllegalArgumentException(ERROR_MESSAGE);
    }

    @Override
    public Color findWinner() {
        Color colorKingDied = board.findColorKingDied();
        return colorKingDied.findOpponent();
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
        return false;
    }

    @Override
    public Board getBoard() {
        return board;
    }
}
