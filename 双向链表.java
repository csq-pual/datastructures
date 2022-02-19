package list;

import java.util.Scanner;
public class 双向链表 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //创建节点
        doublenode i1 = new doublenode(1,1);
        doublenode i2 = new doublenode(7,2);
        doublenode i3 = new doublenode(3,3);
        doublenode i4 = new doublenode(5,4);
        doublenode i5 = new doublenode(9,5);
        doublenode i6 = new doublenode(4,5);

        //创建链表
        doublelinkedlist list = new doublelinkedlist();


        //测试添加链表
        list.add(i1);
        list.add(i2);
        list.add(i3);
        list.add(i4);
        list.add(i5);
        list.showlist();

        System.out.println("---------------------------------------");

        //测试修改链表
        list.update(i6);
        list.showlist();

        System.out.println("----------------------------------------");

        //测试删除节点
        list.delete(3);
        list.delete(5);
        list.showlist();
    }

    //节点类
    static class doublenode{
        public doublenode next;
        public doublenode pre;
        public int data;
        public int num;

        //构造器
        public doublenode(int data, int num) {
            this.data = data;
            this.num = num;
        }

        //重写方法
        @Override
        public String toString() {
            return "doublenode{" +
                    "data=" + data +
                    ", num=" + num +
                    '}';
        }
    }

    //双向链表类
    static class doublelinkedlist{
        //初始化头节点
        private doublenode head = new doublenode(0,0);

        //返回头节点
        public doublenode getHead() {
            return head;
        }

        //输出双向链表的节点
        public void showlist(){
            if (head.next == null){
                System.out.println("此链表为空！！！");
                return;
            }
            //构造新的
            doublenode temp = head.next;
            while(true){
                if (temp==null){
                    break;
                }
                System.out.println(temp);
                temp = temp.next;
            }
        }

        //添加节点到双链表最后
        public void add(doublenode node){
            //辅助节点
            doublenode temp = head;
            while(true){
                if (temp.next==null){
                    break;
                }
                temp = temp.next;
            }
            //temp已经指向最后一个节点
            temp.next = node;
            node.pre = temp;
        }

        //修改节点
        public void update(doublenode node){
            //判空
            if (head.next==null){
                System.out.println("此链表为空！！！");
                return;
            }
            //辅助节点 找到标志
            doublenode temp = head.next;
            boolean flag = false;
            while(true){
                if (temp == null){
                    //遍历完链表
                    break;
                }
                if(node.num == temp.num){
                    flag = true;
                    break;
                }
                temp = temp.next;
            }
            //依靠flag判断链表中是否存在要修改的节点
            if (flag){
                temp.data = node.data;
            }
        }

        //删除节点
        public void delete(int num){
            //辅助变量
            doublenode temp = head.next;
            if (head.next==null){
                System.out.println("此链表为空！！！");
                return;
            }
            while(true){
                if (temp==null){
                    break;
                }
                if (temp.num == num && temp.next!=null){
                    temp.pre.next = temp.next;
                    temp.next.pre = temp.pre;
                    break;
                }
                if (temp.num == num && temp.next==null){
                    temp.pre.next = null;
                }
                temp = temp.next;
            }
        }
    }
}
