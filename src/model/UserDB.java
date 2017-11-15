package model;

public class UserDB{

    private static MyLinkedList<User> users1 = new MyLinkedList<>();

    public static MyLinkedList<User> getUsers1() {
        return users1;
    }

    public static void setUsers1(MyLinkedList<User> users1) {
        UserDB.users1 = users1;
    }
}
