package chess.domain.service.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.dao.JdbcChessGameDao;
import chess.domain.board.Board;
import chess.domain.command.Turn;
import chess.domain.piece.*;
import chess.domain.piece.dto.GeneratePieceDto;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.service.ChessGame;

public class ChessGameDto {


    private static final String KING_TYPE = "KING";

    private static final String QUEEN_TYPE = "QUEEN";

    private static final String BISHOP_TYPE = "BISHOP";

    private static final String KNIGHT_TYPE = "KNIGHT";

    private static final String ROOK_TYPE = "ROOK";

    private static final String PAWN_TYPE = "PAWN";

    private Long gameId;
    private String turnName;
    private List<GeneratePieceDto> generatePieceDtos;

    public ChessGameDto(Long gameId, String turnName, List<GeneratePieceDto> generatePieceDtos) {
        this.gameId = gameId;
        this.turnName = turnName;
        this.generatePieceDtos = generatePieceDtos;
    }

    public ChessGame generateChessGame() {
        List<Piece> pieces = new ArrayList<>();
        for (GeneratePieceDto generatePieceDto : generatePieceDtos) {
            String file = generatePieceDto.getFile();
            String rank = generatePieceDto.getRank();
            Position position = new Position(File.of(file), Rank.of(Integer.parseInt(rank)));
            String type = generatePieceDto.getType();
            String side = generatePieceDto.getSide();
            addPiece(pieces, position, type, side);
        }
        Board board = new Board(new Pieces(pieces));
        return new ChessGame(gameId, board, Turn.from(turnName));
    }

    private void addPiece(List<Piece> pieces, Position position, String type, String side) {
        if (type.equals(KING_TYPE)) {
            pieces.add(new King(position, Side.of(side)));
        }
        if (type.equals(QUEEN_TYPE)) {
            pieces.add(new Queen(position, Side.of(side)));
        }
        if (type.equals(BISHOP_TYPE)) {
            pieces.add(new Bishop(position, Side.of(side)));
        }
        if (type.equals(KNIGHT_TYPE)) {
            pieces.add(new Knight(position, Side.of(side)));
        }
        if (type.equals(ROOK_TYPE)) {
            pieces.add(new Rook(position, Side.of(side)));
        }
        if (type.equals(PAWN_TYPE)) {
            pieces.add(new Pawn(position, Side.of(side)));
        }
    }
}
