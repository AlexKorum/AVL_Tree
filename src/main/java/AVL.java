public class AVL {
    private Node ferst;
    private int size = 0;

    /*public AVL() {
        this.ferst = new Node();
    }*/

    public boolean add(int e) {
        if (ferst == null) {//Проверка на первый элемент(Есть ли корень у дерева)
            this.ferst = new Node();
            this.ferst.setID(e);//Добавляем элемент в корень если он не заполнен
            size++;//Увеличиваем размер дерева(количество элементов в нем
        } else {
            Node newNode = new Node();//Создаем элемент добавления(тот который будет вставлен в дерево)
            newNode.setID(e);//Задаем ключ у нового элемента
            newNode.setParent(this.ferst);//Создаем указатель на родителя
            while (true) {//"Бесконечный" цикл прохождения по дереву
                if (newNode.getID() == newNode.getParent().getID()) {//Если элемент уже существует, то возвращаем False
                    return false;
                }
                if (newNode.getID() < newNode.getParent().getID()) {//Сравнение элемента в вставляемом объекте и его родителя
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

            while (true) {//Бесконечный цикл для прохода по дереву вверх
                if (newNode.getParent() == null) {//Сверяем, есть ли у нашего элемента родитель
                    break;//Если да, выходим из цикла
                }
                if (newNode.getParent().getLeft() == newNode) {//Наш элемент левый?
                    newNode.getParent().setSizeChild(newNode.getParent().getSizeChild() - 1);//Отнимаем от разности потомков родителя 1
                } else {
                    newNode.getParent().setSizeChild(newNode.getParent().getSizeChild() + 1);//Прибавляем разности потомков родителя 1
                }
                newNode = newNode.getParent();//Передвигаемся на следующий элемент вверх(На родителя)
            }
        }
        return true;
    }

    public boolean remove(int e) {//Удаление элемента
        Node node = retNode(e);//Находим требуемый для удаления элемент
        Node pass = node;
        if (node.getLeft() == null && node.getRight() == null) {//У нашего элемента есть 2 потомка?
            if (node.getParent() == null) {//Да. У нашего элемента есть родитель?
                this.ferst = null;//Нет. Значит это первый и единственный элеменит в дереве
                return true;//Возвращаем удачное удаление
            } else {//Да, у нашего элемента еть родитель
                UpNodeSizeChild(node);
                if (node.getParent().getLeft().equals(node)) {//Наш элемент левый?
                    node.getParent().setLeft(null);//Да. Удаляем связь родителя с левым потомком
                    return true;//Возвращаем удачное удаление
                } else {//Нет, он правый
                    node.getParent().setRight(null);//Удаляем связь родителя с правым потомком
                    return true;//Возвращаем удачное удаление
                }
            }
        }
        if (!(node.getLeft() == null) && node.getRight() == null) {//Наш элемент имеет только левый потомок?
            if (node.getParent() == null) {//Да. У нашего элемента есть родитель?
                this.ferst = node.getLeft();//Нет. Делаем корневым элементом левый потомок преведущего
                this.ferst.setParent(null);//Обнуляем значение родителя
                return true;//Возвращаем удачное удаление
            } else {//Да, есть родитель
                Node ret = node.getParent();//Создаем ссылку на родителя нашего элемента
                UpNodeSizeChild(node);
                if (node.getParent().getLeft().equals(node)) {//
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
                this.ferst = node.getRight();
                this.ferst.setParent(null);
                return true;
            } else {
                Node ret = node.getParent();
                UpNodeSizeChild(node);
                if (node.getParent().getLeft() == node) {
                    ret.setLeft(node.getRight());
                    node.getRight().setParent(ret);
                    return true;
                } else {
                    ret.setRight(node.getRight());
                    node.getRight().setParent(ret);
                    return true;
                }
            }
        }
        if (!(node.getLeft() == null) && !(node.getRight() == null)) {
            Node nodeNext = next(node);
            if (nodeNext.getRight() == null) {
                if (nodeNext.getParent().getLeft().equals(nodeNext)) {
                    nodeNext.getParent().setLeft(null);
                } else {
                    nodeNext.getParent().setRight(null);
                }
                insertNode(node, nodeNext);
                return true;
            } else {
                if (nodeNext.getParent().getLeft() == nodeNext) {
                    nodeNext.getParent().setLeft(nodeNext.getRight());
                    nodeNext.getRight().setParent(nodeNext.getParent());
                    nodeNext.setRight(null);
                } else {
                    nodeNext.getParent().setRight(nodeNext.getRight());
                    nodeNext.getRight().setParent(nodeNext.getParent());
                    nodeNext.setRight(null);
                }
                insertNode(node, nodeNext);

                return true;
            }
        }

        return false;
    }

    private void UpNodeSizeChild(Node pass) {
        while (true) {
            if (pass.getParent() == null) {
                break;
            }
            if (pass.getParent().getLeft() == pass) {
                pass.getParent().setSizeChild(pass.getParent().getSizeChild() + 1);
            } else {
                pass.getParent().setSizeChild(pass.getParent().getSizeChild() - 1);
            }
            pass = pass.getParent();
        }
    }

    private void insertNode(Node delNode, Node nodeNext) {//Вставка элемента вместо существующего
        UpNodeSizeChild(nodeNext);
        nodeNext.setSizeChild(delNode.getSizeChild());
        nodeNext.setParent(delNode.getParent());
        nodeNext.setLeft(delNode.getLeft());
        nodeNext.setRight(delNode.getRight());
        if (delNode.getLeft() != null) {
            delNode.getLeft().setParent(nodeNext);
        }
        if (delNode.getRight() != null) {
            delNode.getRight().setParent(nodeNext);
        }
        if (delNode.getParent() != null) {
            if (delNode.getParent().getLeft().equals(delNode)) {
                delNode.getParent().setLeft(nodeNext);
            }
            if (delNode.getParent().getRight().equals(delNode)) {
                delNode.getParent().setRight(nodeNext);
            }
        } else {
            this.ferst = nodeNext;
        }
    }

    public Node minElement() {
        return min(this.ferst);
    }

    public Node retNode(int e) {
        Node node = this.ferst;
        while (true) {
            if (node.getID() == e) {
                return node;
            }
            if (e < node.getID()) {
                if (node.getLeft() == null) {
                    return null;
                }
                node = node.getLeft();
            }
            if (e > node.getID()) {
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

    public Node next(Node node) {//Следующий элемент
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
        return this.ferst;
    }

    public void visual(Node node) {
        if (node.getLeft() != null) {
            visual(node.getLeft());
        }
        System.out.println(node.getID() + " " + node.getSizeChild());
        if (node.getRight() != null) {
            visual(node.getRight());
        }
    }

    public void testNode(Node node) {
        if (node != null) {
            System.out.println("Node: " + node.getID());
            if (node.getParent() != null) {
                System.out.println("Parent: " + node.getParent().getID());
            }
            if (node.getLeft() != null) {
                System.out.println("Left: " + node.getLeft().getID());
            }
            if (node.getRight() != null) {
                System.out.println("Right: " + node.getRight().getID());
            }
            System.out.println();
        } else {
            System.out.println("Don't node");
            System.out.println();
        }
    }
}