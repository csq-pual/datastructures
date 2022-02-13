package stack;

public class 计算器 {
    /*
 * 思路
 * 1.若是数字直接入栈
 * 2.若是符号分两种情况
 * 3.若当前操作符的优先级等于或小于栈顶运算符-->取出两数计算后的结果入栈 并将当前的符号入栈
 * 4.若当前运算符大于栈顶运算符 则直接入栈
     */
    public static void main(String[] args) {

        //定义表达式
        String expression = "3-5+9*2-8/2";

        //定义数栈和符号栈
        arraystack numberstack = new arraystack(10);
        arraystack operstack = new arraystack(10);

        //index
        int index = 0;

        //定义
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int result = 0;
        char ch = ' ';
        String keepnum = "";


        //算
        while (true){
            //取出expression元素
            ch = expression.substring(index,index+1).charAt(0);
            //判断数字或符号
            if (operstack.isOper(ch)){
                //如果是运算符
                if (!operstack.isEmpty()){
                    //判断符号栈是否为空 如果不为空 比较优先级
                    if (operstack.piority(operstack.peek())>=operstack.piority(ch)){
                        //栈顶优先级大 弹出两个数计算
                        num1 = numberstack.pop();
                        num2 = numberstack.pop();
                        oper = operstack.pop();
                        result = numberstack.calculat(num1,num2,oper);
                        //结果放入数栈
                        numberstack.push(result);
                        //hc放入符号栈
                        operstack.push(ch);
                    }else{
                        //优先级大于栈顶 直接push
                        operstack.push(ch);
                    }
                }else{
                    //如果符号栈为空
                    operstack.push(ch);
                }
            }else{
                //如果是数字
                keepnum += ch;
                //若数为多位数时
                //应该将index向后扫描一位 如果是数字就是多位数 如果是符号就不管了
                //多位数则需要拼接
                if (index == expression.length()-1){
                    //如果在最后一位直接push
                    numberstack.push(Integer.parseInt(keepnum));
                }else{
                    //判断后一位是字符还是数字
                    if (operstack.isOper(expression.substring(index+1,index+2).charAt(0))){
                        //如果后一位是符号 push
                        numberstack.push(Integer.parseInt(keepnum));
                        //清空keepnum
                        keepnum = "";
                    }
                }
            }
            index++;
            if (index == expression.length()){
                break;
            }
        }

        //剩余全是同级计算
        while(true){
            //如果符号栈是空的计算结束
            if (operstack.isEmpty()){
                break;
            }
            num1 = numberstack.pop();
            num2 = numberstack.pop();
            oper = operstack.pop();
            result = numberstack.calculat(num1,num2,oper);
            //结果放入数栈
            numberstack.push(result);
        }
        int result2 = numberstack.peek();
        System.out.printf("计算表达式%s完毕 其结果为: %d",expression,result2);
    }


    //定义栈
    static class arraystack{
        private int top=-1;
        private int[] stack;
        private int maxsize;

        //构造器
        public arraystack(int maxsize) {
            this.maxsize = maxsize;
            stack = new int[maxsize];
        }

        //判断栈满
        public boolean isFull(){
            return top == maxsize-1;
        }

        //判断栈空
        public boolean isEmpty(){
            return top == -1;
        }

        //pop函数
        public int pop(){
            if (isEmpty()){
                throw new RuntimeException("此栈为空！！！\n");
            }
            int value = stack[top];
            top--;
            return value;
        }

        //push函数
        public void push(int value){
            if (isFull()){
                System.out.println("此栈已满！！！\n");
            }
            top++;
            stack[top] = value;
        }

        //peek函数
        public int peek(){
            if (isEmpty()){
                throw new RuntimeException("此栈为空！！！\n");
            }
            return stack[top];
        }

        //piority函数
        public int piority(int oper){
            if (oper == '*'||oper == '/'){
                return 1;
            }else if (oper == '+'||oper == '-'){
                return 0;
            }else{
                return -1;
            }
        }

        //calculate函数
        public int calculat(int num1,int num2,int oper){
            int result = 0;
            switch (oper){
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num2 - num1;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    result = num2 / num1;
                    break;
                default:
                    break;
            }
            return result;
        }

        //判断运算符
        public boolean isOper(char val){ return val == '+'||val == '-'||val == '/'||val == '*';}
    }
}
