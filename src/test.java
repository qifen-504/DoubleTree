public class test {
    public static void main(String[] args) {
        MyTreeNode mt = new MyTreeNode();
        mt.add(10);
        mt.add(8);
        mt.add(6);
        mt.add(9);
        mt.add(13);
        mt.add(11);
        mt.add(15);
        mt.add(20);
        mt.add(14);
        mt.print();
        System.out.println();
        System.out.println("--------------------------");
        mt.dele(13);
//        mt.update(13,12);
        mt.print();
        System.out.println();

    }
}
