package chess;

import chess.domain.Team;
import chess.domain.board.Board;
import chess.domain.pieces.Piece;
import chess.domain.position.Position;
import chess.dto.PieceDTO;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;

public class WebUIChessController {
    private ChessGame chessGame = new ChessGame();

    public WebUIChessController() {
    }

    public void run() {
        start();
    }

    public void start() {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            getPieces(model);
            return render(model, "chess.html");
        });
    }

    private void getPieces(final Map<String, Object> model) {
        List<PieceDTO> pieceDTOGroup = new ArrayList<>();

        for (Piece piece : getEntirePieces()) {
            Position piecePosition = piece.getPosition();
            PieceDTO pieceDTO = new PieceDTO(piece.getTeam(), piecePosition.getColumn() + String.valueOf(piecePosition.getRow()), piece.getInitial());
            pieceDTOGroup.add(pieceDTO);
        }

        model.put("pieces", pieceDTOGroup);
    }

    private List<Piece> getEntirePieces() {
        Board board = chessGame.getBoard();
        List<Piece> pieces = board.piecesByTeam(Team.BLACK).toList();
        pieces.addAll(board.piecesByTeam(Team.WHITE).toList());

        return pieces;
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
