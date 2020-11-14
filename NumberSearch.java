/**
* Compile:     javac NumberSearch.java
* Run:         java NumberSearch
*
*@author Duarte Ferreira, fc54981
*@author Vasco Barros, fc54986
*/

public class NumberSearch {

    /**
     * Calculates the power of a given base and exponent
     * 
     * @param base exponential base
     * @param exponent exponent of the number
     * @requires {@code base > 0; exponent >= 0}
     * @ensures
     * @return integer This returns b raised to e
    */
    public static int exponential (int base, int exponent) {
        int result = base;
        
        for (int count = 1; count < exponent; count++) {
            result = result * base;
        }
        if (exponent == 0) {
            result = 1;
        }
        return result;
    }

    /**
     * Calculates the number of digits of a given number
     * 
     * @param num number to be calculated
     * @requires {@code num > 0}
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
     * Does the reverse of a given number
     * 
     * @param num Number to be reversed
     * @requires {@code num > 0}
     * @ensures
     * @return integer Returns the number with the digits in reverse order
     */
    public static int reverseDigits (int num) {
        int reversed = 0;

        while ( num != 0) {
            int digit = num % 10;
            reversed = reversed * 10 + digit;
            num = num / 10;
        }   
        return reversed;
    }

    /**
     * Indicates whether the digit sequence of num1 is a subsequence of the digit sequence of num2
     * 
     * @param num1 num1 is the substring to be tested
     * @param num2 num2 is the string
     * @requires {@code num1 > 0; num2 > 0}
     * @ensures
     * @return boolean Returns true or false
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
     * Calculates the number formed by digits in positions "from" to "to" of a number
     * 
     * @param num num is a number bigger than zero
     * @param from is an integer bigger than zero and smaller or equal to "to"
     * @param to is an integer bigger than "from" and smaller than digits(num)
     * @requires {@code 1 <= "from" <= "to" <= digits(num); num > 0}
     * @ensures
     * @return int Returns the number formed by digits in positions "from" to "to" of num
     */
    public static int subsequence(int num, int from, int to) {
        int var = num;

        var = var % exponential (10, (digits(num) -from +1));
        var = var / exponential (10, (digits(num) - to));

        return var;
    }

    /**
     * Checks whether a number is positive, has numberDigits digits and consists of digits between 1 and 9
     * 
     * @param num num is the row to be tested
     * @param numberDigits number of num digits
     * @requires {@code num > 0; numberDigits > 0}
     * @ensures
     * @return boolean Returns true or false
     */
    public static boolean isValidRow (int num, int numberDigits) {
        boolean var = true;
        int i = 1;

        if (num <= 0 || digits(num) != numberDigits) {
            var = false;
        }

        while (i <= digits(num)) {

            //See if the rest of numCompare by a power of base 10 is equal to the rest of numCompare by the same power by subtracting the exponent 1
            //If it is equal, it is because it has a zero
            if (num % (exponential(10, i)) == num % (exponential (10, i - 1))) {
                var = false;
            }
            i++;
        }
        return var;
    }

    /**
     * Checks whether a number is positive, maximum numberDigits digits and consists of digits between 1 and 9.
     * 
     * @param num num is the sequence to be tested
     * @param numberDigits number of num digits
     * @requires {@code num > 0; numberDigits > 0}
     * @ensures
     * @return boolean Returns true or false
     */
    public static boolean isValidSequence (int num, int numberDigits) {
        boolean var = true;
        int i = 1;
        int numCompare = num;

        if (num <= 0 || digits (num) > numberDigits) {
            var = false;
        }

        while (i <= digits (num)) {

            //See if the rest of numCompare by a power of base 10 is equal to the rest of numCompare by the same power by subtracting the exponent 1
            //If it is equal, it is because it has a zero
            if (numCompare % (exponential (10, i)) == numCompare % (exponential (10, i - 1))) {
                var = false;
            }
            i++;
        }
        return var;
    }

    /**
     * Prints on the screen whether row and sequence are valid or not
     * 
     * @param numberDigits number of row and sequence digits
     * @param row is the row to be tested
     * @param sequence is the sequence to be tested
     * @requires {@code numberDigits > 0; row > 0; sequence > 0}
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
     * Prints on the screen if from and to are valid and indicates the number made up of digits in that range of positions
     * 
     * @param num num is a number bigger than zero
     * @param from is an integer bigger than zero and smaller or equal to "to"
     * @param to is an integer bigger than "from" and smaller than digits(num)
     * @requires {@code row > 0}
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
        System.out.println();
        checkSubsequence ( 198745334, 6, 8);
        checkSubsequence ( 198745334, 1, 5);
        checkSubsequence ( 198745334, 8, 6);
        checkSubsequence ( 198745334, 6, 12);
    }
}