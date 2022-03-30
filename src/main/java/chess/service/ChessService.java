package chess.service;

import chess.model.Color;
import chess.model.File;
import chess.model.Rank;
import chess.model.board.Square;
import chess.model.board.Board;
import chess.model.board.ChessInitializer;
import chess.util.PieceToLetterConvertor;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessService {
    private Board board;

    public List<List<String>> initBoard() {
        board = new Board(new ChessInitializer());
        return getAllPieceLetter(board);
    }

    private List<List<String>> getAllPieceLetter(final Board board) {
        return Rank.getRanksInBoardOrder().stream()
                .map(rank -> getPieceLetterInRank(board, rank))
                .collect(Collectors.toList());
    }

    private List<String> getPieceLetterInRank(Board board, Rank rank) {
        return Arrays.stream(File.values())
                .map(file -> board.findPieceBySquare(Square.of(file, rank)))
                .map(PieceToLetterConvertor::convertToLetter)
                .collect(Collectors.toList());
    }

    public List<List<String>> move(String from, String to) {
        board.move(Square.of(from), Square.of(to));
        return getAllPieceLetter(board);
    }

    public Map<String, Double> getScores() {
        return Color.getPlayerColors().stream()
                .collect(Collectors.toMap(Color::name, color -> board.calculatePoint(color)));
    }

    public boolean isBoardReadyOrRunning() {
        return board == null || board.aliveTwoKings();
    }
}
