import static java.lang.Character.getNumericValue;

public class ValidateISBN {

    public static final int LONG_ISBN_LENGTH = 13;
    public static final int MULTIPLIER_FOR_LONG_ISBN = 10;
    public static final int SHORT_ISBN_LENGTH = 10;
    public static final int MULTIPLIER_FOR_SHORT_ISBN = 11;

    // ISBN numbers can be 10 or 13 digits long. Sometimes instead of the last number there can be X. ISBN 10 digit check:
// 0123456789 -> (0*10 + 1*9 + 2*8 + 3*7 +4*6 + 5*5 + 6*4 + 7*3 + 8*2 + 9*1) % 11 = 0
    // ISBN 13 digit check: 0123456789123 -> (0*1 + 1*3 + 2*1 + 3*3 + 4*1 + 5*3...) % 10 = 0
    public boolean checkISBN(String isbn) {

        if (isbn.length() == LONG_ISBN_LENGTH) {
            return isThisAValidLongISBN(isbn);
        } else if (isbn.length() == SHORT_ISBN_LENGTH) {
            return isThisAValidShortISBN(isbn);
        }
        throw new NumberFormatException("ISBN numbers must be 10 or 13 digits long");
    }

    private boolean isThisAValidShortISBN(String isbn) {
        int total = 0;

        for (int i = 0; i < SHORT_ISBN_LENGTH; i++) {
            if (!Character.isDigit(isbn.charAt(i))) {
                if (i == 9 && isbn.charAt(i) == 'X') {  // 'X' in single quotes because it's a character
                    total += 10;    // 10 needs to be added for X
                } else {
                    throw new NumberFormatException("ISBN numbers contain only digits");
                }
            } else {
                total += getNumericValue(isbn.charAt(i)) * (SHORT_ISBN_LENGTH - i);
            }
        }

        return total % MULTIPLIER_FOR_SHORT_ISBN == 0;
    }

    private boolean isThisAValidLongISBN(String isbn) {
        int total = 0;
        for (int i = 0; i < LONG_ISBN_LENGTH; i++) {
            if (i % 2 == 0) {
                total += getNumericValue(isbn.charAt(i));   // if even multiply by one
            } else {
                total += getNumericValue(isbn.charAt(i)) * 3;   // if odd multiply by 3
            }
        }

        return total % MULTIPLIER_FOR_LONG_ISBN == 0;
    }


    // ----- BEFORE REFACTORING -----
//    public boolean checkISBN(String isbn) {
//
//        if (isbn.length() == 13) {
//            int total = 0;
//            for (int i = 0; i < 13; i++) {
//                if (i % 2 == 0) {
//                    total += getNumericValue(isbn.charAt(i));   // if even multiply by one
//                } else {
//                    total += getNumericValue(isbn.charAt(i)) * 3;   // if odd multiply by 3
//                }
//            }
//            if (total % 10 == 0) {
//                return true;
//            } else {
//                return false;
//            }
//        } else {
//            if (isbn.length() != 10 && isbn.length() != 13) {
//                throw new NumberFormatException("ISBN numbers must be 10 or 13 digits long");
//            }
//
//            int total = 0;
//
//            for (int i = 0; i < 10; i++) {
//                if (!Character.isDigit(isbn.charAt(i))) {
//                    if (i == 9 && isbn.charAt(i) == 'X') {  // 'X' in single quotes because it's a character
//                        total += 10;    // 10 needs to be added for X
//                    } else {
//                        throw new NumberFormatException("ISBN numbers contain only digits");
//                    }
//                } else {
//                    total += getNumericValue(isbn.charAt(i)) * (10 - i);
//                }
//            }
//
//            if (total % 11 == 0) {
//                return true;
//            } else {
//                return false;
//            }
//        }
//    }

}
