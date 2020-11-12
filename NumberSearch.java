/**
*@author Duarte Ferreira, fc54981
*@author Vasco Barros, fc54986
*/

public class NumberSearch {
    /**
     * Calculates the power of a given base and exponent
     * @param b is an integer bigger than zero.
     * @param e is an integer bigger or equal to zero.
     * @requires b>0; e>=0.
     * @ensures
     * @return integer This returns b raised to e.
    */
    public static int exponential (int b, int e) {
        int result = b;
        
        for (int count = 1; count < e; count++) {
            result = result * b;
        }
        if (e == 0) {
            result = 1;
        }
        return result;
    }

    /**
     * 
     * @param num is an integer bigger than zero.
     * @requires num > 0.
     * @ensures 
     * @return integer This returns the number of digits in num.
     */
    public static int digits (int num) {
        int count = 0;

        while (num != 0) {
            num = num / 10;
            count++;
        }
        return count;
    }

    /**
     * 
     * @param num is an integer bigger than zero.
     * @requires num > 0.
     * @ensures
     * @return integer Returns the number with the digits in reverse order.
     */
    public static int reverseDigits (int num) {
        int reversed = 0;

        while ( num != 0) {
            int digit = num%10;
            reversed = reversed * 10 + digit;
            num = num / 10;
        }   
        return reversed;
    }

    /**
     * 
     * @param num1 is an integer bigger than zero.
     * @param num2 is an integer bigger than zero and num1.
     * @requires num1 > 0; num2 > 0.
     * @ensures
     * @return boolean Indicates if the digit sequence of num1 is subsequence of the digit sequence of num.
     */
    public static boolean isSubsequence (int num1, int num2) {
        boolean var = (num1 > num2);
        int i = 0;

        if (!var) {
            while (i < digits (num2) - digits (num1) + 1) {
                int compare = num2 % exponential (10, digits (num1));
                num2 = num2 / 10;
                i++;

                if (compare == num1) {
                    var = true;
                }
            }
        }
        return var;
    }
    
    /**
     * 
     * @param num is an integer bigger than zero.
     * @param from is an integer bigger or equal to 1 and smaller or qual to to.
     * @param to is an integer bigger than from and smaller than digits(num).
     * @requires 1 <= "from" <= "to" <= digits(num); num > 0.
     * @ensures
     * @return int Returns the number formed by digits in positions "from" to "to" of num.
     */
    public static int subsequence(int num, int from, int to) {
        int var = num;

        var = var % exponential (10, (digits(num) -from +1));
        var = var / exponential (10, (digits(num) - to));

        return var;
    }

    /**
     * 
     * @param num is an integer.
     * @param numberDigits is an integer bigger than zero.
     * @requires numberDigits > 0.
     * @ensures
     * @return
     */
    public static boolean isValidRow (int num, int numberDigits) {
        boolean var = true;
        int i = 1;

        if (num <= 0 || digits(num) != numberDigits) {
            var = false;
        }

        while (i <= digits(num)) {
            if (num % (exponential(10, i)) == num % (exponential (10, i - 1))) {
                var = false;
            }
            i++;
        }
        return var;
    }

    /**
     * 
     * @param num is an integer.
     * @param numberDigits is an integer > 0.
     * @requires numberDigits > 0
     * @ensures
     * @return boolean Returns if wether the sequence has a maximum number of numDigits digits and if all of its digits are between 1 and 9.
     */
    public static boolean isValidSequence (int num, int numberDigits) {
        boolean var = true;
        int i = 1;
        int numCompare = num;

        if (num <= 0 || digits (num) > numberDigits) {
            var = false;
        }

        while (i <= digits (num)) {
            if (numCompare % (exponential (10, i)) == numCompare % (exponential (10, i - 1))) {
                var = false;
            }
            i++;
        }
        return var;
    }

    /**
     * 
     * @param numberDigits is an integer > 0.
     * @param row is an integer.
     * @param sequence is an integer.
     * @requires numberDigits > 0.
     * @ensures
     * @return
     */
    public static void checker (int numberDigits, int row, int sequence) {

        if (isValidRow (row, numberDigits) && isValidSequence (sequence, numberDigits)) {

            if (isSubsequence (sequence, row) || isSubsequence (reverseDigits (sequence), row)) {
                System.out.println ("The sequence " +sequence+ " is hidden in row " +row+ ".");
            }
            else {
                System.out.println ("The sequence " +sequence+ " is not hidden in row " +row+ ".");
            }
        }
        else if ((!isValidRow (row, numberDigits)) && (isValidSequence (sequence, numberDigits))) {
            System.out.println ("The row " +row+ " is not valid.");
        }
        else if ((isValidRow (row, numberDigits)) && (!isValidSequence (sequence, numberDigits))) {
            System.out.println ("The sequence " +sequence+ " is not valid.");
        }
        else {
            System.out.println ("The row " +row+ " is not valid. The sequence " +sequence+ " is not valid.");
        }
    }

    /**
     * 
     * @param row is an integer > 0.
     * @param from is an integer.
     * @param to is an integer.
     * @requires roa > 0.
     */
    public static void checkSubsequence (int row, int from, int to) {
        if (1 <= from && from <= to && to <= digits(row)) {
            System.out.println ("The sequence from posizetion " +from+ " to " +to+ " in row " +row+ " is " +subsequence (row, from, to)+ ".");
        }
        else {
            System.out.println ("The range from " +from+ " to " +to+ " is not valid in row " +row+ ".");
        }
    }
    public static void main (String[] args) {
        int size = 9;

        checker (size, 198745334, 533);
        checker (size, 198745334, 335);
        checker (size, 198745334, 5334);
        checker (size, 198745334, 121);
        checker (size, 19874533, 335);
        checker (size, 198745334, 305);
        checker (size, 19874533, 305);

        checkSubsequence ( 198745334, 6, 8);
        checkSubsequence ( 198745334, 1, 5);
        checkSubsequence ( 198745334, 8, 6);
        checkSubsequence ( 198745334, 6, 12);
    }
}