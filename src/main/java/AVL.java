public class AVL {
    private Node ferst;
    private int size = 0;

    public AVL() {
        this.ferst = new Node();
    }

    public boolean add(int e) {
        if (size == 0) {//Проверка на первый элемент(Есть ли корень у дерева)
            ferst.setE(e);//Добавляем элемент в корень если он не заполнен
            size++;//Увеличиваем размер дерева(количество элементов в нем
        } else {
            Node newNode = new Node();//Создаем элемент добавления(тот который будет вставлен в дерево)
            newNode.setE(e);//Задаем ключ у нового элемента
            newNode.setParent(this.ferst);//Создаем указатель на родителя
            while (true) {//"Бесконечный" цикл прохождения по дереву
                if (newNode.getE() == newNode.getParent().getE()) {//Если элемент уже существует, то возвращаем False
                    return false;
                }
                if (newNode.getE() < newNode.getParent().getE()) {//Сравнение элемента в вставляемом объекте и его родителя
                    if (newNode.getParent().getLeft() == null) {//Если элемент меньше родительского, то проверяем существование левого потомка
                        newNode.getParent().setLeft(newNode);//Если его нет, то родителю нашего элемента назначаем его левым потомком
                        size++;//Увеличиваем размер дерева
                        break;//выходим из цикла
                    } else {//если левый потомок существует, то присваеваем левый потомок родителя, родителм нашего элемента
                        newNode.setParent(newNode.getParent().getLeft());
                    }
                } else {//Если ключ элемента оказался больше, проделываем эдентичные операций для правого потомка
                    if (newNode.getParent().getRight() == null) {
                        newNode.getParent().setRight(newNode);
                        size++;
                        break;
                    } else {
                        newNode.setParent(newNode.getParent().getRight());
                    }
                }
            }
        }
        return true;
    }

    public boolean remove(int e) {
        Node node = retNode(e);
        if (node.getLeft() == null && node.getRight() == null) {
            if (node.getParent() == null) {
                ferst = null;
                return true;
            } else {
                if (node.getParent().getLeft().equals(node)) {
                    node.getParent().setLeft(null);
                    return true;
                } else {
                    node.getParent().setRight(null);
                    return true;
                }
            }
        }
        if (!(node.getLeft() == null) && node.getRight() == null) {
            if (node.getParent() == null) {
                ferst = node.getLeft();
                ferst.setParent(null);
                return true;
            } else {
                Node ret = node.getParent();
                if (node.getParent().getLeft().equals(node)) {
                    ret.setLeft(node.getLeft());
                    node.getLeft().setParent(ret);
                    return true;
                } else {
                    ret.setRight(node.getLeft());
                    node.getLeft().setParent(ret);
                    return true;
                }
            }
        }
        if (node.getLeft() == null && !(node.getRight() == null)) {
            if (node.getParent() == null) {
                ferst = node.getRight();
                ferst.setParent(null);
                return true;
            } else {
                Node ret = node.getParent();
                if (node.getParent().getLeft().equals(node)) {
                    ret.setLeft(node.getRight());
                    node.getLeft().setParent(ret);
                    return true;
                } else {
                    ret.setRight(node.getRight());
                    node.getLeft().setParent(ret);
                    return true;
                }
            }
        }
        return false;
    }

    public Node minElement() {
        return min(ferst);
    }

    public Node retNode(int e) {
        Node node = this.ferst;
        while (true) {
            if (node.getE() == e) {
                return node;
            }
            if (e < node.getE()) {
                if (node.getLeft() == null) {
                    return null;
                }
                node = node.getLeft();
            }
            if (e > node.getE()) {
                if (node.getRight() == null) {
                    return null;
                }
                node = node.getRight();
            }
        }
    }

    private Node min(Node Rode) {
        Node node = Rode;
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    public Node next(int e) {//Следующий элемент
        Node node = retNode(e);//Создание ссылки на текущий элемент
        if (node.getRight() == null) {//Проверка на существование правого потомка
            while (true) {//Цикл подъема
                if (node.getParent() == null) {//Если дошли до родительского возвращаем Null
                    return null;//Возврат Null
                } else {//Если родительский элемент существует
                    if (node.equals(node.getParent().getLeft())) {//Проверяем, если наш элемент левый
                        return node.getParent();//Возвращаем родительский элемент
                    } else {//Если элемент правый
                        node = node.getParent();//Поднимаемся пока наш элемент не правый или родитель Null
                    }
                }
            }
        } else {//Если существует правый потомок
            return min(node.getRight());//Возвращаем минимум из правого поддерева
        }
    }

    public Node getFerst() {
        return ferst;
    }
}