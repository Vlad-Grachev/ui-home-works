package shared;

public class FieldVerifier {
    public static boolean isIdValid(String sId) {
        boolean valid = false;
        int id = -1;
        try{
            id = Integer.parseInt(sId);
        } catch (NumberFormatException e) {
            return false;
        }
        if(id >= 100)
            valid = true;
        return valid;
    }

    public static boolean isAuthorValid(String title) {
        if (title == null) {
            return false;
        }
        return title.length() > 4;
    }

    public static boolean isTitleValid(String title) {
        if (title == null) {
            return false;
        }
        return title.length() > 4;
    }

    public static boolean isNumPagesValid(String sNumPages) {
        boolean valid = false;
        int numPages = -1;
        try{
            numPages = Integer.parseInt(sNumPages);
        } catch (NumberFormatException e) {
            return false;
        }
        if(numPages > 0)
            valid = true;
        return valid;
    }

    public static boolean isReleaseYearValid(String sYear) {
        boolean valid = false;
        int year = -1;
        try{
            year = Integer.parseInt(sYear);
        } catch (NumberFormatException e) {
            return false;
        }
        if(year > 1890 && year < 2020)
            valid = true;
        return valid;
    }
}
