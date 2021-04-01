package chess.webdto;

import chess.domain.ChessGame;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.webdao.PieceDBFormat;

import java.util.HashMap;
import java.util.Map;

public class ChessGameInfoDTO {
    private Map<String, Map<String, String>> piecePositionByTeam;
    private String currentTurnTeam;
    private double whiteTeamScore;
    private double blackTeamScore;
    private boolean isPlaying;

    public static ChessGameInfoDTO of(final ChessGame chessGame, final String currentTurnTeam) {
        final Map<String, Map<String, String>> piecePositionByTeam = piecePositionByTeam(chessGame);
        final double whiteTeamScore = chessGame.calculateWhiteTeamScore();
        final double blackTeamScore = chessGame.calculateBlackTeamScore();
        final boolean isPlaying = chessGame.isPlaying();
        return new ChessGameInfoDTO(piecePositionByTeam, currentTurnTeam, whiteTeamScore, blackTeamScore, isPlaying);
    }

    private ChessGameInfoDTO(Map<String, Map<String, String>> piecePositionByTeam, String currentTurnTeam, double whiteTeamScore, double blackTeamScore, boolean isPlaying) {
        this.piecePositionByTeam = piecePositionByTeam;
        this.whiteTeamScore = whiteTeamScore;
        this.blackTeamScore = blackTeamScore;
        this.currentTurnTeam = currentTurnTeam;
        this.isPlaying = isPlaying;
    }

    private static Map<String, Map<String, String>> piecePositionByTeam(final ChessGame chessGame) {
        final Map<String, Map<String, String>> piecePosition = new HashMap<>();
        piecePosition.put("white", piecePositionOfSingleTeam(chessGame.currentWhitePiecePosition()));
        piecePosition.put("black", piecePositionOfSingleTeam(chessGame.currentBlackPiecePosition()));
        return piecePosition;
    }

    private static Map<String, String> piecePositionOfSingleTeam(final Map<Position, Piece> piecePosition) {
        final Map<String, String> jsonConvertedPiecePosition = new HashMap<>();
        for (Position position : piecePosition.keySet()) {
            final String positionInitial = position.getPositionInitial();
            final Piece chosenPiece = piecePosition.get(position);
            final String pieceString = PieceDBFormat.convert(chosenPiece);
            jsonConvertedPiecePosition.put(positionInitial, pieceString);
        }
        return jsonConvertedPiecePosition;
    }
}
