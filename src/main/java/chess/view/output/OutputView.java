package chess.view.output;

import chess.model.board.ChessBoard;
import chess.model.piece.Piece;
import chess.model.position.ChessPosition;
import chess.model.position.File;
import chess.model.position.Rank;

import java.util.*;
import java.util.stream.Collectors;

public class OutputView {

    public void printChessBoard(ChessBoard chessBoard) {
        Map<ChessPosition, Piece> board = chessBoard.getBoard();
        List<List<String>> outputBoard = createOutputBoard(board);
        String text = convertToText(outputBoard);
        System.out.println(text);
    }

    private List<List<String>> createOutputBoard(Map<ChessPosition, Piece> board) {
        List<List<String>> outputBoard = new ArrayList<>();
        List<Rank> reversedRanks = reverseRanks();
        for (Rank rank : reversedRanks) {
            List<String> line = getLineByRank(rank, board);
            outputBoard.add(line);
        }
        return outputBoard;
    }

    private List<Rank> reverseRanks() {
        List<Rank> ranks = Arrays.asList(Rank.values());
        Collections.reverse(ranks);
        return ranks;
    }

    private List<String> getLineByRank(Rank rank, Map<ChessPosition, Piece> board) {
        List<String> line = new ArrayList<>();
        for (File file : File.values()) {
            Piece piece = findByPosition(rank, file, board);
            String shape = getPieceShape(piece);
            line.add(shape);
        }
        return line;
    }

    private Piece findByPosition(Rank rank, File file, Map<ChessPosition, Piece> board) {
        ChessPosition position = board.keySet().stream()
                .filter(chessPosition -> chessPosition.getRank() == rank && chessPosition.getFile() == file)
                .findFirst().orElseThrow(() -> new NoSuchElementException("주어진 좌표에 출력을 위한 Piece가 존재하지 않습니다."));
        return board.get(position);
    }

    private String getPieceShape(Piece piece) {
        return PieceShape.from(piece)
                .getShape();
    }

    private String convertToText(List<List<String>> result) {
        return result.stream()
                .map(strings -> String.join("", strings))
                .collect(Collectors.joining(System.lineSeparator()));
    }

    public void printException(String message) {
        System.out.println("[ERROR] " + message);
    }
}
