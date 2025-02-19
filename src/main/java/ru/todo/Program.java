package ru.todo;

import ru.todo.model.*;
import ru.todo.service.hash.Hash;
import ru.todo.service.parse.JsonTaskParse;
import ru.todo.service.parse.JsonUserParse;
import ru.todo.service.TaskService;
import ru.todo.service.UserService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;

public class Program {
    Scanner sc = new Scanner(System.in);
    private User currentUser;
    UserService userService = new UserService();
    TaskService taskService = new TaskService();
    JsonUserParse jsonUserParser = new JsonUserParse();
    JsonTaskParse jsonTaskParser = new JsonTaskParse();
    public static void main(String[] args) {
        Program program = new Program();
        while (true) {
            if (program.log()) program.mainMenu();
            else program.log();
            for (int i = 0; i < 30; ++i) System.out.println();
        }
    }











}
