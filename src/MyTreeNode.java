public class MyTreeNode {
    private Node root;//根节点

    /**
     * 增加节点方法
     * @param data
     */
    public void add(int data){
        if (root==null){
            //第一次存储存储在根节点
            root=new Node(data);
        }else {
            root.add(data);
        }
    }

    /**
     * 根据数据查查询节点
     * @param data
     * @return
     */
    public Node search(int data) {
        if (root==null){
            return null;
        }else{
            return root.search(data);
        }
    }

    /**
     * 前序遍历
     */
    public void print(){
        if (root!=null){
            root.print();
        }
     }
    /**
     * 删除结点
     */
    public void dele(int value){
        if (root==null){
            return;
        }else {
            root.dele(value);
        }
    }

    /**
     * 修改元素
     */
    public  void update(int oldValue,int newValue){
        if (root==null){
            return;
        }
        root.update(oldValue,newValue);
    }

    private  class Node{
        private int data;//要存储的数据
        private Node left;//左子树
        private Node right;//右子树


        /**
         * 增加节点的方法
         * @param data
         */
        private void add(int data){
            if (data<this.data){
                //左边存放数据
                if (this.left==null){
                    //左边能存放数据
                    this.left=new Node(data);
                }else {
                    //将当前节点看成根节点再次调用添加方法
                    this.left.add(data);
                }
            }else if (data>this.data){
                //右边能存放数据
                if (this.right==null){
                    //数据直接添加到当前节点的右边
                    this.right=new Node(data);
                }else {
                    //将当前节点看成根节点再次调用添加方法
                    this.right.add(data);
                }
            }
        }
        /**
         * 删除元素
         */
        private boolean dele(int data){
            //如果根部为空 直接返回false
            if (root == null) {
                return false;
            }
            Node cur= root;
            Node parent=root;
            boolean isLeftNode=true;
            while (cur.data!=data) {
                //若cur.数据不等于参数
                parent = cur;
                //将cur设为自己父节点
                if (data < cur.data) {
                    //若参数小于cur的数据
                    isLeftNode = true;
                    //将boolean标志设为true
                    cur = cur.left;
                    //cur向左推
                } else {
                    //将boolean标志设为false
                    isLeftNode = false;
                    //将cur往右推
                    cur = cur.right;
                }

                //判断是不是叶子结点 如果当前节点的左右两边都没有节点 那么他就是叶子节点
                if (cur.left==null&&cur.right==null){
                    //如果是根结点 直接将此节点置空
                    if (cur==root){
                        root=null;
                    }else{
                        //删除的是父节点的左节点,将父节点的左节点置空
                        if (cur==parent.left){
                            parent.left=null;
                        }else {
                            //删除的是父节点的右节点,将父节点的右节点置空
                            parent.right=null;
                        }
                    }
                    //如果只有左节点
                }else if (cur.right==null){
                        //如果删除的是根节点
                        if (cur==root){
                            //直接将当前节点的左子节点设为根节点
                            root=cur.left;
                            //如果删除的是根节点左节点
                        }else if (isLeftNode){
                            //boolean标志位若是true 则当前节点的左子节点直接归父节点直接管理,当前节点是父节点的左子树
                            parent.left=cur.left;
                        }else {
                            //当前节点的右子节点直接归父节点直接管理
                            parent.right=cur.right;
                        }
                        //如果只有右子节点
                }else if (cur.left==null){
                    if (cur==root){
                        //若是根节点,直接将当前节点右子节点设为根节点
                        root=cur.right;
                    }else if (isLeftNode){
                        //若boolean标志位为true 则代表要删除的节点是父节点的左节点,此时将父节点的左子树直指向要删除节点的右子树
                        parent.left=cur.right;
                    }else {
                        //要删除的节点是父节点的右节点,将父节点的右子树直接指向要删除节点的右子树
                        parent.right=cur.right;
                        //将删除节点的右子树置空
                        cur.right=null;
                    }
                }else{
                    //要删除的结点有左右子结点
                    //找到一个中继节点
                    Node succ =getSucc(cur);//succ=11 cur=20
                    //如果当前节点是根节点
                    //直接将中继节点设为根节点
                    if (cur==root){
                        root=succ;
                        //若当前节点是父节点的左节点 将中继节点设为父节点的左子树
                    }else if (isLeftNode){
                        parent.left=succ;
                    }else {
                        //若当前节点是父节点的右节点 将中继节点设为父节点的右子树
                        parent.right=succ;
                    }
                    System.out.println(succ.data);
                    //将当前节点指向的左子树换给中继节点指向的左子树
                    succ.left=cur.left;
                }
            }
            return true;
        }

        /**
         * 寻找删除节点过后的中继节点
         * @param delNode
         * @return
         */
        public  Node getSucc(Node delNode){
            //将中继节点的父节点设为要删除的节点
            Node succParent = delNode;
            //将中继节点设为要删除的节点
            Node succ=delNode;
            //设置一个临时节点指向要删除节点的右子树
            Node cur=delNode.right;
            //当右子树不为空时
            while (cur!=null){
                //中继节点指向自己
                succParent=succ;
                //将临时节点指向中继节点
                succ=cur;
                //将临时节点的左子树指向临时节点 若临时节点的左子树为空,则结束循环 并置空临时节点
                cur=cur.left;
            }
            //当中继节点不等于要删除节点的右子树
            if (succ!=delNode.right){
                //中继节点的左子树指向中继节点的右子树
                succParent.left=succ.right;
                //要删除节点的右子树指向中继节点的右子树
                succ.right=delNode.right;
            }
            //返回中继节点
            return succ;
        }
        /**
         * 修改元素
         */
        private void update(int oldValue,int newValue) {
            if(root==null) {
                System.out.println("修改失败");
            }
            //把cur定义为根节点
            Node cur=root;
            //cur不为空并且cur的数值不等于传来的value开始循环查找知道数值相等
            while((cur!=null)&&(cur.data!=oldValue)){
                //当传来的数值小于根节点的数值
                if(oldValue<cur.data){
                    //向左子树中查找
                    cur=cur.left;
                }else{
                    //向右子树中查找
                    cur=cur.right;
                }
            }
            //把要修改的数值赋值给找到的节点的值
            cur.data=newValue;
        }
        /**
         * 查找某个元素
         * @param data
         * @return
         */
        private Node search(int data){
            //如果根节点数值等于参数 直接返回根节点
            if (this.data == data) {
                return this;
                //如果参数小于根部节点 则向左查找
            } else if (data < this.data) {
                //如果root的左边为空, 则返回空
                if (left == null) {
                    return null;
                }
                //将左节点看成根节点调用查找方法
                return left.search(data);
            } else if (data > this.data) {
                //如果参数大于根节点 则向右查找
                if (right == null) {
                    //如果右部为空, 则直接返回空
                    return null;
                }
                //将右节点看成根节点调用查找方法
                return right.search(data);
            } else {
                //代码顺序向下执行 直接返回空
                return null;
            }
        }
        private void print(){
            //先打印自己
            System.out.print(this.data+" ");
            //如果自己的左边不为空, 则左节点调用打印方法直至左边没节点
            if (this.left!=null){
                this.left.print();
            }
            //若自己的右边不为空,则右节点调用打印方法 直至右边没有节点
            if (this.right!=null){
                this.right.print();
            }
        }
        private Node(int data){
            this.data=data;
        }
    }
}
