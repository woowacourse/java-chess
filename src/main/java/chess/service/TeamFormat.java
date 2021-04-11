package chess.service;

public enum TeamFormat {
    WHITE_TEAM("white", "white"),
    BLACK_TEAM("black", "black");

    private final String DTOFormat;
    private final String DAOFormat;

    TeamFormat(final String DTOFormat, final String DAOFormat) {
        this.DTOFormat = DTOFormat;
        this.DAOFormat = DAOFormat;
    }

    public String asDTOFormat() {
        return DTOFormat;
    }

    public String asDAOFormat() {
        return DAOFormat;
    }
}
