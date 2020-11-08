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
    
    //tem de ser testado. Tenho quase a certeza que está correto
    static int subsequence(int num, int from, int to){
        int var1 = num;

        var1 = var1%exponential(10, (digits(num)-from+1));
        var1 = var1/exponential(10, (digits(num)-to));

        return var1;
        
    }

    //tem de ser testado
    static boolean isValidRow(int num, int numberDigits){
        boolean var2 = true;

        int i = 1;
        int numCompare = num;

        if (num <= 0){
            var2 = false;
            return var2;
        }
        
        if(digits(num) != numberDigits){
            var2 = false;
            return var2;
        }

        while(i <= digits(num)){
            numCompare = numCompare%(exponencial(10,k))
             if(numCompare == 0){
                 var2 = false;
                 return var2;
             }
            
            i++;
        }

        return var2;

    }

    //basicamente igual ao isValidRow mas também tem de ser testado (só muda o sinal do segundo if)
    static boolean isValidSequence(int num, int numberDigits){
        boolean var3 = true;

        int i = 1;
        int numCompare = num;

        if (num <= 0){
            var3 = false;
            return var3;
        }
        
        if( digits(num) > numberDigits){
            var3 = false;
            return var3;
        }

        while(i <= digits(num)){
            numCompare = numCompare%(exponencial(10,k))
             if(numCompare == 0){
                 var3 = false;
                 return var3;
             }
            
            i++;
        }
        
        return var3;

    }

}
