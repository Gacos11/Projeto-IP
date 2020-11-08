public class NumberSearch{

    public static void main(String[] args){
        System.out.print(isSubsequence(23, 1234));
        System.out.println(exponential(10,2));
    }

    static int exponential(int b, int e){
        int result = b;
        for(int count = 1; count < e; count++){
            result = result * b;
        }
        return (result);
    }

    static int digits(int num){
        int count = 0;

        while(num != 0){
            num = num / 10;
            count++;
        }
        return(count);
    }

    static int reverseDigits(int num){
        int reversed = 0;

        while(num != 0){
            int digit = num%10;
            reversed = reversed*10 + digit;
            num = num/10;
        }
        return(reversed);
    }

    static boolean isSubsequence(int num1, int num2) {
        boolean var = (num1 <= num2);
        int i = 1;
        int j = 0;
        int sizeNum = digits(num1);
        int compare = 0;
        int num2reduced = num2;
        int reducer = 10;

        while(i < sizeNum){
            reducer = reducer * 10;
            i++;
        }

        if(var){
            while(j < sizeNum){
                compare = num2reduced%reducer;
                num2reduced = num2reduced/10;
                j++;
                if(compare == num1){
                    var = true;
                    return var;
                }
                else if(digits(num2reduced) == digits(num1) && num2reduced != num1){
                    var = false;
                    return var;
                }
            }
        }
        else{
            var = false;
            return var;
        }
        return var;
    }

    static int subSequence(int num, int from, int to){
        
    }
}

aaaaaa|!!!