package list;

/*
 * 添加（创建）
 * 1.先创建头节点head
 * 2.后面添加的节点添加到链表最后
 * 遍历
 * 1.通过辅助变量（指针），帮助遍历整个链表
*/
//108将英雄牌的例子
public class 单链表_英雄 {
    public static void main(String[] args) {
    //创建节点
        heroNode h1 = new heroNode(1,"sj","jsy");
        heroNode h2 = new heroNode(6,"ljy","yql");
        heroNode h3 = new heroNode(3,"wy","zdx");
        heroNode h4 = new heroNode(10,"lc","bzt");
        heroNode h5 = new heroNode(6,"csq","handsome boy");
        heroNode h6 = new heroNode(7,"xhy", "ugly woman");

    //创建链表
        SingleLinkedList list1 = new SingleLinkedList();

    //操作
    //添加英雄
        list1.ad(h1);
        list1.ad(h2);
        list1.ad(h3);
        list1.ad(h4);

    //按编号顺序添加
    //    list1.addbyorder(h1);
    //    list1.addbyorder(h2);
    //    list1.addbyorder(h3);
    //    list1.addbyorder(h4);
    //  list1.addbyorder(h4);

    //侧式修改节点
        list1.uodate(h5);
        list1.uodate(h6);

    //测试删除节点
        list1.delete(3);

    //遍历链表
        list1.showlist();

    /*测试总数
        System.out.println("此单链表的个数为: "+ list.SingleLinkedList.getsum(list1.getHead()) );
     */
    }
}
//定义节点
class heroNode {
    public int num;
    public String name;
    public String nickname;
    public heroNode next;//指向下一个节点

    //构造器
    public heroNode(int num, String name, String nickname) {
        this.num = num;
        this.name = name;
        this.nickname = nickname;
    }

    //为了显示方法，重新toString() 将数值表示出来的一个方法
    @Override
    public String toString() {
        return "list.heroNode{" +
                "num=" + num +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
    //定义SingleLinkedList 单链表管理英雄
    class SingleLinkedList {
        //初始化头节点
        private heroNode head = new heroNode(0, " ", " ");

        //返回头节点
        public heroNode getHead() {
            return head;
        }

        /*
         * 添加单向链表
         * 1.找到最后一个节点
         * 2.将此节点的next 指向新节点
         * */
        public void ad(heroNode hnode) {
            //head节点不能动 需要辅助节点temp
            heroNode temp = head;
            //遍历找到最后
            while (true) {
                //找到最后一个节点
                if (temp.next == null) {
                    break;
                }
                //如果没找到 将temp后移
                temp = temp.next;
            }
            //退出while循环时 temp指向链表最后节点
            temp.next = hnode;
        }

        //显示链表 需要赋值变量来遍历
        public void showlist() {
            if (head.next == null) {
                throw new RuntimeException("链表为空！！！\n");
            }
            //辅助变量
            heroNode temp = head.next;
            while (true) {
                //判断temp是否指向最后
                if (temp == null) {
                    break;
                }
                //若不为空则 输出节点
                System.out.println(temp);
                temp = temp.next;
            }
        }

        //插入算法
        /*
         * 思路
         * 1.通过辅助指针(变量)找到要添加的位置
         * 2.新节点.next = temp.next;
         * 3.temp.next = 新节点;
         * */
        public void addbyorder(heroNode n) {
            //辅助变量 找到插入位置的前一个节点
            heroNode temp = head;
            boolean flag = false;//标志添加的编号是否存在，默认是false
            while (true) {
                if (temp.next == null) {
                    break;
                }
                //找到编号
                if (temp.next.num > n.num) {
                    break;
                } else if (temp.next.num == n.num) {
                    flag = true;//说明编号已存在
                }
                temp = temp.next;//后移遍历
            }
            //判断flag 进而添加节点
            if (flag) {
                System.out.printf("编号: %d 姓名: %s 已存在，不可在添加！！！\n", n.num, n.name);
            } else {
                //插入到temp后面
                n.next = temp.next;
                temp.next = n;
            }
        }

        //修改节点信息
        //根据newn 的num来修订
        public void uodate(heroNode newn) {
            //判断空
            if (head.next == null) {
                throw new RuntimeException("此链表为空！！！\n");
            }
            //通过temp 找到对应num的节点
            heroNode temp = head.next;
            boolean flag = false;//是否找到
            while (true) {
                if (temp == null) {
                    break;//temp已经到了链表最后
                }
                if (temp.num == newn.num) {
                    flag = true;
                    break;
                    //temp.name = newn.name;
                    //temp.nickname = newn.nickname;
                }
                temp = temp.next;
            }
            //根据flag 判断是否有节点符合
            if (flag) {
                temp.name = newn.name;
                temp.nickname = newn.nickname;
            } else {
                //没有符合的节点
                System.out.printf("没有找到编号为%d的英雄！！！\n", newn.num);
            }
        }

        //删除节点
        /*
         * 思路
         * 1.找到该删除节点的前一个节点
         * 2.temp.next = temp.next.nxet;
         * 3.删除节点会被自动回收
         * */
        public void delete(int n) {
            //用flag标志是否找到 通过辅助变量temp遍历链表
            boolean flag = false;
            heroNode temp = head.next;
            while (true) {
                if (temp == null) {
                    throw new RuntimeException("此链表为空！！！\n");
                }
                if (temp.next.num == n) {
                    flag = true;
                    break;
                } else {
                    System.out.println("此链表中没有符合的节点！！！\n");
                }
                temp = temp.next;
            }
            if (flag) {
                temp.next = temp.next.next;
            }
        }
        /*求表中节点个数
        public static int getsum(list.heroNode head){

            //定义辅助变量和计数变量
            if (head.next==null){
                return 0;
            }
            list.heroNode temp = head.next;
            int sum = 0;
            while(temp.next!=null){
                //如果没到最后一个 将temp后移 总数加一
                sum++;
                temp = temp.next;
            }
            return sum;
        }*/
}