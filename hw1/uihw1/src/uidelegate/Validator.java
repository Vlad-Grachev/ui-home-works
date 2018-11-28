package uidelegate;

public class Validator {
    public static boolean checkYear(String sYear){
        return ((sYear.compareTo("1970") >= 0)&&(sYear.compareTo("2050") <= 0));
    }
    public static boolean checkQty(String sQty){
        int qty = -1;
        try {
            qty = Integer.parseInt(sQty);
        }
        catch (NumberFormatException e){
            return false;
        }
        return qty >= 0;
    }

    public static boolean checkPrice(String sPrice){
        Double price = -1.0;
        try {
             price = Double.parseDouble(sPrice);
        }
        catch (NumberFormatException e){
            return false;
        }
        return price > 0.0;
    }

}
