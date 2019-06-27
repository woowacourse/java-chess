package chess.domain.board;

import chess.domain.piece.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Game {
    private final Player whitePlayer;
    private final Player blackPlayer;
    private final ChessObserver observer;
    private PieceColor turn;

    public Game(Player whitePlayer, Player blackPlayer) {
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.turn = PieceColor.BLACK;
        this.observer = new ChessObserver();
    }

    public int whitePiecesCount() {
        return whitePlayer.getPiecesCount();
    }

    public int blackPiecesCount() {
        return blackPlayer.getPiecesCount();
    }

    public Piece getPiece(Square source) {
        return blackPlayer.getPiece(source)
                .orElseGet(() -> whitePlayer.getPiece(source)
                        .orElseThrow(IllegalArgumentException::new));
    }

    public Vectors movableArea(Square source) {
        Piece piece = getPiece(source);
        checkTurn(piece);

        Vectors movableArea = piece.movableArea(source);

        if (!(piece instanceof Knight)) {
            movableArea = movableArea.removeObstacles(blackPlayer, whitePlayer);
        }

        if (piece instanceof King) {
            movableArea = movableArea.removeKingPath(opponentPlayer().getKingPath());
        }

        if (piece instanceof Pawn) {
            movableArea = movableArea.removeOpponentPlayer(opponentPlayer());
        }

        return movableArea.removeCurrentPlayers(currentPlayer());
    }

    private void checkTurn(Piece piece) {
        if (!piece.isSameColor(turn)) {
            throw new IllegalArgumentException("맞는 턴이 아닙니다");
        }
    }

    private Player currentPlayer() {
        if (turn.equals(PieceColor.BLACK)) {
            return blackPlayer;
        }

        return whitePlayer;
    }

    private Player opponentPlayer() {
        if (turn.equals(PieceColor.BLACK)) {
            return whitePlayer;
        }

        return blackPlayer;
    }

    public boolean move(Square source, Square target) {
        Piece sourcePiece = getPiece(source);
        checkTurn(sourcePiece);
        movableArea(source).checkTarget(target);

        currentPlayer().move(source, target);

        if (opponentPlayer().getPiece(target).isPresent()) {
            boolean playing = takePiece(target);
            turn = turn.toggle();
            return playing;
        }
        turn = turn.toggle();
        return true;
    }

    private boolean takePiece(Square target) {
        Piece deadPiece = opponentPlayer().remove(target);
        observer.take(deadPiece);

        return !(deadPiece instanceof King);
    }

    public Score score() {
        double blackScore = blackPlayer.score();
        double whiteScore = whitePlayer.score();

        return new Score(blackScore, whiteScore);
    }

    public PieceColor getTurn() {
        return turn;
    }

    public List<Piece> getDeadList(PieceColor color) {
        return observer.getDeadList(color);
    }
}
