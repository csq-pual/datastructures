package recursion;

public class labyrinth {
    public static void main(String[] args) {
        //创建二维数组模拟迷宫
        //八行七列地图
        int[][] map = new int[8][7];
        //1表示不通
        //设置挡板
        map[3][1] = 1;
        map[3][2] = 1;

        //将上下全置1
        for (int i = 0;i < 7;i++){
            map[0][i] = 1;
            map[7][i] = 1;
        }

        //将左右全置1
        for (int j = 0;j < 8;j++){
            map[j][0] = 1;
            map[j][6] = 1;
        }

        //输出地图
        for (int i = 0;i<8;i++){
            System.out.println();
            for (int j = 0;j<7;j++){
                System.out.printf(map[i][j] + " ");
            }
        }
        //System.out.println();
        //System.out.println("--------------------------------------------------");


        //使用递归回溯找路
        setWay(map,1,1);

        //输出新地图
        for (int i = 0;i<8;i++){
            System.out.println();
            for (int j = 0;j<7;j++){
                System.out.printf(map[i][j] + " ");
            }
        }
    }

    /**
     * 说明
     * 从(1,1)出发到(6,5)
     * 1表示墙 2表示通路 3表示走过没通
     * 策略 :下->右->上->左
     * @param map 表示地图
     * @param i 从哪个位置查找 行
     * @param j 从哪个位置查找 列
     * @return 找到通路返回true
     */
    public static boolean setWay(int map[][],int i,int j){
        //设置终点
        if (map[6][5] == 2){
            return true;
        }else {
            if (map[i][j] == 0) {
                //如果没走过 就假设能通过
                map[i][j] = 2;
                if (setWay(map, i + 1, j)) {
                    //向下走
                    return true;
                }
                else if (setWay(map, i, j + 1)) {
                    //向右走
                    return true;
                }
                else if (setWay(map, i, j - 1)) {
                    //向左走
                    return true;
                }
                else if (setWay(map, i - 1, j)) {
                    //向上走
                    return true;
                }
                //上下左右都不行时
             else {
                map[i][j] = 3;//标记地图此点不通
                return false;
            }
        }else{
                //如果map[i][j]!=0 说明此点有值1,2,3 说明不用上下左右找路
                return false;
            }
        }
    }
}
