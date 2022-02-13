package stack;
/**
 * 思路
 * 1.若是数字直接入栈
 * 2.若是符号分两种情况
 * 3.若当前操作符的优先级等于或小于栈顶运算符-->取出两数计算后的结果入栈 并将当前的符号入栈
 * 4.若当前运算符大于栈顶运算符 则直接入栈
 */
public class 计算器 {
    public static void main(String[] args) {

        //定义表达式
        String expression = "17+2*2-6/2";

        //创建数栈和符号栈
        Arraystack2 numberStack = new Arraystack2(10);
        Arraystack2 operStack = new Arraystack2(10);

        //定义索引用于扫描
        int index = 0;

        //定义弹出两数 弹出运算符 计算结果
        int n = 0;
        int m = 0;
        int oper = 0;
        int result = 0;
        char ch = ' ';//每次扫描的运算符保存到ch
        String keepNum = "";//用于多位数的拼接


        //开始while扫描表达式
        while(true){
            //依次得到expression  的每个字符
            ch = expression.substring(index,index+1).charAt(0);
            //判断数字还是运算符
            if (operStack.isOper(ch))
            //如果是符号
            {
                //判断符号栈是否为空
                if (!operStack.isEmpty())
                {
                    //比较栈顶运算符
                    if (operStack.piority(ch) <= operStack.piority(operStack.peek()))
                    {
                        n = numberStack.pop();
                        m = numberStack.pop();
                        oper = operStack.pop();
                        result = numberStack.calculate(n,m,oper);
                        //将运算结果放入数栈
                        numberStack.push(result);
                        //将运算符放入运算符栈
                        operStack.push(ch);
                    }else{
                        //优先级大于栈顶
                        operStack.push(ch);
                    }
                }
                else
                {
                    //若栈空
                    operStack.push(ch);
                }
            }
            //如果是数字
            else
            {
                //若数为多位数时
                //应该将index向后扫描一位 如果是数字就是多位数 如果是符号就不管了
                //多位数则需要拼接

                //处理多位数
                keepNum += ch;
                //如果index在最后一个字符 直接入栈
                if (index == expression.length()-1){
                    numberStack.push(Integer.parseInt(keepNum));
                }else {
                    //判断是字符还是数字
                    //！！！注意看后一位 而不是index++
                    if (operStack.isOper(expression.substring(index+1,index+2).charAt(0))){
                        numberStack.push(Integer.parseInt(keepNum));
                        //将keepNum清空
                        keepNum = "";
                    }
                }
            }
            
            //让index + 1,判断是否扫描完毕
            index++;
            if (index == expression.length()){
                break;
            }
        }

        //剩余的oper中全是同等优先级的也就是+-法
        while(true){
            //若符号栈为空 则计算结束
            if (operStack.isEmpty()){
                break;
            }
            n = numberStack.pop();
            m = numberStack.pop();
            oper = operStack.pop();
            result = numberStack.calculate(n,m,oper);
            //将运算结果放入数栈
            numberStack.push(result);
        }
        int result2 = numberStack.peek();
        System.out.printf("计算表达式%s完毕 其结果为: %d",expression,result2);
    }
    //定义栈
    static class Arraystack2{
        private int maxSize;//栈大小
        private int[] stack;//数组模拟栈
        private int top = -1;//栈顶


        //构造器
        public Arraystack2(int Maxsize){
            this.maxSize = Maxsize;
            stack = new int[Maxsize];
        }

        //判断栈满
        public boolean isFull(){
            return top == maxSize-1;
        }

        //判断栈空
        public boolean isEmpty(){
            return top == -1;
        }

        //入栈
        public void push(int value){
            //判断栈满
            if (isFull()){
                System.out.println("此栈已满!!!\n");
                return;
            }
            top++;
            stack[top] = value;
        }

        //出栈
        public int pop(){
            if (isEmpty()){
                throw new RuntimeException("此栈已空!!!\n");
            }
            int value = stack[top];
            top--;
            return value;
        }

        //看栈顶值
        public int peek(){
            return stack[top];
        }

        //遍历
        public void showstack(){
            if (isEmpty()){
                throw new RuntimeException("此栈已空!!!\n");
            }
            for (int i = top;i >= 0;i--){
                System.out.printf("stack[%d] = %d\n",i,stack[i]);
            }
        }


        //判断运算符优先级 优先级越大数字越高(自己定)
        public int piority(int oper){
            if (oper == '*'||oper == '/'){
                return 1;
            }else if (oper == '-'||oper == '+'){
                return 0;
            }else{
                return -1;
            }
        }


        //判断运算符
        public boolean isOper(char val){
            return val == '+'||val == '-'||val == '*'||val == '/';
        }


        //弹出那两个数的计算方法
        public int calculate(int n,int m,int oper){
            int result = 0;
            switch (oper){
                case '+':
                    result = n+m;
                    break;
                case '-':
                    result = m-n;//注意减法 栈先进后出
                    break;
                case '*':
                    result = n*m;
                    break;
                case '/':
                    result = m/n;//注意除法 栈先进后出
                    break;
                default:
                    break;
            }
            return result;
        }
    }
}
