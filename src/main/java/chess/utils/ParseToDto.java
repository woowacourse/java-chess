package chess.utils;

import chess.domain.game.ChessGame;
import chess.domain.game.File;
import chess.domain.game.Position;
import chess.domain.game.Rank;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;
import chess.dto.game.ChessGameSaveDto;
import chess.dto.outputView.PrintBoardDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class ParseToDto {

    private ParseToDto() {
    }

    public static PrintBoardDto parseToPrintBoardDto(final Map<Position, Piece> board) {
        List<String> pieces = new ArrayList<>();
        for (int rankOrder = Rank.MAX_ORDER; rankOrder >= Rank.MIN_ORDER; rankOrder--) {
            for (int fileOrder = File.MIN_ORDER; fileOrder <= File.MAX_ORDER; fileOrder++) {
                final Position position = Position.of(File.of(fileOrder), Rank.of(rankOrder));
                final Piece piece = board.get(position);
                pieces.add(render(piece));
            }
        }
        return new PrintBoardDto(pieces);
    }

    private static String render(final Piece piece) {
        final Team team = piece.getTeam();
        final PieceType pieceType = piece.getPieceType();

        if (team.isBlack() || team.isEmpty()) {
            return pieceType.getValue();
        }
        if (team.isWhite()) {
            return pieceType.getValue().toLowerCase();
        }
        throw new AssertionError();
    }

    public static ChessGameSaveDto parseToChessGameDto(final ChessGame chessGame) {
        final List<String> pieceType = new ArrayList<>();
        final List<String> pieceFile = new ArrayList<>();
        final List<String> pieceRank = new ArrayList<>();
        final List<String> pieceTeam = new ArrayList<>();
        final List<String> lastTurn = new ArrayList<>();

        final Map<Position, Piece> board = chessGame.getBoard();
        for (Map.Entry<Position, Piece> entry : board.entrySet()) {
            pieceType.add(entry.getValue().getPieceType().name());
            pieceFile.add(entry.getKey().getFile().name());
            pieceRank.add(entry.getKey().getRank().name());
            pieceTeam.add(entry.getValue().getTeam().name());
            lastTurn.add(chessGame.getTurn().name());
        }
        return new ChessGameSaveDto(pieceType, pieceFile, pieceRank, pieceTeam, lastTurn);
    }
}
