package chess.dto;

public class TeamDTO {
    private PiecesDTO piecesDto;
    private String name;
    private String score;
    private boolean isTurn;


    public TeamDTO(PiecesDTO piecesDto, String name, String score, boolean isTurn) {
        this.piecesDto = piecesDto;
        this.name = name;
        this.score = score;
        this.isTurn = isTurn;
    }

    public PiecesDTO getPiecesDto() {
        return piecesDto;
    }

    public String getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public boolean isTurn() {
        return isTurn;
    }
}
