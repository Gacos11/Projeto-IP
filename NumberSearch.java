public class NumberSearch{

    public static void main(String[] args){
        System.out.print(subsequence(1234, 2, 4));
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
        int i = 0;
        int sizeNum = digits(num1);
        int compare = 0;
        int num2reduced = num2;
        int reducer = exponential(10,sizeNum);

        if(var){
            while(i < sizeNum){
                compare = num2reduced%reducer;
                num2reduced = num2reduced/10;
                i++;
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
    static int subsequence(int num, int from, int to){
        int var1 = num;

        var1 = var1%exponential(10, (digits(num)-from+1));
        System.out.println(exponential(10, (digits(num)-from+1)));
        System.out.println(var1);
        var1 = var1/exponential(10, to-from-1);

        return var1;
        
    }
}
