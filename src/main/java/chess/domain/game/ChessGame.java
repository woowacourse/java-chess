package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.setting.BoardSetting;
import chess.domain.piece.Piece;
import chess.domain.piece.type.PieceWithColorType;
import chess.domain.player.Players;
import chess.domain.player.score.Scores;
import chess.domain.position.Position;
import chess.domain.position.cache.PositionsCache;
import java.util.List;

public class ChessGame {
    private final Players players;
    private final Board board;

    public ChessGame(BoardSetting boardSetting) {
        players = new Players();
        board = new Board();
        setPieces(boardSetting);
    }

    private void setPieces(BoardSetting boardSetting) {
        List<PieceWithColorType> piecesSetting = boardSetting.piecesSetting();
        for (int index = 0; index < piecesSetting.size(); index++) {
            Position position = PositionsCache.get(index);
            PieceWithColorType pieceWithColorType = piecesSetting.get(index);
            setPiece(pieceWithColorType, position);
        }
    }

    private void setPiece(PieceWithColorType pieceWithColorType, Position position) {
        Piece piece = null;
        if (pieceWithColorType != null) {
            piece = Piece.of(pieceWithColorType);
            players.add(piece, position);
        }
        board.setPiece(position, piece);
    }

    public void move(MoveCommand moveCommand) {
        updatePiecesOfPlayers(moveCommand);
        board.move(moveCommand);
    }

    private void updatePiecesOfPlayers(MoveCommand moveCommand) {
        Piece movingPiece = board.findPiece(moveCommand.startPosition());
        players.remove(movingPiece, moveCommand.startPosition());
        players.add(movingPiece, moveCommand.destination());
        if (board.isNotCellEmpty(moveCommand.destination())) {
            Piece deadPiece = board.findPiece(moveCommand.destination());
            players.remove(deadPiece, moveCommand.destination());
        }
    }

    public boolean isKingDead() {
        return board.isKingDead();
    }

    public Board board() {
        return board;
    }

    public Scores scores() {
        return players.scores();
    }
}
