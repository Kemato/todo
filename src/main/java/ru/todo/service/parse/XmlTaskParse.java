package ru.todo.service.parse;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import ru.todo.service.TaskService;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class XmlTaskParse {
    private static final String USERTASKLIST_XML = "src/main/resources/tasks.xml";

    public void read() throws JAXBException, FileNotFoundException {
        TaskService taskService = TaskService.getInstance();
        var context = JAXBContext.newInstance(TaskService.class);
        var um = context.createUnmarshaller();
        taskService.setTasks(
                ((TaskService)
                        um.unmarshal(
                                new InputStreamReader(
                                        new FileInputStream
                                                (USERTASKLIST_XML),
                                        StandardCharsets.UTF_8))
                ).getTasks());
    }

    public void write() throws JAXBException{
        TaskService taskService = TaskService.getInstance();
        var context = JAXBContext.newInstance(TaskService.class);
        var m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        m.marshal(taskService, new File(USERTASKLIST_XML));
    }
}
