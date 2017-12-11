package model;

import java.util.PriorityQueue;

public class UserDB{

    private static MyLinkedList<User> users1 = new MyLinkedList<>();

    public static PriorityQueue<User> usersWithinSpecificRadius = new PriorityQueue<>();

    public static PriorityQueue<User> getUsersWithinSpecificRadius() {
        return usersWithinSpecificRadius;
    }

    public static void setUsersWithinSpecificRadius(PriorityQueue<User> usersWithinSpecificRadius) {
        UserDB.usersWithinSpecificRadius = usersWithinSpecificRadius;
    }

    public static MyLinkedList<User> getUsers1() {
        return users1;
    }

    public static void setUsers1(MyLinkedList<User> users1) {
        UserDB.users1 = users1;
    }
}
