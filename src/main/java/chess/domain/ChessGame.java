package chess.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Square;

public class ChessGame {
    private static final String ERROR_MESSAGE_TURN = "[ERROR] 순서 지키시지?!";

    private Board board;
    private Color turn;

    public ChessGame() {
        this(new Board(), Color.WHITE);
    }

    public ChessGame(Board board, Color turn) {
        this.board = board;
        this.turn = turn;
    }

    public List<List<String>> collectPieceNames() {
        List<List<String>> pieceNames = new ArrayList<>();
        for (List<Piece> piecesByRank : board.splitByRank()) {
            pieceNames.add(collectPieceNamesByRank(piecesByRank));
        }
        return pieceNames;
    }

    private List<String> collectPieceNamesByRank(List<Piece> piecesByRank) {
        return piecesByRank.stream()
                .map(Piece::getEmoji)
                .collect(Collectors.toList());
    }

    public void move(Square source, Square target) {
        if (!board.isRightTurn(source, turn)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_TURN);
        }
        board.checkCanMove(source, target);
        turn = turn.switchColor();

        if (board.isTargetKing(target)) {
            turn = Color.NONE;
        }

        board = board.move(source, target);
    }

    public boolean isKingDie() {
        return turn == Color.NONE;
    }

    public Status saveStatus() {
        return new Status(board);
    }
}
