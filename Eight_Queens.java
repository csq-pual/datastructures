package recursion;
//回溯算法
/*
* 8x8棋盘摆八个皇后棋 不能同行填列同斜线
* */
public class Eight_Queens {
    public int sum = 0;
        //设置皇后数量
        int max = 8;
        //列结果
        int[] array = new int[max];

    public static void main(String[] args) {
        Eight_Queens queens = new Eight_Queens();
        queens.SetQueen(0);
        System.out.println("一共有" + queens.sum +"种放法");
    }


    //放置皇后
    public void SetQueen(int n){
        if (n == max){
            sum++;
            print();
            System.out.println();
            return;
        }
        //依次放8个皇后
        for (int i = 0;i < max;i++){
            //先把当前皇后放到第一列
            array[n] = i;
            if (judge(n)){
                //if true 说明第n个已经方法合理 应该接着放n+1个皇后
                SetQueen(n+1);
            }
            //if false继续执行for循环
        }
    }


    /**
     * 放置皇后的检测函数
     * @param n 表示放第n个皇后
     * @return
     */
    public boolean judge(int n){
        for (int i = 0;i < n;i++){
            //当两个点的坐标的行和列的差值相同时说明在同一条斜线上
            if (array[i] == array[n] || Math.abs(n-i) == Math.abs(array[n] - array[i])){
                return false;
            }
        }
        return true;
    }

    //输出皇后位置
    public void print(){
        for (int i = 0;i < array.length;i++){
            System.out.printf("%d",array[i]);
        }
        System.out.println();
    }
}
