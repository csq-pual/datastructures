package list;/*
*Josephu问题:
* 设编号为1，2，… n的n个人围坐一圈，约定编号为k（1<=k<=n）的人从1开始报数，数到m 的那个人出列，它的下一位又从1开始报数，
* 数到m的那个人又出列，依次类推，直到所有人出列为止，由此产生一个出队编号的序列。
------------------------------------------------------------------
* 分析:
* 单向环形链表
* n->共有n个人
* m->数到m下的那个人出列
* k->从第k个人开始数
* */
public class Josephu问题 {
    public static void main(String[] args) {
        /*addboy中自己创建节点
        boy i1 = new boy(1);
        boy i2 = new boy(1);
        boy i3 = new boy(1);
        boy i4 = new boy(1);
        boy i5 = new boy(1);*/


        //创建链表
        CircleSingleLinkedList list = new CircleSingleLinkedList();


        //测试添加节点
        list.addboy(5);


        //遍历
        list.showlist();


        //测试出队函数
        list.countboy(2,1,5);
    }


    //定义节点
    static class boy {
        private int num;
        private boy next;


        //构造器
        public boy(int num) {
            this.num = num;
        }


        //set改写 get读取
        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public boy getNext() {
            return next;
        }

        public void setNext(boy next) {
            this.next = next;
        }


        //重写
        @Override
        public String toString() {
            return "boy{" +
                    "num=" + num +
                    '}';
        }
    }


    //单向链表
    static class CircleSingleLinkedList{
        //定义first节点
        private boy first = null;


        //添加节点
        public void addboy(int num){
            if (num<2){
                System.out.println("输入num不合法！！！");
                return;
            }
            //辅助变量 first不能动
            boy curboy = first;
            for (int i=1;i<=num;i++){
                boy boy = new boy(i);
                //如果是第一个节点
                if (i == 1){
                    first = boy;
                    first.setNext(first);//成环
                    curboy = first;
                }else{
                    curboy.setNext(boy);
                    boy.setNext(first);
                    curboy = boy;//将curboy置于最后节点
                }
            }
        }


        //遍历节点
        public void showlist(){
            //辅助变量
            boy curboy = first;
            //判空
            if (first==null){
                System.out.println("此链表为空！！！");
                return;
            }

            while (true){
                System.out.println(curboy.getNum());
                if (curboy.getNext()==first){
                    break;
                }
                curboy = curboy.getNext();
            }
        }


        //出队序列
        /*
        * 思路
        * 1.end辅助变量指向最后
        * 2.报数时end和first均向前移动m-1次
        * 3.first小孩出圈
        * first = first.getNext()
        * end.getNext() = first
        * */
        public void countboy(int countnum,int startnum,int num){
            //先判断数据是否合法
            if(num<2||startnum<1||first==null){
                System.out.println("输入数据不合法！！！");
            }
            //定义辅助变量
            boy end = first;
            //将end指向最后
            while (true){
                if (end.getNext()==first){
                    break;
                }
                end = end.getNext();
            }
            //报数出队前校准first和end 第K个开始 所以first end向后移动K-1个节点
            for (int i=0;i<startnum-1;i++){
                first = first.getNext();
                end = end.getNext();
            }
            //出队时 first end同时移动m-1个节点 然后出队
            while(true){
                if (first == end){
                    break;
                }
                for (int j=0;j<countnum-1;j++){
                    first = first.getNext();
                    end = end.getNext();
                }
                System.out.printf("%d->",first.getNum());
                //出队
                first = first.getNext();
                end.setNext(first);
            }
            //最后剩余一个小孩
            System.out.printf("%d",first.getNum());
        }
    }
}
