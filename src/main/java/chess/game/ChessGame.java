package chess.game;

import java.util.List;

import chess.board.Board;
import chess.board.File;
import chess.board.Position;
import chess.board.Rank;
import chess.piece.Piece;

public class ChessGame {

    private final Board board;
    private Turn turn;

    public ChessGame(final Board board) {
        this.board = board;
        this.turn = Turn.WHITE;
    }

    public Position generatePosition(final String file, final int rank) {
        final File sourceFile = File.of(file);
        final Rank sourceRank = Rank.of(rank);
        return new Position(sourceFile, sourceRank);
    }

    public void movePiece(Position sorucePosition, Position targetPosition) {
        checkTurn(sorucePosition);
        board.movePiece(sorucePosition, targetPosition);
        this.turn = turn.change();
    }

    private void checkTurn(Position sorucePosition) {
        Piece sourcePiece = board.findPieceByPosition(sorucePosition);
        if (!turn.isCorrectTurn(sourcePiece.getSide())) {
            throw new IllegalArgumentException("[ERROR] 현재 턴인 진영의 기물만 이동할 수 있습니다.");
        }
    }

    public List<Piece> getPieces() {
        return board.getPieces();
    }
}
