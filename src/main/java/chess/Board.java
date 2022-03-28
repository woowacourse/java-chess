package chess;

import chess.piece.*;

import java.util.*;

import static chess.File.*;
import static chess.Player.*;
import static chess.Rank.*;

public class Board {

    private final Map<Position, Piece> board;

    public Board() {
        board = new HashMap<>();
        initBoard();
        createBlackPieces();
        createWhitePieces();
    }

    private void initBoard() {
        for (Rank rank : Rank.values()) {
            createBlankIn(rank);
        }
    }

    private void createBlankIn(Rank rank) {
        for (File file : File.values()) {
            board.put(Position.of(rank, file), new Blank(NONE, "."));
        }
    }

    private void createBlackPieces() {
        board.put(Position.of(EIGHT, A), new Rook(BLACK, "R"));
        board.put(Position.of(EIGHT, B), new Knight(BLACK, "N"));
        board.put(Position.of(EIGHT, C), new Bishop(BLACK, "B"));
        board.put(Position.of(EIGHT, D), new Queen(BLACK, "Q"));
        board.put(Position.of(EIGHT, E), new King(BLACK, "K"));
        board.put(Position.of(EIGHT, F), new Bishop(BLACK, "B"));
        board.put(Position.of(EIGHT, G), new Knight(BLACK, "N"));
        board.put(Position.of(EIGHT, H), new Rook(BLACK, "R"));
        for (File file : File.values()) {
            board.put(Position.of(SEVEN, file), new Pawn(BLACK, "P"));
        }
    }

    private void createWhitePieces() {
        board.put(Position.of(ONE, A), new Rook(WHITE, "r"));
        board.put(Position.of(ONE, B), new Knight(WHITE, "n"));
        board.put(Position.of(ONE, C), new Bishop(WHITE, "b"));
        board.put(Position.of(ONE, D), new Queen(WHITE, "q"));
        board.put(Position.of(ONE, E), new King(WHITE, "k"));
        board.put(Position.of(ONE, F), new Bishop(WHITE, "b"));
        board.put(Position.of(ONE, G), new Knight(WHITE, "n"));
        board.put(Position.of(ONE, H), new Rook(WHITE, "r"));
        for (File file : File.values()) {
            board.put(Position.of(TWO, file), new Pawn(WHITE, "p"));
        }
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }

    public void move(Position source, Position target) {
        if (board.get(source).isSame(NONE)) {
            throw new IllegalArgumentException("[ERROR] 선택한 위치에 기물이 없습니다.");
        }
        if (!board.get(source).canMove(source, target, board)) {
            throw new IllegalArgumentException("[ERROR] 기물이 해당 위치로 갈 수 없습니다.");
        }
        board.put(target, board.get(source));
        board.put(source, new Blank(NONE, "."));
    }

    public boolean isKilledKing() {
        return board.values()
                .stream()
                .filter(Piece::isKing)
                .count() < 2;
    }

    public boolean checkRightTurn(Player player, Position source) {
        return board.get(source).isSame(player);
    }

    public double calculateScore(Player player) {
        double score = 0;
        for (Rank rank : Rank.values()) {
            int PawnCountInFile = 0;
            for (File file : File.values()) {
                if (board.get(Position.of(rank, file)).isSame(player)) {
                    score = board.get(Position.of(rank, file)).addTo(score);
                    if (board.get(Position.of(rank, file)).isPawn()) {
                        PawnCountInFile += 1;
                    }
                }
            }
            if (PawnCountInFile >= 2) {
                score -= 0.5 * PawnCountInFile;
            }
        }
        return score;
    }
}
