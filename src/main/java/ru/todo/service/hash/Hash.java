package ru.todo.service.hash;
import org.apache.commons.codec.digest.DigestUtils;

public class Hash{
    // Хешируем строку в одну строчку кода – это так просто!
    public static String sha256hex(String input) {
        return DigestUtils.sha256Hex(input);
    }
}
