package chess.util;

public class ASCIIUtil {


    public static boolean verifySameCase(String alphabet1, String alphabet2) {
        return Character.isLowerCase(alphabet1.charAt(0)) == Character.isLowerCase(alphabet2.charAt(0));
    }
}
