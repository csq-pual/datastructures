package array;

// 创建二维数组 找有效值 二维->稀松
public class 稀松数组_五子棋 {
    public static void main(String[] args) {
        //创建棋盘数组
        System.out.println("期盼数组为: ");
        int chessarray[][] = new int[11][11];
        chessarray[0][1] = 2;
        chessarray[2][3] = 3;
        for (int i=0;i<11;i++){
            //System.out.print("\n");
            for (int j=0;j<11;j++){
                System.out.printf("%d",chessarray[i][j]);
            }
            System.out.print("\n");
        }

        //找到有效值个数
        int num=0;
        for (int i=0;i<11;i++){
            for (int j=0;j<11;j++){
                if (chessarray[i][j]!=0){
                    num++;
                }
            }
        }
        System.out.println();
        System.out.println("有效值num = "+num);
        System.out.println();

        //二维->稀松 行 列 值
        int count=0;
        int sparsearray[][] = new int [num+1][3];
        sparsearray[0][0] = 11;
        sparsearray[0][1] = 11;
        sparsearray[0][2] = num;
        for (int i=0;i<11;i++){
            for (int j=0;j<11;j++){
                if (chessarray[i][j]!=0) {
                    sparsearray[count+1][0] = i;
                    sparsearray[count+1][1] = j;
                    sparsearray[count+1][2] = chessarray[i][j];
                }
            }
        }


        //打印稀松数组
        System.out.println("稀松数组为: ");
        for (int i=0;i<3;i++){
            //System.out.println();
            for (int j=0;j<3;j++){
                System.out.printf("%d\t",sparsearray[i][j]);
            }
            System.out.println();
        }
    }
}

