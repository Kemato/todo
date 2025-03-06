package ru.todo.service.db.postgresql;

import ru.todo.model.Task;
import ru.todo.service.UserService;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;


public class TaskRepository {
    UserService userService = UserService.getInstance();

    public void createTask(Task task) throws SQLException {
        String sql = "INSERT INTO tasks (name, description, author, assigned, status, priority, date_created, deadline, date_updated, date_finished) VALUES (?,?,?,?,?,?,?,?,?,?) RETURNING id";
        try (Connection conn = PostgreSqlDriver.getConnection(); PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, task.getName());
            pstm.setString(2, task.getDescription());
            pstm.setLong(3, userService.getIdByUsername(task.getAuthor()));
            pstm.setLong(4, userService.getIdByUsername(task.getAssigned()));
            pstm.setString(5, task.getStatus());
            pstm.setString(6, task.getPriority());
            pstm.setDate(7, new Date (task.getDateCreated().getTime()));
            pstm.setDate(8, new Date (task.getDeadline().getTime()));
            pstm.setDate(9, new Date(task.getDateUpdated().getTime()));
            pstm.setDate(10, new Date (task.getDateFinished().getTime()));
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                task.setId(rs.getLong("id"));
            }
        }
    }

    public Task getTaskById(long id) throws SQLException {
        String sql = "SELECT * FROM tasks WHERE id = ?";
        try (Connection conn = PostgreSqlDriver.getConnection(); PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setLong(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                return new Task(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        userService.getUser(rs.getLong("author")).getName(),
                        userService.getUser(rs.getLong("assigned")).getName(),
                        rs.getString("status"),
                        rs.getString("priority"),
                        rs.getDate("date_created"),
                        rs.getDate("deadline"),
                        rs.getDate("date_updated"),
                        rs.getDate("date_finished")
                );
            }
        }
        return null;
    }

    public ArrayList<Task> getAllTasks() throws SQLException {
        String sql = "SELECT * FROM tasks";
        try (Connection conn = PostgreSqlDriver.getConnection(); PreparedStatement pstm = conn.prepareStatement(sql)) {
            ResultSet rs = pstm.executeQuery();
            ArrayList<Task> tasks = new ArrayList<>();
            while(rs.next()) {
                Task task = new Task(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        userService.getUser(rs.getLong("author")).getName(),
                        userService.getUser(rs.getLong("assigned")).getName(),
                        rs.getString("status"),
                        rs.getString("priority"),
                        rs.getDate("date_created"),
                        rs.getDate("deadline"),
                        rs.getDate("date_updated"),
                        rs.getDate("date_finished")
                );
                tasks.add(task);
            }
            return tasks;
        }
    }

    public void updateTask(Task task) throws SQLException {
        String sql = "UPDATE tasks SET name = ?, description = ?, assigned = ?, status = ?, priority = ?, deadline = ?, date_updated = ?, date_finished = ? WHERE id = ?";
        try (Connection conn = PostgreSqlDriver.getConnection(); PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, task.getName());
            pstm.setString(2, task.getDescription());
            pstm.setLong(3, userService.getIdByUsername(task.getAssigned()));
            pstm.setString(4, task.getStatus());
            pstm.setString(5, task.getPriority());
            pstm.setDate(6, new Date (task.getDeadline().getTime()));
            pstm.setDate(7, new Date(task.getDateUpdated().getTime()));
            pstm.setDate(8, new Date (task.getDateFinished().getTime()));
            pstm.setLong(9, task.getId());
            int rowsAffected = pstm.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Task with id " + task.getId() + " not found");
            }
        }
    }
    public void deleteTask(long id) throws SQLException {
        String sql = "DELETE FROM tasks WHERE id = ?";
        try (Connection conn = PostgreSqlDriver.getConnection(); PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setLong(1, id);
            int rowsAffected = pstm.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Task with id " + id + " not found");
            }
        }
    }
}
